package org.team1540.kingbass.commands.shifters;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.Robot;

/**
 * Overrides the auto-shifter and sets the robot to low gear.
 *
 * @author Zachary Robinson
 */
public class ManualShiftDown extends Command {

  /**
   * Constructs a {@link ManualShiftDown}.
   */
  public ManualShiftDown() {
    super("Manual shift down");
    requires(Robot.shifters);
  }

  @Override
  protected void initialize() {
    Robot.shifters.shiftDown();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
