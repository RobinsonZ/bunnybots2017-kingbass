package org.team1540.kingbass.subsystems;

import org.team1540.kingbass.RobotInfo;
import org.team1540.kingbass.commands.intake.IntakeStop;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Intake for bunnies.
 * 
 * @author Zachary Robinson
 */
public class Intake extends Subsystem {
  private CANTalon intake = new CANTalon(RobotInfo.INTAKE);

  /** Gets the output current of the intake motor. */
  public double getCurrent() {
    return intake.getOutputCurrent();
  }

  /**
   * Set the intake motor's speed.
   * 
   * @param setPoint The point to set the motor to, between -1 and 1 inclusive.
   */
  public void setMotor(double setPoint) {
    intake.changeControlMode(TalonControlMode.PercentVbus);
    intake.set(setPoint);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new IntakeStop());
  }

}
