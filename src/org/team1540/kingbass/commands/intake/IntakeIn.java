package org.team1540.kingbass.commands.intake;

import static org.team1540.kingbass.Robot.intake;
import static org.team1540.kingbass.Tuning.intakeSetPoint;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs the intake inwards.
 *
 * @author Zachary Robinson
 */
public class IntakeIn extends Command {

  /**
   * Constructs an {@link IntakeIn}.
   */
  public IntakeIn() {
    super("Run Intake In");
    requires(intake);
  }

  @Override
  protected void initialize() {
    intake.setMotor(intakeSetPoint);
  }

  @Override
  protected void interrupted() {
    intake.setMotor(0);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
