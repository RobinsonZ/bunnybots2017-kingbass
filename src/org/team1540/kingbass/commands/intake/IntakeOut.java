package org.team1540.kingbass.commands.intake;

import static org.team1540.kingbass.Robot.intake;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.Tuning;

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
    intake.setMotor(-Tuning.getIntakeSetPoint());
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
