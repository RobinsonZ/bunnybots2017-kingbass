package org.team1540.lib;

/**
 * Static utility functions.
 *
 * @author Zachary Robinson
 */
public class Utilities {
//  /**
//   * Calculates the expected motor velocity.
//   *
//   * @param gear The gear the robot is currently in ({@code false} for low, {@code true} for high)
//   *
//   * @return The expected motor velocity, in revolutions per minute.
//   *
//   * @deprecated Probably doesn't work
//   */
//  public static double calcExpectedRPM(boolean gear, double throttle) {
//    return RobotInfo.CIM_FREE_SPEED * throttle
//        * (gear ? RobotInfo.DRIVE_LOW_GEAR_RATIO : RobotInfo.DRIVE_HIGH_GEAR_RATIO);
//  }
//
//  /**
//   * Calculates the difference (from 0 to 1) between the expected velocity based on the motor
//   * throttle and the actual velocity from the encoder.
//   *
//   * @param gear The gear the robot is currently in ({@code false} for low, {@code true} for high)
//   * @param throttle The current power level of the motor.
//   * @param encoderTalon The {@code ChickenTalon} that the encoder is connected to.
//   *
//   * @deprecated Probably doesn't work
//   */
//  public static double calcRPMDeviation(boolean gear, double throttle, ChickenTalon encoderTalon) {
//    encoderTalon.configEncoderCodesPerRev(RobotInfo.ENCODER_CODES_PER_REV);
//    double measuredVelocity = encoderTalon.getEncVelocity();
//    double expectedVelocity = calcExpectedRPM(gear, throttle);
//
//    return Math.abs(expectedVelocity - measuredVelocity) / expectedVelocity;
//  }

  /**
   * Matches up two arrays' indices given their lengths. This matches the arrays around their
   * central points rather then truncating one array's end. The returned values should be used as
   * start and end points for iterating over the array where one would normally use {@code 0}  and
   * {@code arr.length} as start and end.
   *
   * <p>Generally, if {@code aLength == bLength}, the returned values will point to the start and
   * end indices of both arrays. If {@code aLength > bLength}, the function's returned values will
   * indicate {@code bLength} values from the middle of an array with length {@code aLength}. If
   * {@code aLength < bLength}, the function's returned values will indicate {@code aLength} values
   * from the middle of an array with length {@code bLength}.
   *
   * @param aLength the length of the first array to match
   * @param bLength the length of the second array to match
   *
   * @return a {@code double[]} containing start and end indices for both arrays, in the format
   * {@code [aStart, bStart, aEnd, bEnd]}.
   */
  public static int[] lineUpIndices(int aLength, int bLength) {
    if (aLength < 0) { throw new IllegalArgumentException("aLength cannot be less than 0"); }
    if (bLength < 0) { throw new IllegalArgumentException("bLength cannot be less than 0"); }

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
