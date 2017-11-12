package org.team1540.kingbass.commands.claw;

import static org.team1540.kingbass.Robot.claw;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Closes the pneumatic claw.
 *
 * @author Zachary Robinson
 */
public class CloseClaw extends Command {
  Timer t = new Timer();

  /**
   * Constructs a {@link CloseClaw}.
   */
  public CloseClaw() {
    super("Close claw");
    requires(claw);
  }

  @Override
  protected void end() {
    claw.stop();
  }

  @Override
  protected void initialize() {
    claw.startGrab();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
