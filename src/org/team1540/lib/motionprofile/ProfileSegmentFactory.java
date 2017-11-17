package org.team1540.lib.motionprofile;

import java.util.function.DoubleFunction;
import org.apache.commons.math3.analysis.integration.RombergIntegrator;
import org.apache.commons.math3.analysis.integration.UnivariateIntegrator;

/**
 * Class for producing multiple {@link ProfileSegment} instances with preset options (max speed,
 * accel, etc.).
 */
public class ProfileSegmentFactory {
  UnivariateIntegrator integrator = new RombergIntegrator();
  private double maxSpeed; // pos per second
  private double maxAccel; // pos per second per second
  private int resolution;
  private double wheelBase;

  public double getMaxSpeed() {
    return maxSpeed;
  }

  /**
   * Sets the maximum (i.e. cruising) speed of all future {@link ProfileSegment} instances produced
   * by this {@code ProfileSegmentFactory}. <p>This method returns a reference to its instance in a
   * builder pattern. This allows for multiple methods to be chained: <pre>
   *   new ProfileSegmentFactory
   *    .setMaxSpeed(1)
   *    .setMaxAccel(0.5)
   *    .setResolution(10)
   *    .setWheelBase(9.5);
   *  </pre>
   *
   * @param maxSpeed The maximum speed of generated profiles, in your CANTalon position units per
   * second.
   */
  public ProfileSegmentFactory setMaxSpeed(double maxSpeed) {
    this.maxSpeed = maxSpeed;
    return this;
  }

  public double getMaxAccel() {
    return maxAccel;
  }

  /**
   * Sets the maximum allowed acceleration of all future {@link ProfileSegment} instances produced
   * by this {@code ProfileSegmentFactory}. <p>This method returns a reference to its instance in a
   * builder pattern. This allows for multiple methods to be chained: <pre>
   *   new ProfileSegmentFactory
   *    .setMaxSpeed(1)
   *    .setMaxAccel(0.5)
   *    .setResolution(10)
   *    .setWheelBase(9.5);
   *  </pre>
   *
   * @param maxAccel The maximum acceleration of generated profiles, in your CANTalon position units
   * per second^2.
   */
  public ProfileSegmentFactory setMaxAccel(double maxAccel) {
    this.maxAccel = maxAccel;
    return this;
  }

  public int getResolution() {
    return resolution;
  }

  /**
   * Sets the resolution of all future {@link ProfileSegment} instances produced by this {@code
   * ProfileSegmentFactory}. The resolution is the time between points in the motion profile.
   * <p>This method returns a reference to its instance in a
   * builder pattern. This allows for multiple methods to be chained: <pre>
   *   new ProfileSegmentFactory
   *    .setMaxSpeed(1)
   *    .setMaxAccel(0.5)
   *    .setResolution(10)
   *    .setWheelBase(9.5);
   *  </pre>
   *
   * @param resolution The time between motion profile points, in milliseconds.
   */
  public ProfileSegmentFactory setResolution(int resolution) {
    this.resolution = resolution;
    return this;
  }

  public double getWheelBase() {
    return wheelBase;
  }

  /**
   * Sets the assumed wheel-base width of all future {@link ProfileSegment} instances produced by
   * this {@code ProfileSegmentFactory}. The wheel-base width is used for calculating curved
   * profiles. <p>This method returns a reference to its instance in a
   * builder pattern. This allows for multiple methods to be chained: <pre>
   *   new ProfileSegmentFactory
   *    .setMaxSpeed(1)
   *    .setMaxAccel(0.5)
   *    .setResolution(10)
   *    .setWheelBase(9.5);
   *  </pre>
   *
   * @param wheelBase The distance between your drivetrain's wheels, in terms of your CANTalon
   * position units times the circumference of your drive wheels.
   */
  public ProfileSegmentFactory setWheelBase(double wheelBase) {
    this.wheelBase = wheelBase;
    return this;
  }

  public ProfileSegment createStraight(double dist) {
    return createStraight(dist, false, false, false);
  }

  public ProfileSegment createStraight(double dist, boolean rampUp, boolean rampDown,
      boolean isEnd) {
    double rampTime = maxSpeed / maxAccel; // time to accelerate

    double rampDistTraveled = 0;

    if (rampUp) {
      rampDistTraveled += (maxSpeed * rampTime) / 2; // area under triangle
    }
    if (rampDown) { rampDistTraveled += (maxSpeed * rampTime) / 2; }

    double cruiseDistTraveled = dist - rampDistTraveled;

    double cruiseTime = cruiseDistTraveled / maxSpeed;

    double totTime = cruiseTime + (rampUp ? rampTime : 0) + (rampDown ? rampTime : 0); // seconds

    // calculate number of points necessary
    int numPts = (int) Math.ceil((totTime * 1000) / resolution);

    // create our point array (only one because we're just going straight)
    double[][] points = new double[numPts][3];

    DoubleFunction<Double> velCalc = time -> {
      if (time < rampTime * 1000) {
        return time * (maxAccel / 1000);
      } else if (time > (rampTime + cruiseTime) * 1000) {
        return (time % rampTime) * (maxAccel / 1000);
      } else {
        return maxSpeed;
      }
    };

    DoubleFunction<Double> posCalc =
        time -> integrator.integrate(1000, velCalc::apply, 0, time * 1000);

    double currTime = 0; //ms

    for (double[] point : points) {
      point[0] = posCalc.apply(currTime); // position
      point[1] = velCalc.apply(currTime); // velocity
      point[2] = resolution; // duration
      currTime += resolution;
    }

    // create our segment object
    ProfileStraight segment = new ProfileStraight();

    // set fields
    segment.points = points;
    segment.numPoints = points.length;

    return segment;
  }

