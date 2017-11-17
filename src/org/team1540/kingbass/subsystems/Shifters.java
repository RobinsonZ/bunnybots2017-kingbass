package org.team1540.kingbass.subsystems;

import static org.team1540.kingbass.RobotInfo.L_SHIFTER;
import static org.team1540.kingbass.RobotInfo.R_SHIFTER;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Pneumatic gearbox shifters.
 *
 * @author Zachary Robinson
 */
public class Shifters extends Subsystem {
  private Solenoid left = new Solenoid(L_SHIFTER);
  private Solenoid right = new Solenoid(R_SHIFTER);

  /**
   * Shifts to low gear.
   */
  public void shiftDown() {
    left.set(false);
    right.set(false);
  }

  /**
   * Shifts to high gear.
   */
  public void shiftUp() {
    left.set(true);
    right.set(true);
  }

  @Override
  protected void initDefaultCommand() {}
}
