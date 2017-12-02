package org.team1540.kingbass.commands.arm;

import static org.team1540.base.Utilities.processAxisDeadzone;
import static org.team1540.kingbass.OI.copilot;
import static org.team1540.kingbass.Robot.arm;
import static org.team1540.kingbass.Tuning.deadzone;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.Tuning;
import org.team1540.kingbass.commands.controller.VibrateController;

/**
 * Controls the arm with a joystick axis.
 *
 * @author Zachary Robinson
 */
public class JoystickArmControl extends Command {
  private Joystick stick;
  private int axis;
  private double position = 0;
  private VibrateController vibrateControllerCommand = new VibrateController(0.25, copilot);

  /**
   * Constructs a {@link JoystickArmControl} controlled by a single axis on a joystick.
   */
  public JoystickArmControl(Joystick stick, int axis) {
    super("Control arm with joystick");
    requires(arm);
    this.stick = stick;
    this.axis = axis;
  }

  @Override
  protected void initialize() {
    position = arm.getPositionA();
  }

  @Override
  protected void execute() {
    position -= Tuning.armMult * processAxisDeadzone(stick.getRawAxis(axis), deadzone);
    double actPos = arm.setPosition(position);
    if (actPos != position) {
      vibrateControllerCommand.start();
      position += (actPos > position ? 1 : -1) * Tuning.armBounceBack;
    }
  }


  @Override
  protected boolean isFinished() {
    return false;
  }
}
