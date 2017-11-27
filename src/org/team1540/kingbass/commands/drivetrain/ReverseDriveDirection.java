package org.team1540.kingbass.commands.drivetrain;

import static org.team1540.kingbass.Robot.driveTrain;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ReverseDriveDirection extends InstantCommand {
  // Doesn't extend ChickenCommand because it requires no power
  @Override
  protected void execute() {
    driveTrain.switchDriveDirection();
  }
}
