package org.team1540.kingbass;

import org.team1540.kingbass.commands.claw.CloseClaw;
import org.team1540.kingbass.commands.intake.IntakeIn;
import org.team1540.kingbass.commands.intake.IntakeOut;
import org.team1540.kingbass.commands.shifters.AutoShift;
import org.team1540.kingbass.commands.shifters.ManualShiftDown;
import org.team1540.kingbass.commands.shifters.ManualShiftUp;
import org.team1540.kingbass.triggers.DPadButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * OI for a single joystick setup. Bindings:
 * <ul>
 * <li>Left stick Y: left motors</li>
 * <li>Right stick Y: right motors</li>
 * <li>Left trigger: lower arm</li>
 * <li>Right trigger: raise arm</li>
 * <li>D-Pad up: force high gear</li>
 * <li>D-Pad down: force low gear</li>
 * <li>D-Pad left: return to auto-shifting</li>
 * <li>A: open and close claw</li>
 * <li>B: toggle intake in</li>
 * <li>Y: toggle intake out</li>
 * </ul>
 * 
 * @author Zachary Robinson
 */
@SuppressWarnings("unused")
public class SingleControllerOI {
  // Joysticks
  /** Joystick used by the driver. */
  public static final Joystick driver = new Joystick(0);

  // Axes
  private static final int RIGHT_AXIS_Y = 5;
  private static final int LEFT_AXIS_Y = 1;
  private static final int RIGHT_AXIS_X = 4;
  private static final int LEFT_AXIS_X = 0;
  private static final int D_PAD_X = 6;

  private static final int RIGHT_TRIGGER = 3;
  private static final int LEFT_TRIGGER = 2;

  // arm control
  public static final Joystick ARM_JOYSTICK = driver;
  public static final int ARM_AXIS = LEFT_TRIGGER;
  public static final int ARM_AXIS_2 = RIGHT_TRIGGER;
  public static final boolean TRIGGERS = true;

  // DriveTrain
  public static double getDriveRightJoystick() {
    return -driver.getRawAxis(RIGHT_AXIS_Y);
  }

  public static double getDriveLeftJoystick() {
    return driver.getRawAxis(LEFT_AXIS_Y);
  }

  public static double getDriveRightJoystickX() {
    return -driver.getRawAxis(RIGHT_AXIS_X);
  }

  public static double getDriveLeftJoystickX() {
    return driver.getRawAxis(LEFT_AXIS_X);
  }

  public static double getDriveRightTrigger() {
    return driver.getRawAxis(RIGHT_TRIGGER);
  }

  public static double getDriveLeftTrigger() {
    return driver.getRawAxis(LEFT_TRIGGER);
  }

  public static double getDriverDPadX() {
    return driver.getRawAxis(D_PAD_X);
  }

  public SingleControllerOI() {
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

    driverDPadUp.whenPressed(new ManualShiftUp());
    driverDPadDown.whenPressed(new ManualShiftDown());
    driverDPadLeft.whenPressed(new AutoShift());

    driverA.toggleWhenPressed(new CloseClaw());
    driverB.toggleWhenPressed(new IntakeIn());
    driverY.toggleWhenPressed(new IntakeOut());
  }
}
