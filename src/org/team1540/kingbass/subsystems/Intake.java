package org.team1540.kingbass.subsystems;

import static com.ctre.CANTalon.TalonControlMode.Follower;
import static com.ctre.CANTalon.TalonControlMode.PercentVbus;
import static org.team1540.kingbass.RobotInfo.INTAKE_A;
import static org.team1540.kingbass.RobotInfo.INTAKE_B;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.kingbass.commands.intake.IntakeStop;

/**
 * Intake for bunnies.
 *
 * @author Zachary Robinson
 */
public class Intake extends Subsystem {
  private CANTalon intakeA = new CANTalon(INTAKE_A);
  private CANTalon intakeB = new CANTalon(INTAKE_B);


  /**
   * Gets the output current of the intake motor.
   */
  public double getCurrent() {
    return (intakeA.getOutputCurrent() + intakeB.getOutputCurrent()) / 2;
  }

  /**
   * Set the intake motor's speed.
   *
   * @param setPoint The point to set the motor to, between -1 and 1 inclusive.
   */
  public void setMotor(double setPoint) {
    intakeA.changeControlMode(PercentVbus);
    intakeB.changeControlMode(Follower);
    intakeB.set(intakeA.getDeviceID());
    intakeA.set(setPoint);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new IntakeStop());
  }
}
