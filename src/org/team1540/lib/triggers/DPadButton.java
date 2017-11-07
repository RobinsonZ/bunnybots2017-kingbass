package org.team1540.lib.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Used to map a button from the D-Pad on a controller (or any joystick POV device) to a button so
 * it can be used for triggering commands.
 */
public class DPadButton extends Button {
  /**
   * Enum representing the possible axes of a D-Pad.
   */
  public enum DPadAxis {
    UP(315, 45), DOWN(135, 225), LEFT(225, 315), RIGHT(45, 135);

    final int min;
    final int max;

    DPadAxis(int min, int max) {
      this.min = min;
      this.max = max;
    }
  }

  private Joystick stick;
  private int pad;

  private DPadAxis axis;

  /**
   * Constructs a {@link DPadButton}.
   *
   * @param stick The joystick with the button.
   * @param pad The ID of the d-pad.
   * @param axis The axis of the button.
   */
  public DPadButton(Joystick stick, int pad, DPadAxis axis) {
    super();
    this.stick = stick;
    this.pad = pad;
    this.axis = axis;
  }

  @Override
  public boolean get() {
    int pov = stick.getPOV(pad);

    return pov != -1 && (pov >= axis.min && pov <= axis.max);
  }
}
