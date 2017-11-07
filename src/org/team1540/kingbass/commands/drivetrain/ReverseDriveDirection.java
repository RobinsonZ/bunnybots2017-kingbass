package org.team1540.kingbass.commands.drivetrain;

import org.team1540.kingbass.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class ReverseDriveDirection extends InstantCommand {
  @Override
  protected void execute() {
    Robot.driveTrain.switchDriveDirection();
  }
}