  public ProfileSegment createCurve(double dist, double endAngle, boolean rampUp,
      boolean rampDown) {
    // find the radius of the curve
    double radius = (dist / 2) / Math.sin(endAngle / 2);

    double leftRadius = radius - (wheelBase / 2);
    double rightRadius = radius + (wheelBase / 2);

    double leftCirc = Math.PI * leftRadius * leftRadius;
    double rightCirc = Math.PI * rightRadius * rightRadius;

    // scale between left and right circumference
    double scale = Math.min(leftCirc, rightCirc) / Math.max(leftCirc, rightCirc);

    double maxRampTime = maxSpeed / maxAccel; // time to accelerate

    double maxRampDistTraveled = 0;

    if (rampUp) {
      maxRampDistTraveled += (maxSpeed * maxRampTime) / 2; // area under triangle
    }
    if (rampDown) { maxRampDistTraveled += (maxSpeed * maxRampTime) / 2; }

    double maxCruiseDistTraveled = Math.max(
        (endAngle / (2 * Math.PI)) * leftCirc - maxRampDistTraveled,
        (endAngle / (2 * Math.PI)) * rightCirc - maxRampDistTraveled);

    double cruiseTime = maxCruiseDistTraveled / maxSpeed;

    double totTime = cruiseTime + (rampUp ? maxRampTime : 0) + (rampDown ? maxRampTime : 0);

    //calc number of points necessary
    int numPts = (int) Math.ceil((totTime * 1000) / resolution);

    //noinspection Duplicates
    DoubleFunction<Double> maxVelCalc = time -> {
      if (time < maxRampTime * 1000) {
        return time * (maxAccel / 1000);
      } else if (time > (maxRampTime + cruiseTime) * 1000) {
        return (time % maxRampTime) * (maxAccel / 1000);
      } else {
        return maxSpeed;
      }
    };

    DoubleFunction<Double> minVelCalc = time -> {
      if (time < maxRampTime * 1000) {
        return time * (maxAccel / 1000) * scale;
      } else if (time > (maxRampTime + cruiseTime) * 1000) {
        return (time % maxRampTime) * (maxAccel / 1000) * scale;
      } else {
        return maxSpeed * scale;
      }
    };

    DoubleFunction<Double> maxPosCalc =
        time -> integrator.integrate(1000, maxVelCalc::apply, 0, time * 1000);
    DoubleFunction<Double> minPosCalc =
        time -> integrator.integrate(1000, minVelCalc::apply, 0, time * 1000);

    double currTime = 0;

    // create two point arrays
    double[][] minPts = new double[numPts][3];
    double[][] maxPts = new double[numPts][3];

    for (double[] point : minPts) {
      point[0] = minPosCalc.apply(currTime); // position
      point[1] = minVelCalc.apply(currTime); // velocity
      point[2] = resolution; // duration
      currTime += resolution;
    }

    currTime = 0;

    for (double[] point : maxPts) {
      point[0] = maxPosCalc.apply(currTime); // position
      point[1] = maxVelCalc.apply(currTime); // velocity
      point[2] = resolution; // duration
      currTime += resolution;
    }

    // create our segment
    ProfileCurve segment = new ProfileCurve();
    segment.numPoints = numPts;

    segment.left = leftCirc > rightCirc ? maxPts : minPts;
    segment.right = leftCirc > rightCirc ? minPts : maxPts;

    return segment;
  }

  /**
   *
   */
  private static class ProfileCurve implements ProfileSegment {
    int numPoints;
    double[][] left;
    double[][] right;

    @Override
    public double[][] getLeftPts() {
      return left;
    }

    @Override
    public double[][] getRightPts() {
      return right;
    }

    @Override
    public int getSize() {
      return numPoints;
    }
  }

  private static class ProfileStraight implements ProfileSegment {
    int numPoints;
    double[][] points;

    @Override
    public double[][] getLeftPts() {
      return points;
    }

    @Override
    public double[][] getRightPts() {
      return points;
    }

    @Override
    public int getSize() {
      return numPoints;
    }
  }
}
