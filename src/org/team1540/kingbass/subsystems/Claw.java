package org.team1540.kingbass.subsystems;

import static org.team1540.kingbass.RobotInfo.L_CLAW;
import static org.team1540.kingbass.RobotInfo.R_CLAW;

import com.ctre.CANTalon;
import org.team1540.base.ChickenSubsystem;
import org.team1540.kingbass.commands.claw.StopClaw;

/**
 * Motorized claw.
 *
 * @author Zachary Robinson
 */
public class Claw extends ChickenSubsystem {
  private CANTalon left = new CANTalon(L_CLAW);
  private CANTalon right = new CANTalon(R_CLAW);

  public Claw() {
    super();
    left.enableBrakeMode(true);
    left.enableBrakeMode(true);
  }

  /**
   * Closes the claw.
   */
  public void startGrab() {
    left.set(1);
    right.set(1);
  }

  /**
   * Opens the claw.
   */
  public void startRelease() {
    left.set(-1);
    right.set(-1);
  }

  public void stop() {
    left.set(0);
    right.set(0);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new StopClaw());
  }

  @Override
  public double getCurrent() {
    return left.getOutputCurrent() + right.getOutputCurrent();
  }

  @Override
  public void setAbsolutePowerLimit(double limit) {
    left.setCurrentLimit((int) limit);
    right.setCurrentLimit((int) limit);

    // why are these uppercase
    left.EnableCurrentLimit(true);
    right.EnableCurrentLimit(true);
  }

  @Override
  public void stopLimitingPower() {
    left.EnableCurrentLimit(false);
    right.EnableCurrentLimit(false);
  }
}
