package org.team1540.lib.triggers;

import static org.team1540.lib.triggers.DPadButton.DPadAxis.DOWN;
import static org.team1540.lib.triggers.DPadButton.DPadAxis.LEFT;
import static org.team1540.lib.triggers.DPadButton.DPadAxis.RIGHT;
import static org.team1540.lib.triggers.DPadButton.DPadAxis.UP;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.team1540.lib.triggers.DPadButton.DPadAxis;


class DPadButtonTest {
  @ParameterizedTest
  @MethodSource({"processTestProvider"})
  void processTest(Integer pov, DPadAxis axis, boolean expected) {
    Assertions.assertEquals(expected, DPadButton.process(pov, axis));
  }


  static Stream<Arguments> processTestProvider() {
    return Stream.of(
        Arguments.of(0, UP, true),
        Arguments.of(0, DOWN, false),
        Arguments.of(0, LEFT, false),
        Arguments.of(0, RIGHT, false),
        Arguments.of(45, UP, true),
        Arguments.of(45, DOWN, false),
        Arguments.of(45, LEFT, false),
        Arguments.of(45, RIGHT, true),
        Arguments.of(90, UP, false),
        Arguments.of(90, DOWN, false),
        Arguments.of(90, LEFT, false),
        Arguments.of(90, RIGHT, true),
        Arguments.of(135, UP, false),
        Arguments.of(135, DOWN, true),
        Arguments.of(135, LEFT, false),
        Arguments.of(135, RIGHT, true),
        Arguments.of(180, UP, false),
        Arguments.of(180, DOWN, true),
        Arguments.of(180, LEFT, false),
        Arguments.of(180, RIGHT, false),
        Arguments.of(225, UP, false),
        Arguments.of(225, DOWN, true),
        Arguments.of(225, LEFT, true),
        Arguments.of(225, RIGHT, false),
        Arguments.of(270, UP, false),
        Arguments.of(270, DOWN, false),
        Arguments.of(270, LEFT, true),
        Arguments.of(270, RIGHT, false),
        Arguments.of(315, UP, true),
        Arguments.of(315, DOWN, false),
        Arguments.of(315, LEFT, true),
        Arguments.of(315, RIGHT, false)
    );
  }
}