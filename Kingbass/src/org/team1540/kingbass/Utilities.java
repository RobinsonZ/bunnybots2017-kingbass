package org.team1540.kingbass;

import com.ctre.CANTalon;

/**
 * Static utility functions.
 * 
 * @author Zachary Robinson
 */
public class Utilities {
  /**
   * Calculates the expected motor velocity.
   * 
   * @param gear The gear the robot is currently in ({@code false} for low, {@code true} for high)
   * @param motorPower The current power level of the motor.
   * @return The expected motor velocity, in revolutions per minute.
   */
  public static double calcExpectedRPM(boolean gear, double throttle) {
    return RobotInfo.CIM_FREE_SPEED * throttle
        * (gear ? RobotInfo.DRIVE_LOW_GEAR_RATIO : RobotInfo.DRIVE_HIGH_GEAR_RATIO);
  }

  /**
   * Calculates the difference (from 0 to 1) between the expected velocity based on the motor
   * throttle and the actual velocity from the encoder.
   * 
   * @param gear The gear the robot is currently in ({@code false} for low, {@code true} for high)
   * @param throttle The current power level of the motor.
   * @param encoderTalon The {@code CANTalon} that the encoder is connected to.
   */
  public static double calcRPMDeviation(boolean gear, double throttle, CANTalon encoderTalon) {
    encoderTalon.configEncoderCodesPerRev(RobotInfo.ENCODER_CODES_PER_REV);
    double measuredVelocity = encoderTalon.getEncVelocity();
    double expectedVelocity = calcExpectedRPM(gear, throttle);

    return Math.abs(expectedVelocity - measuredVelocity) / expectedVelocity;
  }

  /**
   * Processes an axis and returns the value only if it is outside the deadzone set in
   * {@link Tuning}.
   */
  public static double processAxisDeadzone(double axis) {
    return (Math.abs(axis) > Math.abs(Tuning.getDeadzone())) ? axis : 0;
  }
}
