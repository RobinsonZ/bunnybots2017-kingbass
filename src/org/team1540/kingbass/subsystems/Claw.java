package org.team1540.kingbass.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1540.kingbass.RobotInfo;
import org.team1540.kingbass.commands.claw.StopClaw;

/**
 * Motorized claw.
 *
 * @author Zachary Robinson
 */
public class Claw extends Subsystem {
  private CANTalon left = new CANTalon(RobotInfo.L_CLAW);
  private CANTalon right = new CANTalon(RobotInfo.R_CLAW);

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
}
