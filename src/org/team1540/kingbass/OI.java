package org.team1540.kingbass;

import static org.team1540.kingbass.Tuning.clawEndPoint;
import static org.team1540.kingbass.Tuning.clawLimit;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.team1540.base.triggers.AxisButton;
import org.team1540.base.triggers.DPadButton;
import org.team1540.kingbass.commands.auto.DriveToObject;
import org.team1540.kingbass.commands.claw.MoveClawToPosition;
import org.team1540.kingbass.commands.intake.IntakeBunny;
import org.team1540.kingbass.commands.intake.IntakeIn;
import org.team1540.kingbass.commands.intake.IntakeOut;
import org.team1540.kingbass.commands.shifters.ManualShiftDown;
import org.team1540.kingbass.commands.shifters.ManualShiftUp;

/**
 * Operator Interface.
 */
@SuppressWarnings("unused")
public class OI {
  // Joysticks
  /**
   * Joystick used by the driver.
   */
  public static final Joystick driver = new Joystick(0);
  /**
   * Joystick used by the copilot.
   */
  public static final Joystick copilot = new Joystick(1);

  // Axes
  private static final int RIGHT_AXIS_Y = 5;
  private static final int LEFT_AXIS_Y = 1;
  private static final int RIGHT_AXIS_X = 4;
  private static final int LEFT_AXIS_X = 0;
  private static final int D_PAD_X = 6;

  private static final int RIGHT_TRIGGER = 3;
  private static final int LEFT_TRIGGER = 2;

  // arm control
  /**
   * Joystick used for the arm constrol.
   */
  public static final Joystick ARM_JOYSTICK = copilot;
  /**
   * Axis used for arm control.
   */
  public static final int ARM_AXIS = LEFT_AXIS_Y;
  /**
   * Axis used for arm control if there are two axes (unused here).
   */
  public static final int ARM_AXIS_2 = 0;
  /**
   * Whether {@link #ARM_AXIS_2} should be used
   */
  public static final boolean TRIGGERS = false;

  static {
    Button driverA = new JoystickButton(driver, 1);
    Button driverB = new JoystickButton(driver, 2);
    Button driverX = new JoystickButton(driver, 3);
    Button driverY = new JoystickButton(driver, 4);

    Button driverLeftBumper = new JoystickButton(driver, 5);
    Button driverRightBumper = new JoystickButton(driver, 6);

    Button driverSelect = new JoystickButton(driver, 7);
    Button driverStart = new JoystickButton(driver, 8);

    Button driverLeftStick = new JoystickButton(driver, 9);
    Button driverRightStick = new JoystickButton(driver, 10);

    Button driverDPadUp = new DPadButton(driver, 0, DPadButton.DPadAxis.UP);
    Button driverDPadDown = new DPadButton(driver, 0, DPadButton.DPadAxis.DOWN);
    Button driverDPadLeft = new DPadButton(driver, 0, DPadButton.DPadAxis.LEFT);
    Button driverDPadRight = new DPadButton(driver, 0, DPadButton.DPadAxis.RIGHT);

    Button copilotA = new JoystickButton(copilot, 1);
    Button copilotB = new JoystickButton(copilot, 2);
    Button copilotX = new JoystickButton(copilot, 3);
    Button copilotY = new JoystickButton(copilot, 4);

    Button copilotLeftBumper = new JoystickButton(copilot, 5);
    Button copilotRightBumper = new JoystickButton(copilot, 6);

    Button copilotSelect = new JoystickButton(copilot, 7);
    Button copilotStart = new JoystickButton(copilot, 8);

    Button copilotLeftStick = new JoystickButton(copilot, 9);
    Button copilotRightStick = new JoystickButton(copilot, 10);

    Button copilotDPadUp = new DPadButton(copilot, 0, DPadButton.DPadAxis.UP);
    Button copilotDPadDown = new DPadButton(copilot, 0, DPadButton.DPadAxis.DOWN);
    Button copilotDPadLeft = new DPadButton(copilot, 0, DPadButton.DPadAxis.LEFT);
    Button copilotDPadRight = new DPadButton(copilot, 0, DPadButton.DPadAxis.RIGHT);

    Button driverRightTrigger = new AxisButton(driver, 0.5, RIGHT_TRIGGER);

    driverRightBumper.whenPressed(new ManualShiftUp());
    driverLeftBumper.whenPressed(new ManualShiftDown());

    // driverDPadLeft.whenPressed(new AutoShift());

    copilotRightBumper.whenPressed(new IntakeBunny());
    driverLeftStick.whenPressed(new DriveToObject());

    copilotX.toggleWhenPressed(new IntakeIn());
    copilotY.toggleWhenPressed(new IntakeOut());
    copilotA.whenPressed(new MoveClawToPosition(clawLimit));
    copilotB.whenPressed(new MoveClawToPosition(clawEndPoint));
  }

  public static double getCopilotDPadX() {
    return copilot.getRawAxis(D_PAD_X);
  }

  public static double getCopilotLeftTrigger() {
    return copilot.getRawAxis(LEFT_TRIGGER);
  }

  public static double getCopilotRightTrigger() {
    return -copilot.getRawAxis(RIGHT_TRIGGER);
  }

  public static double getDriveLeftJoystick() {
    return driver.getRawAxis(LEFT_AXIS_Y);
  }

  public static double getDriveLeftJoystickX() {
    return driver.getRawAxis(LEFT_AXIS_X);
  }

  public static double getDriveLeftTrigger() {
    return driver.getRawAxis(LEFT_TRIGGER);
  }

  public static double getDriverDPadX() {
    return driver.getRawAxis(D_PAD_X);
  }

  // DriveTrain
  public static double getDriveRightJoystick() {
    return -driver.getRawAxis(RIGHT_AXIS_Y);
  }

  public static double getDriveRightJoystickX() {
    return -driver.getRawAxis(RIGHT_AXIS_X);
  }

  public static double getDriveRightTrigger() {
    return driver.getRawAxis(RIGHT_TRIGGER);
  }

}
