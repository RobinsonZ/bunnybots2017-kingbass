package org.team1540.kingbass.commands.claw;

import static org.team1540.kingbass.Robot.claw;

import org.team1540.base.ChickenCommand;

/**
 *
 */
public class OpenClawForTime extends ChickenCommand {

  public OpenClawForTime(double timeout) {
    super(timeout);
    addRequirement(claw);
    setPriority(7.5);
  }

  // Called once when the command executes
  protected void execute() {
    claw.startRelease();
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    super.end();
    claw.stop();
  }
}
