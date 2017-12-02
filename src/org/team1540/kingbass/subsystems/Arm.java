package org.team1540.kingbass.subsystems;

import static com.ctre.CANTalon.TalonControlMode.Follower;
import static com.ctre.CANTalon.TalonControlMode.PercentVbus;
import static com.ctre.CANTalon.TalonControlMode.Position;
import static org.team1540.kingbass.OI.ARM_AXIS;
import static org.team1540.kingbass.OI.ARM_JOYSTICK;
import static org.team1540.kingbass.RobotInfo.ARM_A;
import static org.team1540.kingbass.RobotInfo.ARM_B;
import static org.team1540.kingbass.Tuning.armBounceBack;
import static org.team1540.kingbass.Tuning.armD;
import static org.team1540.kingbass.Tuning.armI;
import static org.team1540.kingbass.Tuning.armLimit;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.kingbass.Tuning;
import org.team1540.kingbass.commands.arm.JoystickArmControl;

/**
 * Motorized arm.
 *
 * @author Zachary Robinson
 */
public class Arm extends Subsystem {
  private Notifier powerLimiterNotifier = new Notifier(new Runnable() {
    @Override
    public void run() {
      armIsCurrentLimited = armA.getOutputCurrent() > Tuning.armCurrLimThresh
          || armB.getOutputCurrent() > Tuning.armCurrLimThresh;
      if (armIsCurrentLimited) {
        // This is synchronized so we don't have, for example, the arm's control mode being set to
        // vbus and then getting passed a value from setPos()
        synchronized (talonLock) {
          armA.changeControlMode(PercentVbus);
          armA.set(0);
          armB.changeControlMode(PercentVbus);
          armB.set(0);
        }
      }
    }
  });

  private boolean armIsCurrentLimited = false;
  private final CANTalon armA = new CANTalon(ARM_A);
  private final CANTalon armB = new CANTalon(ARM_B);
  private final Object talonLock = new Object();

  /**
   * Constructs an {@link Arm}.
   */
  public Arm() {
    super();
    armA.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    armA.configEncoderCodesPerRev(1024);
    armA.enableBrakeMode(true);
    armA.reverseOutput(true);
    armB.enableBrakeMode(true);
    armB.reverseOutput(true);
    armA.setP(Tuning.armP);
    armA.setI(armI);
    armA.setD(armD);
    powerLimiterNotifier.startPeriodic(0.05);
  }

  /**
   * Sets the arm to a given speed.
   *
   * @param setPoint The speed of the arm motors, from -1 to 1 inclusive.
   */
  public void setArm(double setPoint) {
    if (!armIsCurrentLimited) {
      synchronized (talonLock) {
        setTalonsToVbusMode();
        armA.set(setPoint);
      }
    }
  }

  /**
   * Stops the arm.
   */
  public void stopArm() {
    synchronized (talonLock) {
      setTalonsToVbusMode();
      armA.set(0);
    }
  }

  public double setPosition(double position) {
    boolean atLim = false;
    if (!armIsCurrentLimited) {
      synchronized (talonLock) {
        atLim = position >= armLimit || position < 0;
        position = position < armLimit ? position : armLimit - armBounceBack;
        position = position >= 0 ? position : 0 + armBounceBack;
        setTalonsToPositionMode();
        armA.set(position);
      }
    }
    return position;
  }

  public void zeroPosition() {
    synchronized (talonLock) {
      armA.setPosition(0);
    }
  }

  public double getPositionA() {
    return armA.getPosition();
  }

  public double getCurrentA() {
    return armA.getOutputCurrent();
  }

  public double getCurrentB() {
    return armB.getOutputCurrent();
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new JoystickArmControl(ARM_JOYSTICK, ARM_AXIS));
  }

  // Following two methods should only be called in a "synchronized (talonLock) {}" block.
  private void setTalonsToVbusMode() {
    armA.changeControlMode(PercentVbus);
    armB.changeControlMode(Follower);
    armB.set(armA.getDeviceID());
  }

  private void setTalonsToPositionMode() {
    armA.changeControlMode(Position);
    armB.changeControlMode(Follower);
    armB.set(armA.getDeviceID());
  }

  public boolean isCurrentLimited() {
    return armIsCurrentLimited;
  }

  public void updatePIDs() {
    armA.setPID(Tuning.armP, armI, armD);
  }
}
