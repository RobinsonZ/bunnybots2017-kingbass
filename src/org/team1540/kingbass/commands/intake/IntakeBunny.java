package org.team1540.kingbass.commands.intake;

import static org.team1540.kingbass.Robot.intake;
import static org.team1540.kingbass.Tuning.intakeSetPoint;
import static org.team1540.kingbass.Tuning.intakeStopThresh;
import static org.team1540.kingbass.Tuning.intakeTimeout;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.base.ChickenCommand;
import org.team1540.kingbass.OI;
import org.team1540.kingbass.Tuning;
import org.team1540.kingbass.commands.controller.VibrateController;

public class IntakeBunny extends ChickenCommand {
  public IntakeBunny() {
    super("Intake Bunny", intakeTimeout);
    addRequirement(intake);
    setPriority(5);
  }

  Command vibrateControllerCmd = new VibrateController(.25, OI.copilot);

  @Override
  protected boolean isFinished() {
    return (intake.getCurrent() >= intakeStopThresh
        && timeSinceInitialized() > Tuning.intakeMinTime) || isTimedOut();
  }

  @Override
  protected void initialize() {
    super.initialize();
    intake.setMotor(intakeSetPoint);
  }

  @Override
  protected void end() {
    intake.setMotor(0);
    if (intake.getCurrent() >= intakeStopThresh) {
      vibrateControllerCmd.start();
    }
    super.end();
  }
}
