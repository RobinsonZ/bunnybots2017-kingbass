package org.team1540.kingbass.commands.intake;

import static org.team1540.kingbass.Robot.intake;
import static org.team1540.kingbass.Tuning.intakeSetPoint;

import org.team1540.base.ChickenCommand;

/**
 * Runs the intake outwards.
 *
 * @author Zachary Robinson
 */
public class IntakeOut extends ChickenCommand {

  /**
   * Constructs an {@link IntakeOut}.
   */
  public IntakeOut() {
    super("Run Intake Out");
    addRequirement(intake);
    setPriority(5);
  }

  @Override
  protected void initialize() {
    super.initialize();
    intake.setMotor(-intakeSetPoint);
  }

  @Override
  protected void end() {
    super.end();
    intake.setMotor(0);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
