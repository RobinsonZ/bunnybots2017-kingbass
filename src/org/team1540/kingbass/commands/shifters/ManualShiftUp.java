package org.team1540.kingbass.commands.shifters;

import static org.team1540.kingbass.Robot.shifters;

import edu.wpi.first.wpilibj.command.Command;

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
    requires(shifters);
  }

  @Override
  protected void initialize() {
    shifters.shiftUp();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
