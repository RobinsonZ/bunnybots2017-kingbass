package org.team1540.kingbass.commands.claw;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.team1540.kingbass.Robot;

public class StopClaw extends InstantCommand {
  public StopClaw() {
    super("Stop claw");

    requires(Robot.claw);
  }

  @Override
  protected void initialize() {
    Robot.claw.stop();
  }
}
