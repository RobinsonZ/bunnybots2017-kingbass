package org.team1540.kingbass.commands.arm;

import static org.team1540.kingbass.Robot.arm;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ZeroArmPosition extends InstantCommand {

  public ZeroArmPosition() {
    // this makes it stop the arm control for a cycle.
    // if this wasn't here and you zeroed it in teleop the arm would immediately yeet to
    // a position a while away from where it actually should be
    requires(arm);
    setRunWhenDisabled(true);
  }

  @Override
  protected void execute() {
    arm.zeroPosition();
  }
}
