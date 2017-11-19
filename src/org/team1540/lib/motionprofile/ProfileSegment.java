package org.team1540.lib.motionprofile;

/**
 * A segment of a motion profile.
 */
public interface ProfileSegment {

  /**
   * Gets the left-side output of this part of the motion profile, as an array of {@code double[]}
   * formatted as {@code [position, velocity, duration]}.
   *
   * @return The left-side output of this motion profile.
   */
  public double[][] getLeftPts();

  /**
   * Gets the right-side output of this part of the motion profile, as an array of {@code double[]}
   * formatted as {@code [position, velocity, duration]}.
   *
   * @return The right-side output of this motion profile.
   */
  public double[][] getRightPts();

  /**
   * Gets the number of points in the motion profile.
   *
   * @return The size of the profile.
   */
  public int getSize();
}
