package org.team1540.kingbass.commands.intake;

import static org.team1540.kingbass.Robot.intake;

import org.team1540.base.ChickenCommand;

/**
 * Stops the intake.
 *
 * @author Zachary Robinson
 */
public class IntakeStop extends ChickenCommand {

  /**
   * Constructs an {@link IntakeStop}.
   */
  public IntakeStop() {
    super("Stop intake");
    addRequirement(intake);
    setPriority(5);
  }

  @Override
  protected void initialize() {
    super.initialize();
    intake.setMotor(0);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
