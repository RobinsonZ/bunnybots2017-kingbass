package org.team1540.kingbass.commands.arm;

import static org.team1540.base.Utilities.processAxisDeadzone;
import static org.team1540.kingbass.Robot.arm;
import static org.team1540.kingbass.Tuning.deadzone;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.Tuning;

/**
 * Controls the arm with a joystick axis.
 *
 * @author Zachary Robinson
 */
public class JoystickArmControl extends Command {
  private Joystick stick;
  private int axis;

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
  protected void execute() {
    double position = arm.getPositionA();
    position += Tuning.armMult * processAxisDeadzone(stick.getRawAxis(axis), deadzone);
    arm.setPosition(position);
  }


  @Override
  protected boolean isFinished() {
    return false;
  }
}
