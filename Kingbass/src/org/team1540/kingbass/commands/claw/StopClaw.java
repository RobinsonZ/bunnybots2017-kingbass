package org.team1540.kingbass.commands.claw;

import org.team1540.kingbass.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

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
