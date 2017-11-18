package org.team1540.kingbass.commands.intake;

import static org.team1540.kingbass.Robot.intake;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.Tuning;

public class IntakeBunny extends Command {

  public IntakeBunny() {
    super("Intake Bunny", Tuning.intakeTimeout);
  }

  @Override
  protected boolean isFinished() {
    return intake.getCurrent() >= Tuning.intakeStopThresh;
  }

  @Override
  protected void initialize() {
    intake.setMotor(1);
  }

  @Override
  protected void end() {
    intake.setMotor(0);
  }
}
