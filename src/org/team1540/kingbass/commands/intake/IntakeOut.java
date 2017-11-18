package org.team1540.kingbass.commands.intake;

import static org.team1540.kingbass.Robot.intake;
import static org.team1540.kingbass.Tuning.intakeSetPoint;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs the intake outwards.
 *
 * @author Zachary Robinson
 */
public class IntakeOut extends Command {

  /**
   * Constructs an {@link IntakeOut}.
   */
  public IntakeOut() {
    super("Run Intake Out");
    requires(intake);
  }

  @Override
  protected void initialize() {
    intake.setMotor(-intakeSetPoint);
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
