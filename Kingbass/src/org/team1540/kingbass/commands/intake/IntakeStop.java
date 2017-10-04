package org.team1540.kingbass.commands.intake;

import org.team1540.kingbass.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Stops the intake.
 * 
 * @author Zachary Robinson
 */
public class IntakeStop extends Command {

  /** Constructs an {@link IntakeStop}. */
  public IntakeStop() {
    super("Stop intake");
    requires(Robot.intake);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void initialize() {
    Robot.intake.setMotor(0);
  }
}
