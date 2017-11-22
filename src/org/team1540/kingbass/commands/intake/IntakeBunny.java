package org.team1540.kingbass.commands.intake;

import static org.team1540.kingbass.Robot.intake;
import static org.team1540.kingbass.Tuning.intakeStopThresh;
import static org.team1540.kingbass.Tuning.intakeTimeout;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.OI;
import org.team1540.kingbass.commands.controller.VibrateController;

public class IntakeBunny extends Command {

  public IntakeBunny() {
    super("Intake Bunny", intakeTimeout);
  }

  @Override
  protected boolean isFinished() {
    return intake.getCurrent() >= intakeStopThresh;
  }

  @Override
  protected void initialize() {
    intake.setMotor(1);
  }

  @Override
  protected void end() {
    intake.setMotor(0);
    new VibrateController(.25, OI.driver).start();
  }
}
