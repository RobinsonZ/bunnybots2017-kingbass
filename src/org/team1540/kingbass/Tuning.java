package org.team1540.kingbass;


import org.team1540.base.adjustables.Tunable;

/**
 * Provides values (from the SmartDashboard) for tuning the robot.
 *
 * @author Zachary Robinson
 */
public class Tuning {

  @Tunable("Vision P")
  public static double visionP = 0.5;
  @Tunable("Return Threshold")
  public static double returnThreshold = 500;
  // advanced drive
  @Tunable("Velocity History Size")
  public static int velHistorySize = 5;

  // motion profiling
  //values should be changed, these are just copied from henny's code
  @Tunable("Profile P")
  public static double profileP = 0.1;
  @Tunable("Profile I")
  public static double profileI = 0.00001;
  @Tunable("Profile D")
  public static double profileD = 0.1;
  @Tunable("Profile F")
  public static double profileF = 7.795;


  // drivetrain
  @Tunable("Left reverse output")
  public static boolean lReverseOutput = false;
  @Tunable("Right reverse output")
  public static boolean rReverseOutput = false;
  @Tunable("Left reverse sensor")
  public static boolean lReverseSensor = false;
  @Tunable("Right reverse sensor")
  public static boolean rReverseSensor = false;
  @Tunable("Intake timeout")
  public static double intakeTimeout = 5;
  // Arm
  /**
   * Motor set point (out of 1) for moving the arm up and down.
   */
  @Tunable("Arm set point")
  public static double armSpeed = 1;

  // Autoshifting
  /**
   * The level of acceptable deviation between expected speed (as given by the motor throttle) and
   * actual speed (as given by the wheel encoders) before the shifter will shift down.
   */
  public static double autoShiftDeviationThreshold = 0.1;

  /**
   * The time, in ticks (about 20ms), between the autoshifter changing gears.
   */
  public static int autoshiftCooldown = 5;

  // claw
  public static double openClawTime = 1;
  public static double closeClawTime = 1;


  // Intake
  /**
   * Motor set point (out of 1) for running the intake in and out.
   */
  @Tunable("Intake setpoint")
  public static double intakeSetPoint = 1;
  @Tunable("Intake stop threshold")
  public static double intakeStopThresh = 5;

  // Input
  /**
   * Axis input that is less than this value in either direction will be ignored.
   */
  @Tunable("Deadzone")
  public static double deadzone = 0.1;
}
