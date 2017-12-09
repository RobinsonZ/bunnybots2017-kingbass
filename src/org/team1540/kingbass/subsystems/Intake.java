package org.team1540.kingbass.subsystems;

import static com.ctre.CANTalon.TalonControlMode.PercentVbus;
import static org.team1540.kingbass.RobotInfo.INTAKE_A;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Intake for bunnies.
 *
 * @author Zachary Robinson
 */
public class Intake extends Subsystem {
  private CANTalon intake = new CANTalon(INTAKE_A);

  public Intake() {
    intake.enableBrakeMode(true);
    intake.setInverted(true);
  }

  @Override
  protected void initDefaultCommand() {}

  /**
   * Gets the output current of the intake motor.
   */
  public double getCurrent() {
    return intake.getOutputCurrent();
  }

  /**
   * Set the intake motor's speed.
   *
   * @param setPoint The point to set the motor to, between -1 and 1 inclusive.
   */
  public void setMotor(double setPoint) {
    intake.changeControlMode(PercentVbus);
    intake.set(setPoint);
  }
}
