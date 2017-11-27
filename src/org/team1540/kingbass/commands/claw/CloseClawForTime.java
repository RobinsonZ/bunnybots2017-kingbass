package org.team1540.kingbass.commands.claw;

import static org.team1540.kingbass.Robot.claw;

import org.team1540.base.ChickenCommand;

/**
 *
 */
public class CloseClawForTime extends ChickenCommand {

  public CloseClawForTime(double timeout) {
    super(timeout);
    addRequirement(claw);
    setPriority(7.5);
  }


  protected void execute() {
    claw.startGrab();
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