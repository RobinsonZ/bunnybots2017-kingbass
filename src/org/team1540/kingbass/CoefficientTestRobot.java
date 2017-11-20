package org.team1540.kingbass;

import static com.ctre.CANTalon.TalonControlMode.Follower;
import static com.ctre.CANTalon.TalonControlMode.Voltage;
import static org.team1540.kingbass.RobotInfo.L_MASTER;
import static org.team1540.kingbass.RobotInfo.L_SLAVE_A;
import static org.team1540.kingbass.RobotInfo.L_SLAVE_B;
import static org.team1540.kingbass.RobotInfo.R_MASTER;
import static org.team1540.kingbass.RobotInfo.R_SLAVE_A;
import static org.team1540.kingbass.RobotInfo.R_SLAVE_B;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import org.team1540.kingbass.commands.drivetrain.AdvancedDrive;

/**
 * This robot class can be run to find coefficients for the {@link AdvancedDrive} command. It
 * outputs its values to a .csv file on the RoboRIO that can be offloaded for further analysis.
 */
public class CoefficientTestRobot extends IterativeRobot {
  private CANTalon lMain = new CANTalon(L_MASTER);
  private CANTalon lSlaveA = new CANTalon(L_SLAVE_A);
  private CANTalon lSlaveB = new CANTalon(L_SLAVE_B);

  private CANTalon rMain = new CANTalon(R_MASTER);
  private CANTalon rSlaveA = new CANTalon(R_SLAVE_A);
  private CANTalon rSlaveB = new CANTalon(R_SLAVE_B);


  private double currentAppliedVoltage;
  private double RAMP_RATE = 0.00025;
  private PrintStream leftCSV;
  private PrintStream rightCSV;
  private long lastTime;

  @Override
  public void robotInit() {
    lMain.changeControlMode(Voltage);
    rMain.changeControlMode(Voltage);

    lSlaveA.changeControlMode(Follower);
    lSlaveA.set(lMain.getDeviceID());

    lSlaveB.changeControlMode(Follower);
    lSlaveB.set(lMain.getDeviceID());

    rSlaveA.changeControlMode(Follower);
    rSlaveA.set(rMain.getDeviceID());

    rSlaveB.changeControlMode(Follower);
    rSlaveB.set(rMain.getDeviceID());
  }

  @Override
  public void teleopInit() {
    long ts = System.currentTimeMillis(); // used for making filenames unique
    try {
      leftCSV = new PrintStream(new File("/home/lvuser/advanced-drive/left" + ts + ".csv"));
      rightCSV = new PrintStream(new File("/home/lvuser/advanced-drive/right" + ts + ".csv"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


    lastTime = System.currentTimeMillis();
    currentAppliedVoltage = 0;
  }

  @Override
  public void teleopPeriodic() {
    double actualVoltageLeft = lMain.getOutputVoltage();
    double actualVoltageRight = rMain.getOutputVoltage();

    double leftVel = lMain.getEncVelocity();
    double rightVel = rMain.getEncVelocity();

    // write data to files
    leftCSV.println(actualVoltageLeft + "," + leftVel);
    rightCSV.println(actualVoltageRight + "," + rightVel);

    long thisTime = System.currentTimeMillis();
    long tDelta = thisTime - lastTime;

    lastTime = thisTime;

    double vIncrease = RAMP_RATE * tDelta;
    currentAppliedVoltage += vIncrease;
    lMain.set(currentAppliedVoltage);
    rMain.set(currentAppliedVoltage);
  }
}
