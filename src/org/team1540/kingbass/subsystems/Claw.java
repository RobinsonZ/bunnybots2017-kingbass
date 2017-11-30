package org.team1540.kingbass.subsystems;

import static org.team1540.kingbass.RobotInfo.L_CLAW;
import static org.team1540.kingbass.RobotInfo.R_CLAW;
import static org.team1540.kingbass.Tuning.clawD;
import static org.team1540.kingbass.Tuning.clawI;
import static org.team1540.kingbass.Tuning.clawP;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
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
    left.setInverted(true);
    right.enableBrakeMode(true);
    left.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    left.configEncoderCodesPerRev(1024);
    right.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    right.configEncoderCodesPerRev(1024);
    left.setP(clawP);
    left.setI(clawI);
    left.setD(clawD);
    right.setP(clawP);
    right.setI(clawI);
    right.setD(clawD);
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
