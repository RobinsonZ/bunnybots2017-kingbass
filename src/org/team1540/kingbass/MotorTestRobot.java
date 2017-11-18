package org.team1540.kingbass;


import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotorTestRobot extends IterativeRobot {
  private SendableChooser<Integer> chooser;
  private CANTalon[] talons = new CANTalon[]{
      new CANTalon(0),
      new CANTalon(1),
      new CANTalon(2),
      new CANTalon(3),
      new CANTalon(4),
      new CANTalon(5),
      new CANTalon(6),
      new CANTalon(7),
      new CANTalon(8),
      new CANTalon(9),
      new CANTalon(10),
      new CANTalon(11),
  };
  private Joystick joystick = new Joystick(0);

  @Override
  public void robotInit() {
    chooser = new SendableChooser<>();
    chooser.addDefault("Motor 0", 1);
    chooser.addObject("Motor 1", 2);
    chooser.addObject("Motor 2", 3);
    chooser.addObject("Motor 3", 3);
    chooser.addObject("Motor 4", 4);
    chooser.addObject("Motor 5", 5);
    chooser.addObject("Motor 6", 6);
    chooser.addObject("Motor 7", 7);
    chooser.addObject("Motor 8", 8);
    chooser.addObject("Motor 9", 9);
    chooser.addObject("Motor 10", 10);
    chooser.addObject("Motor 11", 11);
    chooser.addObject("Motor 12", 12);

    SmartDashboard.putNumber("Motor Output", 0.5);
    SmartDashboard.putData("Motor", chooser);
  }

  @Override
  public void teleopPeriodic() {
    CANTalon talon = talons[chooser.getSelected()];
    double output = SmartDashboard.getNumber("Motor Output", 0.5);

    if (joystick.getRawButton(1)) {
      talon.set(output);
    } else {
      talon.set(0);
    }
  }
}
