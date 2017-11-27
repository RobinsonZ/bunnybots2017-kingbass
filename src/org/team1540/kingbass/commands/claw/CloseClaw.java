package org.team1540.kingbass.commands.claw;

import static org.team1540.kingbass.Robot.claw;

import org.team1540.base.ChickenCommand;

/**
 * Closes the pneumatic claw.
 *
 * @author Zachary Robinson
 */
public class CloseClaw extends ChickenCommand {
  /**
   * Constructs a {@link CloseClaw}.
   */
  public CloseClaw() {
    super("Close claw");
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
    claw.startGrab();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
