package org.team1540.kingbass.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.kingbass.RobotInfo;
import org.team1540.kingbass.commands.intake.IntakeStop;

/**
 * Intake for bunnies.
 *
 * @author Zachary Robinson
 */
public class Intake extends Subsystem {
  private CANTalon intakeA = new CANTalon(RobotInfo.INTAKE_A);
  private CANTalon intakeB = new CANTalon(RobotInfo.INTAKE_B);


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
    intakeA.changeControlMode(TalonControlMode.PercentVbus);
    intakeB.changeControlMode(TalonControlMode.Follower);
    intakeB.set(intakeA.getDeviceID());
    intakeA.set(setPoint);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new IntakeStop());
  }
}
