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
   * @param gear The gear the robot is currently in ({@code false} for low, {@code true} for high)
   *
   * @return The expected motor velocity, in revolutions per minute.
   *
   * @deprecated Probably doesn't work
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
   *
   * @deprecated Probably doesn't work
   */
  public static double calcRPMDeviation(boolean gear, double throttle, CANTalon encoderTalon) {
    encoderTalon.configEncoderCodesPerRev(RobotInfo.ENCODER_CODES_PER_REV);
    double measuredVelocity = encoderTalon.getEncVelocity();
    double expectedVelocity = calcExpectedRPM(gear, throttle);

    return Math.abs(expectedVelocity - measuredVelocity) / expectedVelocity;
  }

  /**
   * Processes an axis and returns the value only if it is outside the provided deadzone.
   *
   * @param axis The axis to return
   * @param deadzone The deadzone to use.
   *
   * @return If |{@code axis}| > |{@code deadzone}|, returns {@code axis}; otherwise, returns 0.
   */
  public static double processAxisDeadzone(double axis, double deadzone) {
    return (Math.abs(axis) > Math.abs(deadzone)) ? axis : 0;
  }

  public static int[] lineUpIndices(int aLength, int bLength) {
    if (aLength == bLength) { return new int[]{0, 0, aLength, bLength}; }
    int[] out;
    if (aLength > bLength) {
      out = new int[]{
          (aLength / 2) - (bLength / 2),
          0,
          (aLength / 2) + (bLength / 2) + 1,
          bLength
      };
    } else {
      out = new int[]{
          0,
          (bLength / 2) - (aLength / 2),
          aLength,
          (bLength / 2) + (aLength / 2) + 1
      };
    }
    return out;
  }
}
