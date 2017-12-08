package org.team1540.kingbass.commands.intake;

import static org.team1540.kingbass.Robot.intake;
import static org.team1540.kingbass.Tuning.intakeSetPoint;
import static org.team1540.kingbass.Tuning.outputBunnyTime;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class OutputBunny extends TimedCommand {
  public OutputBunny() {
    super(outputBunnyTime);
    requires(intake);
  }

  @Override
  protected void initialize() {
    super.initialize();
    intake.setMotor(-intakeSetPoint);
  }

  @Override
  protected void end() {
    intake.setMotor(0);
  }
}
