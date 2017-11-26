package org.team1540.kingbass.commands.controller;

import static edu.wpi.first.wpilibj.GenericHID.RumbleType.kLeftRumble;
import static edu.wpi.first.wpilibj.GenericHID.RumbleType.kRightRumble;

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
    joystick.setRumble(kLeftRumble, 1);
    joystick.setRumble(kRightRumble, 1);
  }

  @Override
  protected void end() {
    joystick.setRumble(kLeftRumble, 0);
    joystick.setRumble(kRightRumble, 0);
  }
}
