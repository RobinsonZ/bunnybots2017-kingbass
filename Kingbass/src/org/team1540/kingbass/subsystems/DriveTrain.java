package org.team1540.kingbass.subsystems;

import org.team1540.kingbass.RobotInfo;
import org.team1540.kingbass.Tuning;
import org.team1540.kingbass.commands.drivetrain.JoystickDrive;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 6-cim drive train.
 * 
 * @author Zachary Robinson
 *
 */
public class DriveTrain extends Subsystem {
  private CANTalon lMain = new CANTalon(RobotInfo.L_MASTER);
  private CANTalon lSlaveA = new CANTalon(RobotInfo.L_SLAVE_A);
  private CANTalon lSlaveB = new CANTalon(RobotInfo.L_SLAVE_B);

  private CANTalon rMain = new CANTalon(RobotInfo.R_MASTER);
  private CANTalon rSlaveA = new CANTalon(RobotInfo.R_SLAVE_A);
  private CANTalon rSlaveB = new CANTalon(RobotInfo.R_SLAVE_B);

  // various arrays to make code cleaner and easier to write
  private CANTalon[] talons = {lMain, rMain, lSlaveA, rSlaveA, lSlaveB, rSlaveB};
  private CANTalon[] mains = {lMain, rMain};
  private CANTalon[] lSlaves = {lSlaveA, lSlaveB};
  private CANTalon[] rSlaves = {rSlaveA, rSlaveB};

  public DriveTrain() {

    for (CANTalon c : talons) {
      c.setVoltageRampRate(0);
      c.enableBrakeMode(true);
    }
    for (CANTalon c : mains) {
      c.setFeedbackDevice(FeedbackDevice.QuadEncoder);
      c.setProfile(0);
    }

    lMain.reverseOutput(Tuning.lReverseOutput());
    rMain.reverseOutput(Tuning.rReverseOutput());

    lMain.reverseSensor(Tuning.lReverseSensor());
    rMain.reverseSensor(Tuning.rReverseSensor());
    
    lMain.changeMotionControlFramePeriod(5);
    rMain.changeMotionControlFramePeriod(5);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new JoystickDrive());
  }

  /**
   * Sets the speed of the right-side motors.
   * 
   * @param setPoint The speed of the right-side motors, from -1 to 1 inclusive.
   */
  public void setRightMotors(double setPoint) {
    groupTalons();
    rMain.set(setPoint);
  }

  /**
   * Sets the speed of the left-side motors.
   * 
   * @param setPoint The speed of the right-side motors, from -1 to 1 inclusive.
   */
  public void setLeftMotors(double setPoint) {
    groupTalons();
    lMain.set(setPoint);
  }

  @SuppressWarnings("unused")
  private void makeTalonsIndependent() {
    for (CANTalon c : talons) {
      c.changeControlMode(TalonControlMode.PercentVbus);
    }
  }

  private void groupTalons() {
    lMain.changeControlMode(TalonControlMode.PercentVbus);
    for (CANTalon c : lSlaves) {
      c.changeControlMode(TalonControlMode.Follower);
      c.set(lMain.getDeviceID());
    }

    rMain.changeControlMode(TalonControlMode.PercentVbus);
    for (CANTalon c : rSlaves) {
      c.changeControlMode(TalonControlMode.Follower);
      c.set(rMain.getDeviceID());
    }
  }

  /** Gets the output current of each of the talons, averaged. */
  public double getAvgCurrentDraw() {
    double totDraw = 0;

    for (CANTalon c : talons) {
      totDraw += c.getOutputCurrent();
    }

    return totDraw / 6;
  }

  public CANTalon getLeftMainTalon() {
    return lMain;
  }

  public CANTalon getRightMainTalon() {
    return rMain;
  }
  
  public void setPID(double p, double i, double d, double f) {
    lMain.setPID(p, i, d);
    rMain.setPID(p, i, d);
    lMain.setF(f);
    rMain.setF(f);
  }
  
  public void processMpBuffer() {
    lMain.processMotionProfileBuffer();
    rMain.processMotionProfileBuffer();
  }

}
