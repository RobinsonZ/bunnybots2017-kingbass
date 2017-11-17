package org.team1540.lib.motionprofile;

import java.util.List;

public class ProfileSegmentChain {
  private List<ProfileSegment> segments;
  // cache compilation result
  private double[][] leftCache;
  private double[][] rightCache;

  public void add(ProfileSegment s) {
    segments.add(s);

    // caches are invalid now, reset them
    leftCache = null;
    rightCache = null;
  }

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