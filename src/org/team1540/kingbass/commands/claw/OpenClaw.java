package org.team1540.kingbass.commands.claw;

import static org.team1540.kingbass.Robot.claw;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Opens the pneumatic claw.
 *
 * @author Zachary Robinson
 */
public class OpenClaw extends Command {

  /**
   * Constructs an {@link OpenClaw}.
   */
  public OpenClaw() {
    super("Open claw");
    requires(claw);
  }

  @Override
  protected void end() {
    claw.stop();
  }

  @Override
  protected void initialize() {
    claw.startRelease();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
