package org.team1540.kingbass.commands.controller;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class VibrateController extends TimedCommand {

  private final Joystick joystick;

  public VibrateController(double timeout, Joystick joystick) {
    super(timeout);

    this.joystick = joystick;
  }

  @Override
  protected void initialize() {
    joystick.setRumble(RumbleType.kLeftRumble, 1);
    joystick.setRumble(RumbleType.kRightRumble, 1);
  }

  @Override
  protected void end() {
    joystick.setRumble(RumbleType.kLeftRumble, 0);
    joystick.setRumble(RumbleType.kRightRumble, 0);
  }
}
