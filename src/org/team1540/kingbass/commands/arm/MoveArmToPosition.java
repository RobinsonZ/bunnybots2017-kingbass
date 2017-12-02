package org.team1540.kingbass.commands.arm;

import static org.team1540.kingbass.Robot.arm;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.Tuning;

public class MoveArmToPosition extends Command {
  private double endPosition;
  private double position;



  public MoveArmToPosition(double endPosition) {
    requires(arm);
    this.position = position;
  }

  @Override
  protected void initialize() {
    position = arm.getPosition();
  }

  protected void execute() {
    arm.setPosition((endPosition-position)*Tuning.armMult);
  }

  @Override
  protected boolean isFinished() {
    return (Math.abs(arm.getPosition()-this.endPosition) < Tuning.armEndThreshold);
  }
}