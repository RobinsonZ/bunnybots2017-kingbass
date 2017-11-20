package org.team1540.kingbass.commands.auto;

import static org.team1540.kingbass.Robot.driveTrain;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class DriveForward extends TimedCommand {
  private double driveSpeed;

  public DriveForward(double timeout) {
    this(timeout, 1);

  }
  public DriveForward(double timeout, double driveSpeed) {
    super("Drive forward " + timeout + " sec", timeout);
    requires(driveTrain);
    this.driveSpeed = driveSpeed;
  }

  @Override
  protected void end() {
    driveTrain.setLeftMotors(0);
    driveTrain.setRightMotors(0);
  }

  @Override
  protected void initialize() {
    driveTrain.setLeftMotors(driveSpeed);
    driveTrain.setRightMotors(driveSpeed);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
