package org.team1540.kingbass.commands.drivetrain;

import static org.team1540.kingbass.Robot.driveTrain;
import static org.team1540.kingbass.Tuning.getVelHistorySize;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Arrays;
import org.team1540.kingbass.OI;
import org.team1540.lib.FixedSizeHistory;
import org.team1540.lib.math.SavitskyGolayFilter;

public class AdvancedDrive extends Command {
  // taken from https://en.wikipedia.org/wiki/Savitzky–Golay_filter#Tables_of_selected_convolution_coefficients
  // first derivative using linear/quadratic polynomial
  private static final double[] COEFFS = new double[]{-4, -3, -2, -1, 0, 1, 2, 3, 4};

  private FixedSizeHistory<Double> lVelocityHistory;
  private FixedSizeHistory<Double> rVelocityHistory;
  private double[] lHistArr;
  private double[] rHistArr;
  private Double[] lHistWrapper;
  private Double[] rHistWrapper;
  private double lErrorAccum;
  private double rErrorAccum;
  private double lastThrotL;
  private double lastThrotR;

  public AdvancedDrive() {
    requires(driveTrain);
    SmartDashboard.putNumber("Drive P-Value", 0.01);
  }

  @Override
  protected void initialize() {
    // check if output is odd and within the number of coeffs we have, otherwise use a default
    int velHistorySize = getVelHistorySize();

    if (velHistorySize % 2 == 0) {
      DriverStation.reportWarning("Velocity history size should be an odd number, but was "
          + velHistorySize
          + "; using fallback value", false);
      velHistorySize = 5;
    }
    if (velHistorySize > 9) {
      DriverStation.reportWarning("Velocity history size should be less than 9, but was "
          + velHistorySize
          + "; using fallback value", false);
      velHistorySize = 5;
    }
    // re-instantiate everything
    lVelocityHistory = new FixedSizeHistory<>(velHistorySize);
    rVelocityHistory = new FixedSizeHistory<>(velHistorySize);

    lHistWrapper = new Double[velHistorySize];
    rHistWrapper = new Double[velHistorySize];

    lHistArr = new double[velHistorySize];
    rHistArr = new double[velHistorySize];

    lErrorAccum = 0;
    rErrorAccum = 0;

    lastThrotL = 0;
    lastThrotR = 0;
  }

  @Override
  protected void execute() {
    double lThrot = OI.getDriveLeftJoystick();
    double rThrot = OI.getDriveRightJoystick();

    lVelocityHistory.add(driveTrain.getLeftVelocity());
    rVelocityHistory.add(driveTrain.getRightVelocity());

    lHistWrapper = lVelocityHistory.getHistoryAsArray(lHistWrapper);
    rHistWrapper = rVelocityHistory.getHistoryAsArray(rHistWrapper);

    // unbox to primitives
    Arrays.setAll(lHistArr, i -> lHistWrapper[i]);
    Arrays.setAll(rHistArr, i -> rHistWrapper[i]);

    // Get derivative using Savitsky-Golay Filter
    // this also has the effect of smoothing data
    double left = SavitskyGolayFilter.filter(lHistArr, COEFFS);
    double right = SavitskyGolayFilter.filter(rHistArr, COEFFS);

    // put to the dashboard for debugging
    SmartDashboard.putNumber("Left Acc", left);
    SmartDashboard.putNumber("Right Acc", right);

    // units: revolutions per minute^2
    double p = SmartDashboard.getNumber("Drive P-Value", 0.01);

    // TODO: find approx coefficients given drivetrain spec
    lErrorAccum += (lThrot - lastThrotL) - left;
    rErrorAccum += (rThrot - lastThrotR) - right;

    double lOutput = p * (Math.signum(lErrorAccum) - (1 / lErrorAccum));
    double rOutput = p * (Math.signum(rErrorAccum) - (1 / rErrorAccum));

    driveTrain.setLeftMotors(lOutput);
    driveTrain.setRightMotors(rOutput);
  }

  @Override
  protected void end() {

  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
