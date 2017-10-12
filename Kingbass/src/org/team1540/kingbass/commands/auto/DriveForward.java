package org.team1540.kingbass.commands.auto;

import org.team1540.kingbass.Robot;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class DriveForward extends TimedCommand {

  public DriveForward(double timeout) {
    super("Drive forward " + timeout + " sec", timeout);
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() {
    Robot.driveTrain.setLeftMotors(1);
    Robot.driveTrain.setRightMotors(1);
  }

  @Override
  protected void end() {
    Robot.driveTrain.setLeftMotors(0);
    Robot.driveTrain.setRightMotors(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
