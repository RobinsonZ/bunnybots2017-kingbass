package org.team1540.kingbass.commands.claw;

import static java.lang.Math.abs;
import static java.lang.Math.signum;
import static org.team1540.kingbass.Robot.claw;
import static org.team1540.kingbass.Tuning.clawMult;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.Tuning;

public class MoveClawToPosition extends Command {
  private double endPosition;
  private double position;


  public MoveClawToPosition(double endPosition) {
    requires(claw);
    this.endPosition = endPosition;
  }

  @Override
  protected void initialize() {
    position = claw.getPosition();
  }

  @Override
  protected void execute() {
    position += (signum(endPosition - position) * clawMult);
    claw.setPosition(position);
  }

  @Override
  protected boolean isFinished() {
    return abs(claw.getPosition() - this.endPosition) < Tuning.clawEndThreshold;
  }
}