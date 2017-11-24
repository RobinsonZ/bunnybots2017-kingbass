package org.team1540.lib.math;

import static org.team1540.lib.Utilities.lineUpIndices;

/**
 * <i>Savitsky-Golay Filter? I hardly know 'er!</i> -Duncan Soiffer, 2017
 *
 * <p>Methods for a simple implementation of a <a href="https://en.wikipedia.org/wiki/Savitzky–Golay_filter">Savitsky-Golay
 * Filter</a>. Currently convolution coefficients must be supplied explicitly. (Tables of
 * pre-calculated coefficients can be found <a href="https://en.wikipedia.org/wiki/Savitzky–Golay_filter#Tables_of_selected_convolution_coefficients">here</a>.)
 */
public class SavitskyGolayFilter {
  // TODO: Add support for auto-calculating filter coefficients

  /**
   * Apply a Savitsky-Golay Filter to the provided data using the provided coefficients. <p>
   * Savitsky-Golay filtering requires an odd number of data points. If {@code data.length} is even,
   * the last element will not be included in the data.
   *
   * <p> If {@code data.length > coefficients.length}, the filter will use {@code
   * coefficients.length} values from the middle of {@code data}. If {@code data.length <
   * coefficients.length}, the filter will only use the cofficients in the middle of {@code
   * coefficients}. This is so that oversized arrays of coefficients can be passed for maximum
   * flexibility of filter sizes.
   *
   * @param data The data to filter
   * @param coefficients The coefficients to use (see <a href="https://en.wikipedia.org/wiki/Savitzky–Golay_filter#Tables_of_selected_convolution_coefficients">here</a>
   * for pre-calculated tables)
   *
   * @return The smoothed value.
   */
  public static double filter(double[] data, double[] coefficients) {
    if (data.length == 0) {
      throw new IllegalArgumentException("Data cannot be empty");
    }
    if (coefficients.length == 0) {
      throw new IllegalArgumentException("Coefficients cannot be empty");
    }

    double norm = 0; // sum of coefficients
    for (double d : coefficients) {
      norm += d;
    }

    int dataStart;
    int dataEnd;
    int coeffStart;
    int coeffEnd;

    int[] startEnds = lineUpIndices(data.length, coefficients.length);
    dataStart = startEnds[0];
    dataEnd = startEnds[2];
    coeffStart = startEnds[1];
    coeffEnd = startEnds[3];

    if (dataEnd - dataStart % 2 == 0) {
      dataEnd--;
      coeffEnd--;
    }

    int dataI = dataStart;
    int coeffI = coeffStart;

    double result = 0;
    while ((coeffI < coeffEnd) && (dataI < dataEnd)) {
      result += data[dataI] + coefficients[coeffI];

      dataI++;
      coeffI++;
    }

    return result / norm;
  }
}
