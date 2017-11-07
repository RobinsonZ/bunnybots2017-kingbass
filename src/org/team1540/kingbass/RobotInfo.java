package org.team1540.kingbass;

/**
 * Robot Information such as the drive gear ratio, motor/pneumatic ports, and other such things.
 * This is for stuff rooted in the physical robot, as opposed to constants for algorithms which
 * should go in {@link Tuning}.
 * 
 * @author Zachary Robinson
 */
public class RobotInfo {
  // info
  // ratios are between the motor shaft and the encoder
  public static final double DRIVE_LOW_GEAR_RATIO = 0.05;
  public static final double DRIVE_HIGH_GEAR_RATIO = 0.5;

  // motor stats
  public static final int CIM_FREE_SPEED = 5330;

  // encoder stats
  public static final int ENCODER_CODES_PER_REV = 4096;

  // motors
  // drivetrain
  /** Left drive motor with encoder connected. */
  public static final int L_MASTER = 1;
  /** Left drive motor with no encoder. */
  public static final int L_SLAVE_A = 2;
  /** Left drive motor with no encoder. */
  public static final int L_SLAVE_B = 3;

  /** Right drive motor with encoder connected. */
  public static final int R_MASTER = 4;
  /** Right drive motor with no encoder. */
  public static final int R_SLAVE_A = 5;
  /** Right drive motor with no encoder. */
  public static final int R_SLAVE_B = 6;

  // intake
  /** Intake drive motor. */
  public static final int INTAKE = 7;

  // arm
  /** Arm motor with encoder. */
  public static final int ARM_A = 8;
  /** Arm motor without encoder. */
  public static final int ARM_B = 9;

  // claw
  /** Motor for the left claw finger. */
  public static final int L_CLAW = 10;
  /** Motor for the right claw finger. */
  public static final int R_CLAW = 11;


  // pneumatics
  // shifters
  /** Shifter for the left gearbox. */
  public static final int L_SHIFTER = 1;
  /** Shifter for the right gearbox. */
  public static final int R_SHIFTER = 2;
}
