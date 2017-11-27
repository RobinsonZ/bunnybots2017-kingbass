package org.team1540.kingbass.commands;

import static org.team1540.kingbass.Robot.arm;
import static org.team1540.kingbass.Robot.claw;
import static org.team1540.kingbass.Robot.driveTrain;
import static org.team1540.kingbass.Robot.intake;

import org.team1540.base.ChickenCommand;

public class StopEverything extends ChickenCommand {

  public StopEverything() {
    addRequirement(arm);
    addRequirement(claw);
    addRequirement(driveTrain);
    addRequirement(intake);
    setPriority(15);
  }

  @Override
  protected void initialize() {
    arm.stopArm();
    claw.stop();
    driveTrain.setLeftMotors(0);
    driveTrain.setRightMotors(0);
    intake.setMotor(0);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
