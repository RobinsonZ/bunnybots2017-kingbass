package org.team1540.kingbass.commands.arm;

import static java.lang.Math.abs;
import static java.lang.Math.signum;
import static org.team1540.kingbass.Robot.arm;
import static org.team1540.kingbass.Tuning.armEndThreshold;
import static org.team1540.kingbass.Tuning.armMult;

import edu.wpi.first.wpilibj.command.Command;

public class MoveArmToPosition extends Command {
  private double endPosition;
  private double position;


  public MoveArmToPosition(double endPosition) {
    requires(arm);
    this.endPosition = endPosition;
  }

  @Override
  protected void initialize() {
    position = arm.getPosition();
  }

  @Override
  protected void execute() {
    position += (signum(endPosition - position) * armMult);
    arm.setPosition(position);
  }

  @Override
  protected boolean isFinished() {
    return abs(arm.getPosition() - this.endPosition) < armEndThreshold;
  }
}