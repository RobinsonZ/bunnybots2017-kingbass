package org.team1540.kingbass;

/**
 * Provides values (from the SmartDashboard) for tuning the robot.
 * 
 * @author Zachary Robinson
 */
public class Tuning {

  // drivetrain
  private static final boolean L_REVERSE_OUTPUT = false;
  private static final boolean R_REVERSE_OUTPUT = false;
  private static final boolean L_REVERSE_SENSOR = false;
  private static final boolean R_REVERSE_SENSOR = false;
  // Arm
  /** Motor set point (out of 1) for moving the arm up and down. */
  private static final double ARM_SPEED = 1;

  // Autoshifting
  /** Current threshold below which the autoshifter will shift up. */
  private static final double AUTO_SHIFT_DEVIATION_THRESHOLD = 3;

  /**
   * The time, in ticks (about 20ms), between the autoshifter changing gearss.
   */
  private static final int AUTOSHIFT_COOLDOWN = 5;

  // claw
  private static final double OPEN_CLAW_TIME = 1;
  private static final double CLOSE_CLAW_TIME = 1;


  // Intake
  /** Motor set point (out of 1) for running the intake in and out. */
  private static final double INTAKE_SET_POINT = 1;

  // Input
  /** Axis input that is less than this value in either direction will be ignored. */
  private static final double DEADZONE = 0.1;

  public static boolean lReverseOutput() {
    return L_REVERSE_OUTPUT;
  }

  public static boolean rReverseOutput() {
    return R_REVERSE_OUTPUT;
  }

  public static boolean lReverseSensor() {
    return L_REVERSE_SENSOR;
  }

  public static boolean rReverseSensor() {
    return R_REVERSE_SENSOR;
  }

  public static double getArmSpeed() {
    return ARM_SPEED;
  }

  public static double getAutoShiftDeviationThreshold() {
    return AUTO_SHIFT_DEVIATION_THRESHOLD;
  }

  public static int getAutoshiftCooldown() {
    return AUTOSHIFT_COOLDOWN;
  }

  public static double getOpenClawTime() {
    return OPEN_CLAW_TIME;
  }

  public static double getCloseClawTime() {
    return CLOSE_CLAW_TIME;
  }

  public static double getIntakeSetPoint() {
    return INTAKE_SET_POINT;
  }

  public static double getDeadzone() {
    return DEADZONE;
  }
}
