package org.team1540.kingbass.commands.auto;

import static org.team1540.kingbass.Robot.driveTrain;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class DriveForward extends TimedCommand {

  public DriveForward(double timeout) {
    super("Drive forward " + timeout + " sec", timeout);
    requires(driveTrain);
  }

  @Override
  protected void end() {
    driveTrain.setLeftMotors(0);
    driveTrain.setRightMotors(0);
  }

  @Override
  protected void initialize() {
    driveTrain.setLeftMotors(1);
    driveTrain.setRightMotors(1);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
