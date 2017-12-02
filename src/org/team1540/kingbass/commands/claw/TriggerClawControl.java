package org.team1540.kingbass.commands.claw;

import static org.team1540.base.Utilities.processAxisDeadzone;
import static org.team1540.kingbass.OI.copilot;
import static org.team1540.kingbass.Robot.claw;
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
public class TriggerClawControl extends Command {
  private final int lAxis;
  private final int rAxis;
  private Joystick stick;
  private double position = claw.getPosition();
  private VibrateController vibrateControllerCommand = new VibrateController(0.25, copilot);

  /**
   * Constructs a {@link TriggerClawControl} controlled by a single axis on a joystick.
   */
  public TriggerClawControl(Joystick stick, int lAxis, int rAxis) {
    super("Control arm with joystick");
    this.lAxis = lAxis;
    this.rAxis = rAxis;
    requires(claw);
    this.stick = stick;
  }

  @Override
  protected void initialize() {
    position = claw.getPosition();
  }

  @Override
  protected void execute() {
    position -=
        Tuning.clawMult * processAxisDeadzone(stick.getRawAxis(rAxis) + stick.getRawAxis(lAxis),
            deadzone);
    double actPos = claw.setPosition(position);
    if (actPos != position) {
      vibrateControllerCommand.start();
      position += (actPos > position ? 1 : -1) * Tuning.clawBounceBack;
    }
  }


  @Override
  protected boolean isFinished() {
    return false;
  }
}

