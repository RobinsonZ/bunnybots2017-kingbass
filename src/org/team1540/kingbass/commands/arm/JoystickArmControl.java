package org.team1540.kingbass.commands.arm;

import static org.team1540.kingbass.Robot.arm;
import static org.team1540.kingbass.Tuning.deadzone;
import static org.team1540.lib.Utilities.processAxisDeadzone;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Controls the arm with a joystick axis.
 *
 * @author Zachary Robinson
 */
public class JoystickArmControl extends Command {
  private Joystick stick;
  private int axis;
  private int axis2;
  private boolean triggers;

  /**
   * Constructs a {@link JoystickArmControl} controlled by a single axis on a joystick.
   */
  public JoystickArmControl(Joystick stick, int axis) {
    super("Control arm with joystick");
    requires(arm);
    this.stick = stick;
    this.axis = axis;
    this.triggers = false;
  }

  /**
   * Constructs a {@link JoystickArmControl} controlled by a combination of two joystick axes (i. e.
   * triggers).
   */
  public JoystickArmControl(Joystick stick, int axis, int axis2) {
    super("Control arm with triggers");
    requires(arm);
    this.stick = stick;
    this.axis = axis;
    this.axis2 = axis2;
    this.triggers = true;
  }

  @Override
  protected void execute() {
    if (triggers) {
      arm.setArm(processAxisDeadzone(stick.getRawAxis(axis2) - stick.getRawAxis(axis),
          deadzone));
    } else {
      arm.setArm(processAxisDeadzone(stick.getRawAxis(axis), deadzone));
    }
  }


  @Override
  protected boolean isFinished() {
    return false;
  }
}
