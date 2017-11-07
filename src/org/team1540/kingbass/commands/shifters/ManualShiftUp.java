package org.team1540.kingbass.commands.shifters;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.Robot;

/**
 * Overrides the auto-shifter and sets the robot to high gear
 *
 * @author Zachary Robinson
 */
public class ManualShiftUp extends Command {

  /**
   * Constructs a {@link ManualShiftUp}.
   */
  public ManualShiftUp() {
    super("Manual shift up");
    requires(Robot.shifters);
  }

  @Override
  protected void initialize() {
    Robot.shifters.shiftUp();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
