package org.team1540.kingbass.commands.claw;

import static org.team1540.base.Utilities.processAxisDeadzone;
import static org.team1540.kingbass.OI.getCopilotLeftTrigger;
import static org.team1540.kingbass.OI.getCopilotRightTrigger;
import static org.team1540.kingbass.Robot.claw;
import static org.team1540.kingbass.Tuning.deadzone;

import edu.wpi.first.wpilibj.command.Command;

public class TriggerClawControl extends Command {

  public TriggerClawControl() {
    super("Claw control");
    requires(claw);
  }

  @Override
  protected void execute() {
    claw.set(processAxisDeadzone(getCopilotRightTrigger(),
        deadzone) - processAxisDeadzone(getCopilotLeftTrigger(), deadzone));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
