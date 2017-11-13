package org.team1540.lib;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.team1540.lib.Utilities.lineUpIndices;
import static org.team1540.lib.Utilities.processAxisDeadzone;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Utility Functions")
public class UtilitiesTest {
  @Test
  @DisplayName("processAxisDeadzone() returns 0 when axis is 0")
  public void processAxisDeadzoneZero() throws Exception {
    assertEquals(0, processAxisDeadzone(0, 0.1));
  }

  @Test
  @DisplayName("processAxisDeadzone() returns 0 when axis is negative within deadzone")
  public void processAxisDeadzoneSmallNeg() throws Exception {
    assertEquals(0, processAxisDeadzone(-0.05, 0.1));
  }

  @Test
  @DisplayName("processAxisDeadzone() returns 0 when axis is positive within deadzone")
  public void processAxisDeadzoneSmallPos() throws Exception {
    assertEquals(0, processAxisDeadzone(0.05, 0.1));
  }

  @Test
  @DisplayName("processAxisDeadzone() returns axis when axis is negative outside deadzone")
  public void processAxisDeadzoneLrgNeg() throws Exception {
    assertEquals(-1, processAxisDeadzone(-1, 0.1));
  }

  @Test
  @DisplayName("processAxisDeadzone() returns axis when axis is positive outside deadzone")
  public void processAxisDeadzoneLrgPos() throws Exception {
    assertEquals(1, processAxisDeadzone(1, 0.1));
  }

  @Test
  @DisplayName("lineUpIndices() does nothing when lengths are equal")
  public void lineUpIndicesEqual() {
    assertArrayEquals(new int[]{0, 0, 5, 5}, lineUpIndices(5, 5));
  }

  @Test
  @DisplayName("lineUpIndices() shortens A correctly when A is longer")
  public void lineUpIndicesWhenAIsGreater() {
    assertArrayEquals(new int[]{1, 0, 4, 3}, lineUpIndices(5, 3));
  }

  @Test
  @DisplayName("lineUpIndices() shortens B correctly when B is longer")
  public void lineUpIndicesWhenBIsGreater() {
    assertArrayEquals(new int[]{0, 1, 3, 4}, lineUpIndices(3, 5));
  }
}