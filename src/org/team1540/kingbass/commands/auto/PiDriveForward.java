package org.team1540.kingbass.commands.auto;

import static org.team1540.kingbass.Robot.driveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class PiDriveForward extends Command {
  private double dist;
  private double stopTolerance;

  public PiDriveForward(double dist, double stopTolerance) {
    this.dist = dist;
    this.stopTolerance = stopTolerance;
  }

  @Override
  protected boolean isFinished() {
    return driveTrain.getLeftError() <= stopTolerance
        && driveTrain.getRightError() <= stopTolerance;
  }

  @Override
  protected void initialize() {
    driveTrain.updatePIDs();
    driveTrain.setPosition(dist, dist);
  }

  @Override
  protected void end() {
    driveTrain.setLeftMotors(0);
    driveTrain.setRightMotors(0);
  }
}
