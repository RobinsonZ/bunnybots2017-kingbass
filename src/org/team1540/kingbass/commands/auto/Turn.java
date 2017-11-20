package org.team1540.kingbass.commands.auto;

import static org.team1540.kingbass.Robot.driveTrain;

import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team1540.kingbass.Robot;


public class Turn extends TimedCommand {

  private int turnLeftSpeed;
  private int turnRightSpeed;

  public Turn(double timeout, int turnLeftSpeed, int turnRightSpeed) {
    super(timeout);
    this.turnLeftSpeed = turnLeftSpeed;
    this.turnRightSpeed = turnRightSpeed;
    requires(driveTrain);
  }

  protected void execute() {
    driveTrain.setLeftMotors(turnLeftSpeed);
    driveTrain.setRightMotors(turnRightSpeed);
  }
}
