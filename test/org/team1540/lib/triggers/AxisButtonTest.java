package org.team1540.lib.triggers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.team1540.lib.triggers.AxisButton.process;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AxisButtonTest {

  @ParameterizedTest(name = "Is {2} when axis is {0} and threshold is {1}")
  @CsvSource({
      "1.0, 0.5, true",
      "-1.0, 0.5, false",
      "1.0, -0.5, false",
      "-1.0, -0.5, true",
      "0.25, 0.5, false",
      "-0.25, 0.5, false",
      "0.25, -0.5, false",
      "-0.25, -0.5, false",
      "0, 0.5, false",
      "0, -0.5, false"
  })
  public void processTest(double axis, double threshold, boolean expected) throws Exception {
    assertEquals(expected, process(axis, threshold));
  }
}