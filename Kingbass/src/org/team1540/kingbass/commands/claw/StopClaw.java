package org.team1540.kingbass.commands.claw;

import org.team1540.kingbass.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class StopClaw extends Command {
  public StopClaw() {
    super("Stop claw");

    requires(Robot.claw);
  }

  @Override
  protected void initialize() {
    Robot.claw.stop();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

}
