package org.team1540.kingbass.subsystems;

import static com.ctre.CANTalon.TalonControlMode.MotionProfile;
import static com.ctre.CANTalon.TalonControlMode.PercentVbus;
import static com.ctre.CANTalon.TalonControlMode.Position;
import static org.team1540.kingbass.RobotInfo.L_CLAW;
import static org.team1540.kingbass.RobotInfo.R_CLAW;
import static org.team1540.kingbass.Tuning.clawBounceBack;
import static org.team1540.kingbass.Tuning.clawD;
import static org.team1540.kingbass.Tuning.clawEndPoint;
import static org.team1540.kingbass.Tuning.clawI;
import static org.team1540.kingbass.Tuning.clawP;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.kingbass.Tuning;
import org.team1540.lib.motionprofile.ProfileExecuter;

/**
 * Motorized claw.
 *
 * @author Zachary Robinson
 */
public class Claw extends Subsystem {

  private CANTalon left = new CANTalon(L_CLAW);
  private CANTalon right = new CANTalon(R_CLAW);

  private ProfileExecuter leftProfile;
  private ProfileExecuter rightProfile;
  private boolean clawIsCurrentLimited = false;
  private double clawLimit = Tuning.clawLimit;
  private boolean useLimits;


  public Claw() {
    super();
    left.enableBrakeMode(true);
    left.setInverted(true);
    right.enableBrakeMode(true);
    left.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    left.configEncoderCodesPerRev(1024);
    right.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    right.configEncoderCodesPerRev(1024);
    left.setP(clawP);
    left.setI(clawI);
    left.setD(clawD);
    right.setP(clawP);
    right.setI(clawI);
    right.setD(clawD);

    left.changeMotionControlFramePeriod(5);
    right.changeMotionControlFramePeriod(5);
  }

  public void setMp(double[][] lPts, double[][] rPts) {
    leftProfile = new ProfileExecuter(left, lPts, lPts.length);
    rightProfile = new ProfileExecuter(right, rPts, rPts.length);
  }

  @SuppressWarnings("Duplicates")
  public boolean controlMp() {
    if (leftProfile != null && rightProfile != null) {
      boolean done = leftProfile.control();
      left.changeControlMode(MotionProfile);
      left.set(leftProfile.getSetValue().value);

      done = (done || rightProfile.control());
      right.changeControlMode(MotionProfile);
      right.set(rightProfile.getSetValue().value);

      return done;
    }

    return false;
  }

  public void setPID(double p, double i, double d) {
    left.setPID(p, i, d);
    right.setPID(p, i, d);
  }

  public void startMp() {
    if (leftProfile != null && rightProfile != null) {
      leftProfile.startMotionProfile();
      rightProfile.startMotionProfile();
    }
  }

  @SuppressWarnings("Duplicates")
  public void stopMp() {
    if (leftProfile != null && rightProfile != null) {
      leftProfile.reset();
      left.changeControlMode(PercentVbus);
      left.set(0);

      rightProfile.reset();
      right.changeControlMode(PercentVbus);
      right.set(0);
    }
  }

  /**
   * Closes the claw.
   */
  public void startGrab() {
    setTalonsToVbusMode();
    left.set(1);
    right.set(1);
  }

  public void stop() {
    setTalonsToVbusMode();
    left.set(0);
    right.set(0);
  }

  public void set(double value) {
    setTalonsToVbusMode();
    left.set(value);
    right.set(value);
  }

  public double setPosition(double pos) {
    if (!clawIsCurrentLimited) {
      if (useLimits) {
        pos = pos < clawLimit ? pos : clawLimit - clawBounceBack;
        pos = pos >= clawEndPoint ? pos : clawEndPoint + clawBounceBack;
      }
      setTalonsToPositionMode();
      left.set(pos);
      right.set(-pos);
    }
    return pos;
  }

  public double getPosition() {
    return left.getPosition(); //might change to average later
  }

  public double getLeftPosition() {
    return left.getPosition();
  }

  public double getRightPosition() {
    return right.getPosition();
  }

  public void zeroPosition() {
    left.setPosition(0);
    right.setPosition(0);
  }

  @Override
  protected void initDefaultCommand() {
    //setDefaultCommand(new TriggerClawControl());
  }

  private void setTalonsToPositionMode() {
    left.changeControlMode(Position);
    right.changeControlMode(Position);
  }

  private void setTalonsToVbusMode() {
    left.changeControlMode(PercentVbus);
    right.changeControlMode(PercentVbus);
  }

  public void updatePIDs() {
    left.setPID(clawP, clawI, clawD);
    right.setPID(clawP, clawI, clawD);
  }

  public void setUseLimits(boolean useLimits) {
    this.useLimits = useLimits;
  }
}
