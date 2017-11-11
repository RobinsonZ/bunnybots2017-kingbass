package org.team1540.lib;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.team1540.lib.Utilities.lineUpIndices;
import static org.team1540.lib.Utilities.processAxisDeadzone;

import org.junit.Test;

public class UtilitiesTest {
  @Test
  public void processAxisDeadzoneZero() throws Exception {
    assertEquals(0, processAxisDeadzone(0, 0.1), 0);
  }

  @Test
  public void processAxisDeadzoneSmallNeg() throws Exception {
    assertEquals(0, processAxisDeadzone(-0.05, 0.1), 0);
  }

  @Test
  public void processAxisDeadzoneSmallPos() throws Exception {
    assertEquals(0, processAxisDeadzone(0.05, 0.1), 0);
  }

  @Test
  public void processAxisDeadzoneLrgNeg() throws Exception {
    assertEquals(-1, processAxisDeadzone(-1, 0.1), 0);
  }

  @Test
  public void processAxisDeadzoneLrgPos() throws Exception {
    assertEquals(1, processAxisDeadzone(1, 0.1), 0);
  }

  @Test
  public void lineUpIndicesEqual() {
    assertArrayEquals(new int[]{0, 0, 5, 5}, lineUpIndices(5, 5));
  }

  @Test
  public void lineUpIndicesWhenAIsGreater() {
    assertArrayEquals(new int[]{1, 0, 4, 3}, lineUpIndices(5, 3));
  }

  @Test
  public void lineUpIndicesWhenBIsGreater() {
    assertArrayEquals(new int[]{0, 1, 3, 4}, lineUpIndices(3, 5));
  }
}