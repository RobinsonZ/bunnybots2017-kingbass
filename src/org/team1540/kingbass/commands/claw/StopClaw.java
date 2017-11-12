package org.team1540.kingbass.commands.claw;

import static org.team1540.kingbass.Robot.claw;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class StopClaw extends InstantCommand {
  public StopClaw() {
    super("Stop claw");

    requires(claw);
  }

  @Override
  protected void initialize() {
    claw.stop();
  }
}
