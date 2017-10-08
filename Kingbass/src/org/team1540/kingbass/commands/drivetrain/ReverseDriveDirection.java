package org.team1540.kingbass.commands.drivetrain;

import org.team1540.kingbass.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ReverseDriveDirection extends Command {

  @Override
  protected boolean isFinished() {
    return true;
  }
  
  @Override
  protected void initialize() {
    Robot.driveTrain.switchDriveDirection();
  }

}
