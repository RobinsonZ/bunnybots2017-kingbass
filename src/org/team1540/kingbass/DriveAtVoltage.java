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
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class DriveAtVoltage extends IterativeRobot {

  private PrintStream leftCSV;
  private PrintStream rightCSV;
  private long lastTime;
  private int runVoltage = 0;

  private CANTalon lMain = new CANTalon(L_MASTER);
  private CANTalon lSlaveA = new CANTalon(L_SLAVE_A);
  private CANTalon lSlaveB = new CANTalon(L_SLAVE_B);

  private CANTalon rMain = new CANTalon(R_MASTER);
  private CANTalon rSlaveA = new CANTalon(R_SLAVE_A);
  private CANTalon rSlaveB = new CANTalon(R_SLAVE_B);
  private JoystickButton button;
  private Button stickA;

  @Override
  public void robotInit() {
    SmartDashboard.putNumber("Voltage", runVoltage);
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

    Joystick stick = new Joystick(0);
    stickA = new JoystickButton(stick, 1);

    button = new JoystickButton(stick, 1);
    button.whenPressed(new RunMotors());
  }

  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
  }

  private class RunMotors extends Command {
    @Override
    protected void initialize() {
      long ts = System.currentTimeMillis();
      try {
        leftCSV = new PrintStream(new File("/home/lvuser/advanced-drive/" + ts + "left.csv"));
        rightCSV = new PrintStream(new File("/home/lvuser/advanced-drive/" + ts + "right.csv"));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      // print headers
      leftCSV.println("Voltage,Velocity");
      rightCSV.println("Voltage,Velocity");
    }

    @Override
    protected void execute() {

      lMain.set(runVoltage);
      rMain.set(runVoltage);
      double actualVoltageLeft = lMain.getOutputVoltage();
      double actualVoltageRight = rMain.getOutputVoltage();

      double leftVel = lMain.getEncVelocity();
      double rightVel = rMain.getEncVelocity();

      // write data to files
      leftCSV.println(actualVoltageLeft + "," + leftVel);
      rightCSV.println(actualVoltageRight + "," + rightVel);
    }

    @Override
    protected boolean isFinished() {
      return (!stickA.get());
    }

    @Override
    protected void end() {
      leftCSV.close();
      rightCSV.close();
    }
  }
}