package org.team1540.kingbass;

/**
 * Provides values (from the SmartDashboard) for tuning the robot.
 * 
 * @author Zachary Robinson
 */
public class Tuning {

  // drivetrain
  private static boolean lReverseOutput = false;
  private static boolean rReverseOutput = false;
  private static boolean lReverseSensor = false;
  private static boolean rReverseSensor = false;
  // Arm
  /** Motor set point (out of 1) for moving the arm up and down. */
  private static double armSpeed = 1;

  // Autoshifting
  /** Current threshold below which the autoshifter will shift up. */
  private static double autoShiftDeviationThreshold = 3;

  /**
   * The time, in ticks (about 20ms), between the autoshifter changing gearss.
   */
  private static int autoshiftCooldown = 5;

  // claw
  private static double openClawTime = 1;
  private static double closeClawTime = 1;


  // Intake
  /** Motor set point (out of 1) for running the intake in and out. */
  private static double intakeSetPoint = 1;

  // Input
  /** Axis input that is less than this value in either direction will be ignored. */
  private static double deadzone = 0.1;

  public static boolean lReverseOutput() {
    return lReverseOutput;
  }

  public static boolean rReverseOutput() {
    return rReverseOutput;
  }

  public static boolean lReverseSensor() {
    return lReverseSensor;
  }

  public static boolean rReverseSensor() {
    return rReverseSensor;
  }

  public static double getArmSpeed() {
    return armSpeed;
  }

  public static double getAutoShiftDeviationThreshold() {
    return autoShiftDeviationThreshold;
  }

  public static int getAutoshiftCooldown() {
    return autoshiftCooldown;
  }

  public static double getOpenClawTime() {
    return openClawTime;
  }

  public static double getCloseClawTime() {
    return closeClawTime;
  }

  public static double getIntakeSetPoint() {
    return intakeSetPoint;
  }

  public static double getDeadzone() {
    return deadzone;
  }
}
