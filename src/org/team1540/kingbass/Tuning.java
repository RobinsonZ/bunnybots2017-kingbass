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

  @Tunable("Claw P")
  public static double clawP = 0.1;
  @Tunable("Claw I")
  public static double clawI = 0.00001;
  @Tunable("Claw D")
  public static double clawD = 0.1;
  // drivetrain
  @Tunable("Left reverse output")
  public static boolean lReverseOutput = true;
  @Tunable("Right reverse output")
  public static boolean rReverseOutput = true;
  @Tunable("Left reverse sensor")
  public static boolean lReverseSensor = false;
  //@Tunable("Right reverse sensor")
  public static boolean rReverseSensor = false;
  //@Tunable("Intake timeout")
  public static double intakeTimeout = 5;
  // Arm
  /**
   * Motor set point (out of 1) for moving the arm up and down.
   */
  @Tunable("Arm set point")
  public static double armSpeed = 1;

  // claw
  public static double openClawTime = 1;
  public static double closeClawTime = 1;


  // Intake
  /**
   * Motor set point (out of 1) for running the intake in and out.
   */
  //@Tunable("Intake setpoint")
  public static double intakeSetPoint = 0.5;

  @Tunable("Intake stop threshold")
  public static double intakeStopThresh = 7.5;

  // Input
  /**
   * Axis input that is less than this value in either direction will be ignored.
   */
  @Tunable("Deadzone")
  public static double deadzone = 0.1;
  //@Tunable("Intake min time")
  public static double intakeMinTime = 1;
  @Tunable("Arm P")
  public static double armP = 1;
  @Tunable("Arm I")
  public static double armI = 0;
  @Tunable("Arm D")
  public static double armD = 1;
  // Stall current for a 775pro is 134A so this should be sufficient
  @Tunable("Arm current limit threshold")
  public static double armCurrLimThresh = 100;
  @Tunable("Arm multiplier")
  public static double armMult = 0.010;
  @Tunable("Arm Limit")
  public static double armLimit = 0.7;
  @Tunable("Arm bounceback")
  public static double armBounceBack = 0.05;
  @Tunable("Arm end threshold")
  public static double armEndThreshold = 0.05;
  @Tunable("Claw multiplier")
  public static double clawMult = 0.01;
  @Tunable("Open Claw Value")
  public static double openClawPos = 0.1;
  @Tunable("Close Claw Value")
  public static double closeClawPos = 0.0;
  @Tunable("Claw Limit")
  public static double clawLimit = 0.7;
  @Tunable("Claw Bounce Back")
  public static double clawBounceBack = 0.05;
  @Tunable("Claw end threshold")
  public static double clawEndThreshold = 0.05;
}
