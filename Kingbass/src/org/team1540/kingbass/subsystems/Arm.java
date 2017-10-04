package org.team1540.kingbass.subsystems;

import org.team1540.kingbass.OI;
import org.team1540.kingbass.RobotInfo;
import org.team1540.kingbass.Tuning;
import org.team1540.kingbass.commands.arm.JoystickArmControl;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Motorized arm.
 * 
 * @author Zachary Robinson
 *
 */
public class Arm extends Subsystem {
  private CANTalon armA = new CANTalon(RobotInfo.ARM_A);
  private CANTalon armB = new CANTalon(RobotInfo.ARM_A);

  @Override
  protected void initDefaultCommand() {
    if (OI.TRIGGERS) {
      setDefaultCommand(new JoystickArmControl(OI.ARM_JOYSTICK, OI.ARM_AXIS));
    } else {
      setDefaultCommand(new JoystickArmControl(OI.ARM_JOYSTICK, OI.ARM_AXIS, OI.ARM_AXIS_2));
    }
  }

  /** Constructs an {@link Arm}. */
  public Arm() {
    super();
    armB.changeControlMode(TalonControlMode.Follower);
    armB.set(armA.getDeviceID());
    armA.enableBrakeMode(true);
    armB.enableBrakeMode(true);
  }

  /** Raises the arm at the speed set by {@code Tuning.ARM_SPEED}. */
  public void raiseArm() {
    armB.set(Tuning.getArmSpeed());
  }

  /** Lowers the arm at the speed set by {@code Tuning.ARM_SPEED}. */
  public void lowerArm() {
    armA.set(Tuning.getArmSpeed());
  }

  /** Stops the arm. */
  public void stopArm() {
    armA.set(0);
  }

  /**
   * Sets the arm to a given speed.
   * 
   * @param setPoint The speed of the arm motors, from -1 to 1 inclusive.
   */
  public void setArm(double setPoint) {
    armA.set(setPoint);
  }

}
