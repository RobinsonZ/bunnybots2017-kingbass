package org.team1540.kingbass.commands.auto;

import static org.team1540.kingbass.Robot.driveTrain;

import org.team1540.base.ChickenCommand;


public class Turn extends ChickenCommand {

  private int turnLeftSpeed;
  private int turnRightSpeed;

  public Turn(double timeout, int turnLeftSpeed, int turnRightSpeed) {
    super(timeout);
    this.turnLeftSpeed = turnLeftSpeed;
    this.turnRightSpeed = turnRightSpeed;
    addRequirement(driveTrain);
    setPriority(10);
  }

  protected void execute() {
    driveTrain.setLeftMotors(turnLeftSpeed);
    driveTrain.setRightMotors(turnRightSpeed);
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }
}
