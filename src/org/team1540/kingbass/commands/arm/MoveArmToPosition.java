package org.team1540.kingbass.commands.arm;

import static org.team1540.kingbass.Robot.arm;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class MoveArmToPosition extends InstantCommand {
  private double position;

  public MoveArmToPosition(double position) {
    requires(arm);
    this.position = position;
  }

  @Override
  protected void initialize() {
    arm.setPosition(position);
  }
}