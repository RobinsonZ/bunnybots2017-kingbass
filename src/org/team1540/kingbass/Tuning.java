package org.team1540.kingbass;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.lib.adjustables.Tunable;

/**
 * Provides values (from the SmartDashboard) for tuning the robot.
 *
 * @author Zachary Robinson
 */
public class Tuning {

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

  // Input
  /**
   * Axis input that is less than this value in either direction will be ignored.
   */
  @Tunable("Deadzone")
  public static double deadzone = 0.1;

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

  public static int getVelHistorySize() {
    return velHistorySize;
  }

  public static void setVelHistorySize(int velHistorySize) {
    Tuning.velHistorySize = velHistorySize;
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

  public static void putAdvDriveTuningValues() {
    SmartDashboard.putNumber("Velocity History Size", velHistorySize);
  }

  public static void putTuningValues() {
    /*
    putMotionProfileTuningValues();
    putDrivetrainTuningValues();
    putArmTuningValues();
    putAutoshiftTuningValues();
    putClawTuningValues();
    putInputTuningValues();
    putIntakeTuningValues();
    putAdvDriveTuningValues();
    */
  }

  public static boolean rReverseOutput() {
    return rReverseOutput;
  }

  public static boolean rReverseSensor() {
    return rReverseSensor;
  }

  public static void updateTuningValues() {
/*
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

velHistorySize = (int) SmartDashboard.getNumber("Velocity History Size", velHistorySize);
*/
  }
}
