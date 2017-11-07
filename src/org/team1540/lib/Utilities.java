package org.team1540.lib;

import com.ctre.CANTalon;
import org.team1540.kingbass.RobotInfo;

/**
 * Static utility functions.
 *
 * @author Zachary Robinson
 */
public class Utilities {
  /**
   * Calculates the expected motor velocity.
   *
   * @deprecated Probably doesn't work
   * @param gear The gear the robot is currently in ({@code false} for low, {@code true} for high)
   *
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
   * @deprecated Probably doesn't work
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
   * Processes an axis and returns the value only if it is outside the provided deadzone.
   * @param axis The axis to return
   * @param deadzone The deadzone to use.
   * @return If |{@code axis}| > |{@code deadzone}|, returns {@code axis}; otherwise, returns 0.
   */
  public static double processAxisDeadzone(double axis, double deadzone) {
    return (Math.abs(axis) > Math.abs(deadzone)) ? axis : 0;
  }
}
