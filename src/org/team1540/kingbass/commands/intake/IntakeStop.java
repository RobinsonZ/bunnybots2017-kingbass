package org.team1540.kingbass.commands.intake;

import static org.team1540.kingbass.Robot.intake;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Stops the intake.
 *
 * @author Zachary Robinson
 */
public class IntakeStop extends InstantCommand {

  /**
   * Constructs an {@link IntakeStop}.
   */
  public IntakeStop() {
    super("Stop intake");
    requires(intake);
  }

  @Override
  protected void initialize() {
    intake.setMotor(0);
  }
}
