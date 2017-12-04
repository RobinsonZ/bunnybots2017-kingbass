package org.team1540.lib.motionprofile;

import static com.ctre.CANTalon.SetValueMotionProfile.Disable;
import static com.ctre.CANTalon.SetValueMotionProfile.Enable;
import static com.ctre.CANTalon.SetValueMotionProfile.Hold;

import com.ctre.CANTalon;
import com.ctre.CANTalon.SetValueMotionProfile;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Notifier;


public class ProfileExecuter {
  private static final int MIN_POINTS = 5;
  private static final int LOOP_TIMEOUT = 10;
  private int totalCnt;
  private double[][] profile;
  private CANTalon talon;
  private int state;
  private int loopTimeout;
  private boolean bStart;
  private SetValueMotionProfile setValue;
  private int loaded;
  private Notifier processor = new Notifier(new Runnable() {
    @Override
    public void run() {
      talon.processMotionProfileBuffer();
    }
  });

  private CANTalon.MotionProfileStatus status;

  public ProfileExecuter(CANTalon talon, double[][] profile, int totalCnt) {
    this.talon = talon;
    this.profile = profile;
    this.totalCnt = totalCnt;
    this.state = 0;
    this.loopTimeout = -1;
    this.bStart = false;
    this.setValue = Disable;
    this.status = new CANTalon.MotionProfileStatus();
  }

  public boolean control() {
    /* Get the motion profile status every loop */
    talon.getMotionProfileStatus(status);

    /*
     * track time, this is rudimentary but that's okay, we just want to make sure things never get
     * stuck.
     */
    if (loopTimeout >= 0) {
      /* our timeout is nonzero */
      if (loopTimeout != 0) {
        --loopTimeout;
      }
    } /* else do nothing, timeout is disabled */

    /* first check if we are in MP mode */
    if (talon.getControlMode() != CANTalon.TalonControlMode.MotionProfile) {
      /*
       * we are not in MP mode. We are probably driving the robot around using gamepads or some
       * other mode.
       */
      state = 0;
      loopTimeout = -1;
    } else {
      /*
       * we are in MP control mode. That means: starting Mps, checking Mp progress, and possibly
       * interrupting MPs if thats what you want to do.
       */
      switch (state) {
        case 0: /* wait for application to tell us to start an MP */
          if (bStart) {
            bStart = false;

            setValue = Disable;
            loaded += startFilling();
            /*
             * MP is being sent to CAN bus, wait a small amount of time
             */
            state = 1;
            loopTimeout = LOOP_TIMEOUT;
          }
          break;
        case 1: /*
                 * wait for MP to stream to Talon, really just the first few points
                 */
          /* do we have a minimum numberof points in Talon */
          if (status.btmBufferCnt > MIN_POINTS) {
            /* start (once) the motion profile */
            setValue = Enable;
            /* MP will start once the control frame gets scheduled */
            state = 2;
            loopTimeout = LOOP_TIMEOUT;
          }
          break;
        case 2: /* check the status of the MP */
          /*
           * if talon is reporting things are good, keep adding to our timeout. Really this is so
           * that you can unplug your talon in the middle of an MP and react to it.
           */
          if (!status.isUnderrun) {
            loopTimeout = LOOP_TIMEOUT;
          }

          if (loaded < totalCnt) {
            continueFilling(loaded);
          }

          /*
           * If we are executing an MP and the MP finished, start loading another. We will go into
           * hold state so robot servo's position.
           */
          if (status.activePointValid && status.activePoint.isLastPoint) {
            /*
             * because we set the last point's isLast to true, we will get here when the MP is done
             */
            setValue = Hold;
            state = 0;
            loopTimeout = -1;
            return true;
          }

          break;
      }
    }

    return false;
  }

  public SetValueMotionProfile getSetValue() {
    return setValue;
  }

  public double getTargetPosition() {
    return status.activePointValid ? status.activePoint.position : Double.NaN;
  }

  public boolean isActivePointValid() {
    return status.activePointValid;
  }

  public boolean isRunning() {
    return state == 2;
  }

  public void reset() {
    talon.clearMotionProfileTrajectories();
    setValue = Disable;
    state = 0;
    loopTimeout = -1;
    bStart = false;
  }

  public void startMotionProfile() {
    bStart = true;
  }

  private int continueFilling(int current) {
    CANTalon.TrajectoryPoint tPoint = new CANTalon.TrajectoryPoint();

    if (status.hasUnderrun) {
      DriverStation.reportError("Talon motion profile underrun", false);

      talon.clearMotionProfileHasUnderrun();
    }


    return processTPoint(tPoint) - current;
  }

  private int startFilling() {
    CANTalon.TrajectoryPoint tPoint = new CANTalon.TrajectoryPoint();

    if (status.hasUnderrun) {
      DriverStation.reportError("Talon motion profile underrun", false);

      talon.clearMotionProfileHasUnderrun();
    }

    processTPoint(tPoint);

    return profile.length;
  }

  private int processTPoint(CANTalon.TrajectoryPoint tPoint) {
    int i;
    for (i = 0; i < profile.length; i++) {
      if (status.topBufferRem == 0) { return i; }

      tPoint.position = profile[i][0];
      tPoint.velocity = profile[i][1];
      tPoint.timeDurMs = (int) profile[i][2];
      tPoint.profileSlotSelect = 0;
      tPoint.velocityOnly = false;

      tPoint.zeroPos = (i == 0);
      tPoint.isLastPoint = (i + 1) == profile.length;

      talon.pushMotionProfileTrajectory(tPoint);
    }
    return i;
  }
}
