package org.team1540.kingbass.commands.claw;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.team1540.kingbass.Robot;
import org.team1540.kingbass.Tuning;

public class OpenClaw extends InstantCommand {


  public OpenClaw() {
    requires(Robot.claw);
  }

  @Override
  protected void initialize() {
    Robot.claw.setPosition(Tuning.openClawPos);
  }

}
