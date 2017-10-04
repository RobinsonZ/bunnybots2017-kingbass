package org.team1540.kingbass;

/**
 * Various constants for tuning the robot.
 * 
 * @author Zachary Robinson
 */
public class Tuning {

  // drivetrain
  public static final boolean L_REVERSE_OUTPUT = false;
  public static final boolean R_REVERSE_OUTPUT = false;
  public static final boolean L_REVERSE_SENSOR = false;
  public static final boolean R_REVERSE_SENSOR = false;
  // Arm
  /** Motor set point (out of 1) for moving the arm up and down. */
  public static final double ARM_SPEED = 1;

  // Autoshifting
  /** Current threshold below which the autoshifter will shift up. */
  public static final double AUTO_SHIFT_DEVIATION_THRESHOLD = 3;

  /**
   * The time, in ticks (about 20ms), between the autoshifter changing gearss.
   */
  public static final int AUTOSHIFT_COOLDOWN = 5;

  // claw
  public static final double OPEN_CLAW_TIME = 1;
  public static final double CLOSE_CLAW_TIME = 1;


  // Intake
  /** Motor set point (out of 1) for running the intake in and out. */
  public static final double INTAKE_SET_POINT = 1;

  // Input
  /** Axis input that is less than this value in either direction will be ignored. */
  public static final double DEADZONE = 0.1;
}
