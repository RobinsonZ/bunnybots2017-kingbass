package org.team1540.kingbass.motion;

import org.team1540.kingbass.Robot;
import com.ctre.CANTalon;
import com.ctre.CANTalon.MotionProfileStatus;
import com.ctre.CANTalon.SetValueMotionProfile;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Notifier;


public class MotionProfile {
  private int totalCnt;
  private double[][] profile;
  private CANTalon talon;
  private int state;
  private int loopTimeout;
  private boolean bStart;
  private SetValueMotionProfile setValue;
  private int loaded;
  private MotionProfileStatus status;
  private static final int MIN_POINTS = 5;
  private static final int LOOP_TIMEOUT = 10;

  class PeriodicMpRunnable implements Runnable {
    @Override
    public void run() {
      Robot.driveTrain.processMpBuffer();
    }
  }

  Notifier mpNotifier = new Notifier(new PeriodicMpRunnable());

  public MotionProfile(CANTalon talon, double[][] profile, int totalCnt) {
    mpNotifier.startPeriodic(0.005);
    this.talon = talon;
    this.profile = profile;
    this.totalCnt = totalCnt;
    this.state = 0;
    this.loopTimeout = -1;
    this.bStart = false;
    this.setValue = SetValueMotionProfile.Disable;
    this.status = new MotionProfileStatus();
  }

  public void reset() {
    talon.clearMotionProfileTrajectories();
    setValue = CANTalon.SetValueMotionProfile.Disable;
    state = 0;
    loopTimeout = -1;
    bStart = false;
  }

  public boolean control() {
    /* Get the motion profile status every loop */
    talon.getMotionProfileStatus(status);

    /*
     * track time, this is rudimentary but that's okay, we just want to make sure things never get
     * stuck.
     */
    if (loopTimeout < 0) {
      /* do nothing, timeout is disabled */
    } else {
      /* our timeout is nonzero */
      if (loopTimeout == 0) {
      } else {
        --loopTimeout;
      }
    }

    /* first check if we are in MP mode */
    if (talon.getControlMode() != TalonControlMode.MotionProfile) {
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

            setValue = CANTalon.SetValueMotionProfile.Disable;
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
            setValue = CANTalon.SetValueMotionProfile.Enable;
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
          if (status.isUnderrun == false) {
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
            setValue = CANTalon.SetValueMotionProfile.Hold;
            state = 0;
            loopTimeout = -1;
            return true;
          }

          break;
      }
    }

    return false;
  }

  private int continueFilling(int current) {
    TrajectoryPoint tPoint = new TrajectoryPoint();

    if (status.hasUnderrun) {
      DriverStation.reportError("Talon motion profile underrun", false);

      talon.clearMotionProfileHasUnderrun();
    }

    for (int i = current; i < profile.length; i++) {
      if (status.topBufferRem == 0)
        return i;

      tPoint.position = profile[i][0];
      tPoint.velocity = profile[i][1];
      tPoint.timeDurMs = (int) profile[i][2];
      tPoint.profileSlotSelect = 0;
      tPoint.velocityOnly = false;

      tPoint.zeroPos = (i == 0);
      tPoint.isLastPoint = (i + 1) == profile.length;

      talon.pushMotionProfileTrajectory(tPoint);
    }

    return profile.length - current;
  }

  private int startFilling() {
    TrajectoryPoint tPoint = new TrajectoryPoint();

    if (status.hasUnderrun) {
      DriverStation.reportError("Talon motion profile underrun", false);

      talon.clearMotionProfileHasUnderrun();
    }

    for (int i = 0; i < profile.length; i++) {
      if (status.topBufferRem == 0)
        return i;

      tPoint.position = profile[i][0];
      tPoint.velocity = profile[i][1];
      tPoint.timeDurMs = (int) profile[i][2];
      tPoint.profileSlotSelect = 0;
      tPoint.velocityOnly = false;

      tPoint.zeroPos = (i == 0);
      tPoint.isLastPoint = (i + 1) == profile.length;

      talon.pushMotionProfileTrajectory(tPoint);
    }

    return profile.length;
  }

  public void startMotionProfile() {
    bStart = true;
  }

  public CANTalon.SetValueMotionProfile getSetValue() {
    return setValue;
  }

  public boolean isActivePointValid() {
    return status.activePointValid;
  }

  public double getTargetPosition() {
    return status.activePointValid ? status.activePoint.position : Double.NaN;
  }

  public boolean isRunning() {
    return state == 2;
  }
}
