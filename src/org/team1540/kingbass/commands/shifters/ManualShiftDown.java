package org.team1540.kingbass.commands.shifters;

import static org.team1540.kingbass.OI.driver;
import static org.team1540.kingbass.Robot.shifters;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.commands.controller.VibrateController;

/**
 * Overrides the auto-shifter and sets the robot to low gear.
 *
 * @author Zachary Robinson
 */
public class ManualShiftDown extends Command {

  private VibrateController vibrateController = new VibrateController(.25, driver);

  public ManualShiftDown() {
    super("Manual shift down");
    requires(shifters);
  }

  @Override
  protected void initialize() {
    shifters.shiftDown();
    vibrateController.start();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
