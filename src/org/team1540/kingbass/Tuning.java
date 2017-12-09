package org.team1540.kingbass;


import org.team1540.base.adjustables.Tunable;

/**
 * Provides values (from the SmartDashboard) for tuning the robot.
 *
 * @author Zachary Robinson
 */
public class Tuning {

  @Tunable("Do autonomous")
  public static boolean doAuto = true;
  @Tunable("Auto distance")
  public static double autoDistance = 25;
  @Tunable("Auto back distance")
  public static double autoBackDistance = 2;
  //@Tunable("Vision P")
  public static double visionP = 0.5;
  //@Tunable("Return Threshold")
  public static double returnThreshold = 500;
  // advanced drive
  //@Tunable("Velocity History Size")
  public static int velHistorySize = 5;

  //TODO: Tune
  @Tunable("Drive P")
  public static double driveP = 0.1;
  @Tunable("Drive I")
  public static double driveI = 0.00001;
  @Tunable("Drive D")
  public static double driveD = 0.1;

  @Tunable("Profile F")
  public static double profileF = 7.795;

  // @Tunable("Claw P")
  public static double clawP = 1;
  // @Tunable("Claw I")
  public static double clawI = 0.00001;
  // @Tunable("Claw D")
  public static double clawD = 0.1;
  // drivetrain
  //@Tunable("Left reverse output")
  public static boolean lReverseOutput = false;
  //@Tunable("Right reverse output")
  public static boolean rReverseOutput = false;
  //@Tunable("Left reverse sensor")
  public static boolean lReverseSensor = false;
  //@Tunable("Right reverse sensor")
  public static boolean rReverseSensor = false;
  //@Tunable("Intake timeout")
  public static double intakeTimeout = 5;

  // Intake
  /**
   * Motor set point (out of 1) for running the intake in and out.
   */
  //@Tunable("Intake setpoint")
  public static double intakeSetPoint = 0.5;

  //@Tunable("Intake stop threshold")
  public static double intakeStopThresh = 7.5;

  // Input
  /**
   * Axis input that is less than this value in either direction will be ignored.
   */
  //@Tunable("Deadzone")
  public static double deadzone = 0.1;
  //@Tunable("Intake min time")
  public static double intakeMinTime = 1;
  // @Tunable("Arm P")
  public static double armP = 1;
  // @Tunable("Arm I")
  public static double armI = 0;
  // @Tunable("Arm D")
  public static double armD = 1;
  // Stall current for a 775pro is 134A so this should be sufficient
  // @Tunable("Arm current limit threshold")
  public static double armCurrLimThresh = 100;
  // @Tunable("Arm multiplier")
  public static double armMult = 0.010;
  // @Tunable("Arm Limit")
  public static double armLimit = 0.7;
  // @Tunable("Arm bounceback")
  public static double armBounceBack = 0.05;
  // @Tunable("Arm end threshold")
  public static double armEndThreshold = 0.05;
  // @Tunable("Claw multiplier")
  public static double clawMult = 0.01;
  // @Tunable("Claw Limit")
  public static double clawLimit = 0.09;
  // @Tunable("Claw Grab Point")
  public static double clawEndPoint = -0.03;
  // @Tunable("Claw Bounce Back")
  public static double clawBounceBack = 0.005;
  // @Tunable("Claw end threshold")
  public static double clawEndThreshold = 0.005;
  // @Tunable("Output Bunny Time")
  public static double outputBunnyTime = .5;
  @Tunable("Grab Bucket Claw Position")
  public static double grabBucketPosition = .3;
  @Tunable("DT Ramp Rate")
  public static double dtRampRate = 1;
}
