package org.team1540.kingbass.commands.arm;

import static org.team1540.kingbass.Robot.arm;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ZeroArmPosition extends InstantCommand {

  public ZeroArmPosition() {
    setRunWhenDisabled(true);
  }

  @Override
  protected void execute() {
    arm.zeroPosition();
  }
}
