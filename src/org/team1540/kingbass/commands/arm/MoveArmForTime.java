package org.team1540.kingbass.commands.arm;

import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team1540.kingbass.Robot;

public class MoveArmForTime extends TimedCommand {

  double moveSpeed;

  public MoveArmForTime(double timeout, double moveSpeed) {
    super(timeout);
    requires(Robot.arm);
    this.moveSpeed = moveSpeed;
  }

  protected void execute() {
    Robot.arm.setArm(moveSpeed);
  }
}