package org.team1540.kingbass.commands.auto;

import static org.team1540.kingbass.Robot.driveTrain;

import org.team1540.base.ChickenCommand;

public class DriveForward extends ChickenCommand {
  private double driveSpeed;

  public DriveForward(double timeout) {
    this(timeout, 1);

  }
  public DriveForward(double timeout, double driveSpeed) {
    super("Drive forward " + timeout + " sec", timeout);
    addRequirement(driveTrain);
    setPriority(10);

    this.driveSpeed = driveSpeed;
  }

  @Override
  protected void end() {
    super.end();
    driveTrain.setLeftMotors(0);
    driveTrain.setRightMotors(0);
  }

  @Override
  protected void initialize() {
    super.initialize();
    driveTrain.setLeftMotors(driveSpeed);
    driveTrain.setRightMotors(driveSpeed);
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
