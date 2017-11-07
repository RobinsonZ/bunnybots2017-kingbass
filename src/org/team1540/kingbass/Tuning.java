package org.team1540.kingbass;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Provides values (from the SmartDashboard) for tuning the robot.
 * 
 * @author Zachary Robinson
 */
public class Tuning {

  // motion profiling
  //values should be changed, these are just copied from henny's code
  private static double profileP = 0.1; 
  private static double profileI = 0.00001;
  private static double profileD = 0.1;
  private static double profileF = 7.795;
  

  // drivetrain
  private static boolean lReverseOutput = false;
  private static boolean rReverseOutput = false;
  private static boolean lReverseSensor = false;
  private static boolean rReverseSensor = false;
  // Arm
  /** Motor set point (out of 1) for moving the arm up and down. */
  private static double armSpeed = 1;

  // Autoshifting
  /**
   * The level of acceptable deviation between expected speed (as given by the motor throttle) and
   * actual speed (as given by the wheel encoders) before the shifter will shift down.
   */
  private static double autoShiftDeviationThreshold = 0.1;

  /**
   * The time, in ticks (about 20ms), between the autoshifter changing gears.
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

  public static double getArmSpeed() {
    return armSpeed;
  }
  public static int getAutoshiftCooldown() {
    return autoshiftCooldown;
  }

  public static double getAutoShiftDeviationThreshold() {
    return autoShiftDeviationThreshold;
  }

  public static double getCloseClawTime() {
    return closeClawTime;
  }

  public static double getDeadzone() {
    return deadzone;
  }

  public static double getIntakeSetPoint() {
    return intakeSetPoint;
  }

  public static double getOpenClawTime() {
    return openClawTime;
  }

  public static double getProfileD() {
    return profileD;
  }

  public static double getProfileF() {
    return profileF;
  }

  public static double getProfileI() {
    return profileI;
  }

  public static double getProfileP() {
    return profileP;
  }

  public static boolean lReverseOutput() {
    return lReverseOutput;
  }

  public static boolean lReverseSensor() {
    return lReverseSensor;
  }

  public static void putArmTuningValues() {
    SmartDashboard.putNumber("Arm speed", armSpeed);
  }

  public static void putAutoshiftTuningValues() {
    SmartDashboard.putNumber("Autoshifter deviation threshold", autoShiftDeviationThreshold);
    SmartDashboard.putNumber("Autoshifter update speed", autoshiftCooldown);
  }

  public static void putClawTuningValues() {
    SmartDashboard.putNumber("Open claw duration", openClawTime);
    SmartDashboard.putNumber("Close claw duration", closeClawTime);
  }

  public static void putDrivetrainTuningValues() {
    SmartDashboard.putBoolean("Reverse left drive", lReverseOutput);
    SmartDashboard.putBoolean("Reverse left encoder", lReverseSensor);
    SmartDashboard.putBoolean("Reverse right drive", rReverseOutput);
    SmartDashboard.putBoolean("Reverse right encoder", rReverseSensor);
  }

  public static void putInputTuningValues() {
    SmartDashboard.putNumber("Joystick deadzone", deadzone);
  }

  public static void putIntakeTuningValues() {
    SmartDashboard.putNumber("Intake speed", intakeSetPoint);
  }

  public static void putMotionProfileTuningValues() {
    SmartDashboard.putNumber("Profile P", profileP);
    SmartDashboard.putNumber("Profile I", profileI);
    SmartDashboard.putNumber("Profile D", profileD);
    SmartDashboard.putNumber("Profile F", profileF);
  }
  public static void putTuningValues() {
    putMotionProfileTuningValues();
    putDrivetrainTuningValues();
    putArmTuningValues();
    putAutoshiftTuningValues();
    putClawTuningValues();
    putInputTuningValues();
    putIntakeTuningValues();
  }
  public static boolean rReverseOutput() {
    return rReverseOutput;
  }
  public static boolean rReverseSensor() {
    return rReverseSensor;
  }
  public static void updateTuningValues() {
    profileP = SmartDashboard.getNumber("Profile P", profileP);
    profileI = SmartDashboard.getNumber("Profile I", profileI);
    profileD = SmartDashboard.getNumber("Profile D", profileD);
    profileF = SmartDashboard.getNumber("Profile F", profileF);
    
    lReverseOutput = SmartDashboard.getBoolean("Reverse left drive", lReverseOutput);
    lReverseSensor = SmartDashboard.getBoolean("Reverse left encoder", lReverseSensor);
    rReverseOutput = SmartDashboard.getBoolean("Reverse right drive", rReverseOutput);
    rReverseSensor = SmartDashboard.getBoolean("Reverse right encoder", rReverseSensor);

    armSpeed = SmartDashboard.getNumber("Arm speed", armSpeed);

    autoShiftDeviationThreshold =
        SmartDashboard.getNumber("Autoshifter deviation threshold", autoShiftDeviationThreshold);
    autoshiftCooldown =
        (int) SmartDashboard.getNumber("Autoshifter update speed", autoshiftCooldown);

    openClawTime = SmartDashboard.getNumber("Open claw duration", openClawTime);
    closeClawTime = SmartDashboard.getNumber("Close claw duration", closeClawTime);

    intakeSetPoint = SmartDashboard.getNumber("Intake speed", intakeSetPoint);

    deadzone = SmartDashboard.getNumber("Joystick deadzone", deadzone);
  }
}
