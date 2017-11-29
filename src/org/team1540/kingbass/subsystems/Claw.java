package org.team1540.kingbass.subsystems;

import static org.team1540.kingbass.RobotInfo.L_CLAW;
import static org.team1540.kingbass.RobotInfo.R_CLAW;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.kingbass.commands.claw.TriggerClawControl;

/**
 * Motorized claw.
 *
 * @author Zachary Robinson
 */
public class Claw extends Subsystem {
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

  public void set(double value) {
    left.set(value);
    right.set(value);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new TriggerClawControl());
  }
}
