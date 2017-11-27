package org.team1540.kingbass.commands.claw;

import static org.team1540.kingbass.Robot.claw;

import org.team1540.base.ChickenCommand;

public class StopClaw extends ChickenCommand {
  public StopClaw() {
    super("Stop claw");

    addRequirement(claw);
    setPriority(7.5);
  }

  @Override
  protected void initialize() {
    super.initialize();
    claw.stop();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
