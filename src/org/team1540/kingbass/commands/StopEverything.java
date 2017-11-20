package org.team1540.kingbass.commands;

import static org.team1540.kingbass.Robot.arm;
import static org.team1540.kingbass.Robot.claw;
import static org.team1540.kingbass.Robot.driveTrain;
import static org.team1540.kingbass.Robot.intake;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class StopEverything extends InstantCommand {

  public StopEverything() {
    requires(arm);
    requires(claw);
    requires(driveTrain);
    requires(intake);
  }

  @Override
  protected void initialize() {
    arm.stopArm();
    claw.stop();
    driveTrain.setLeftMotors(0);
    driveTrain.setRightMotors(0);
    intake.setMotor(0);
  }
}
