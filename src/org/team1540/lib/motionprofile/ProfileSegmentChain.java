package org.team1540.lib.motionprofile;

import java.util.List;

/**
 * A chain of {@link org.team1540.lib.motionprofile.ProfileSegment} instances. Multiple
 * ProfileSegments (either directly instantiated or produced by a {@link ProfileSegmentFactory} can
 * be added to the chain before combining them into two arrays for the left and right motors using
 * {@link #compileLeft()} and {@link #compileRight()}. This allows for complex profiles to be made
 * using a combination of different kinds of {@code ProfileSegment}s.
 *
 * @see ProfileSegment
 * @see ProfileSegmentFactory
 */
public class ProfileSegmentChain {
  private List<ProfileSegment> segments;
  // cache compilation result
  private double[][] leftCache;
  private double[][] rightCache;

  /**
   * Adds a {@link org.team1540.lib.motionprofile.ProfileSegment} to the chain. The segment is added
   * to the end of the chain and will be executed after any segments that have been already added.
   *
   * <p> This method returns a reference to its instance in a builder pattern. This allows multiple
   * method calls to be chained together: <pre>
   *   ProfileSegmentChain chain = new ProfileSegmentChain()
   *    .add(segment1)
   *    .add(segment2)
   *    .add(segment3);
   * </pre>
   *
   * @param s The segment to add.
   */
  public ProfileSegmentChain add(ProfileSegment s) {
    segments.add(s);

    // caches are invalid now, reset them
    leftCache = null;
    rightCache = null;

    return this;
  }

  /**
   * Compiles the left-side output of the motion profile into a single array of points in the format
   * {@code [position, velocity, duration]}. This method caches its output, and as such if it is
   * called multiple times without the makeup of the chain changing through {@link
   * #add(ProfileSegment)}, it will not re-compile the motion profile and instead return a reference
   * to the same array as the last time it was called.
   *
   * @return An array of {@code double[]} instances each representing one point in the left-side
   * motion profile, and formatted as {@code [position, velocity, duration]}.
   */
  public double[][] compileLeft() {
    if (leftCache != null) { return leftCache; }

    // find the number of total points
    int totPts = 0;
    for (ProfileSegment segment : segments) {
      totPts += segment.getSize();
    }

    leftCache = new double[totPts][3];

    int i = 0;
    for (ProfileSegment segment : segments) {
      for (double pt[] : segment.getLeftPts()) {
        leftCache[i] = pt;
        i++;
      }
    }

    return leftCache;
  }

  /**
   * Compiles the right-side output of the motion profile into a single array of points in the
   * format {@code [position, velocity, duration]}. This method caches its output, and as such if it
   * is called multiple times without the makeup of the chain changing through {@link
   * #add(ProfileSegment)}, it will not re-compile the motion profile and instead return a reference
   * to the same array as the last time it was called.
   *
   * @return An array of {@code double[]} instances each representing one point in the right-side
   * motion profile, and formatted as {@code [position, velocity, duration]}.
   */
  public double[][] compileRight() {
    if (rightCache != null) { return rightCache; }

    // find the number of total points
    int totPts = 0;
    for (ProfileSegment segment : segments) {
      totPts += segment.getSize();
    }

    rightCache = new double[totPts][3];

    int i = 0;
    for (ProfileSegment segment : segments) {
      for (double[] pt : segment.getRightPts()) {
        rightCache[i] = pt;
        i++;
      }
    }

    return rightCache;
  }
}