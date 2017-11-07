package org.team1540.kingbass.commands.intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.team1540.kingbass.Robot;

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
    requires(Robot.intake);
  }

  @Override
  protected void initialize() {
    Robot.intake.setMotor(0);
  }
}
