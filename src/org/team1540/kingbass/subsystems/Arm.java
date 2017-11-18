package org.team1540.kingbass.subsystems;

import static com.ctre.CANTalon.TalonControlMode.Follower;
import static org.team1540.kingbass.OI.ARM_AXIS;
import static org.team1540.kingbass.OI.ARM_AXIS_2;
import static org.team1540.kingbass.OI.ARM_JOYSTICK;
import static org.team1540.kingbass.RobotInfo.ARM_A;
import static org.team1540.kingbass.Tuning.armSpeed;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.kingbass.OI;
import org.team1540.kingbass.commands.arm.JoystickArmControl;

/**
 * Motorized arm.
 *
 * @author Zachary Robinson
 */
public class Arm extends Subsystem {
  private CANTalon armA = new CANTalon(ARM_A);
  private CANTalon armB = new CANTalon(ARM_A);

  /**
   * Constructs an {@link Arm}.
   */
  public Arm() {
    super();
    armB.changeControlMode(Follower);
    armB.set(armA.getDeviceID());
    armA.enableBrakeMode(true);
    armB.enableBrakeMode(true);
  }

  /**
   * Lowers the arm at the speed set by {@code Tuning.ARM_SPEED}.
   */
  public void lowerArm() {
    armA.set(armSpeed);
  }

  /**
   * Raises the arm at the speed set by {@code Tuning.ARM_SPEED}.
   */
  public void raiseArm() {
    armB.set(armSpeed);
  }

  /**
   * Sets the arm to a given speed.
   *
   * @param setPoint The speed of the arm motors, from -1 to 1 inclusive.
   */
  public void setArm(double setPoint) {
    armA.set(setPoint);
  }

  /**
   * Stops the arm.
   */
  public void stopArm() {
    armA.set(0);
  }

  @Override
  protected void initDefaultCommand() {
    if (OI.TRIGGERS) {
      setDefaultCommand(new JoystickArmControl(ARM_JOYSTICK, ARM_AXIS));
    } else {
      setDefaultCommand(new JoystickArmControl(ARM_JOYSTICK, ARM_AXIS, ARM_AXIS_2));
    }
  }
}
