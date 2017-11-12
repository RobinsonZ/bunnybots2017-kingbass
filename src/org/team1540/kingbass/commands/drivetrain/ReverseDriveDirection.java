package org.team1540.kingbass.commands.drivetrain;

import static org.team1540.kingbass.Robot.driveTrain;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ReverseDriveDirection extends InstantCommand {
  @Override
  protected void execute() {
    driveTrain.switchDriveDirection();
  }
}
