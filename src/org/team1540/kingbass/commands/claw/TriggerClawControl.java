package org.team1540.kingbass.commands.claw;

import static org.team1540.base.Utilities.processAxisDeadzone;
import static org.team1540.kingbass.OI.getCopilotLeftTrigger;
import static org.team1540.kingbass.OI.getCopilotRightTrigger;
import static org.team1540.kingbass.Robot.claw;
import static org.team1540.kingbass.Tuning.deadzone;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.Tuning;

public class TriggerClawControl extends Command {
  private double position;

  public TriggerClawControl() {
    super("Claw control");
    requires(claw);
  }

  @Override
  protected void initialize() {
    super.initialize(); // here for later power mgmt compatibility
    position = claw.getPosition();
  }

  @Override
  protected void execute() {
    position +=
        Tuning.clawMult * -processAxisDeadzone(getCopilotRightTrigger() + getCopilotLeftTrigger(),
            deadzone);
    claw.setPosition(position);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
