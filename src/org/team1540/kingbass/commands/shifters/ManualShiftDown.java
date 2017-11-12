package org.team1540.kingbass.commands.shifters;

import static org.team1540.kingbass.Robot.shifters;

import edu.wpi.first.wpilibj.command.Command;

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
    requires(shifters);
  }

  @Override
  protected void initialize() {
    shifters.shiftDown();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
