package org.team1540.kingbass.commands.claw;

import static org.team1540.kingbass.Robot.claw;

import org.team1540.base.ChickenCommand;

/**
 * Opens the pneumatic claw.
 *
 * @author Zachary Robinson
 */
public class OpenClaw extends ChickenCommand {

  /**
   * Constructs an {@link OpenClaw}.
   */
  public OpenClaw() {
    super("Open claw");
    addRequirement(claw);
    setPriority(7.5);
  }

  @Override
  protected void end() {
    super.end();
    claw.stop();
  }

  @Override
  protected void initialize() {
    super.initialize();
    claw.startRelease();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
