package org.team1540.kingbass.subsystems;

import static com.ctre.CANTalon.TalonControlMode.PercentVbus;
import static org.team1540.kingbass.RobotInfo.INTAKE_A;

import com.ctre.CANTalon;
import org.team1540.base.ChickenSubsystem;
import org.team1540.kingbass.commands.intake.IntakeStop;

/**
 * Intake for bunnies.
 *
 * @author Zachary Robinson
 */
public class Intake extends ChickenSubsystem {
  private CANTalon intake = new CANTalon(INTAKE_A);

  @Override
  public double getCurrent() {
    return intake.getOutputCurrent();
  }

  @Override
  public void setAbsolutePowerLimit(double limit) {
    intake.setCurrentLimit((int) limit);
    intake.EnableCurrentLimit(true);
  }

  @Override
  public void stopLimitingPower() {
    intake.EnableCurrentLimit(false);
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

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new IntakeStop());
  }
}
