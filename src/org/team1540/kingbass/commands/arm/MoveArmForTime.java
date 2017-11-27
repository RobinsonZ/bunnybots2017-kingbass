package org.team1540.kingbass.commands.arm;

import static org.team1540.kingbass.Robot.arm;

import org.team1540.base.ChickenCommand;

public class MoveArmForTime extends ChickenCommand {

  double moveSpeed;

  public MoveArmForTime(double timeout, double moveSpeed) {
    super(timeout);

    addRequirement(arm);
    setPriority(2.5);

    this.moveSpeed = moveSpeed;
  }

  protected void execute() {
    arm.setArm(moveSpeed);
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }
}