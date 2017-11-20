package org.team1540.kingbass.commands.claw;

import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team1540.kingbass.Robot;

/**
 *
 */
public class OpenClawForTime extends TimedCommand {

  public OpenClawForTime(double timeout) {
    super(timeout);
    requires(Robot.claw);
  }

  // Called once when the command executes
  protected void execute() {
    Robot.claw.startRelease();
  }

  @Override
  protected void end() {
    Robot.claw.stop();
  }
}
