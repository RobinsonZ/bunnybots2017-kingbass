package org.team1540.kingbass.commands.arm;

import static java.lang.Math.abs;
import static java.lang.Math.signum;
import static org.team1540.kingbass.Robot.arm;
import static org.team1540.kingbass.Tuning.armEndThreshold;
import static org.team1540.kingbass.Tuning.armMult;

import org.team1540.base.ChickenCommand;

public class MoveArmToPosition extends ChickenCommand {
  private double endPosition;
  private double position;


  public MoveArmToPosition(double endPosition) {
    addRequirement(arm);
    setPriority(2.5);
    this.endPosition = endPosition;
  }

  @Override
  protected void initialize() {
    super.initialize();
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