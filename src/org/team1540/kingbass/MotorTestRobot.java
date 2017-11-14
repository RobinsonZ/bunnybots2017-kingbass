package org.team1540.kingbass;


import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotorTestRobot extends IterativeRobot {
  private SendableChooser<CANTalon> talons;
  private Joystick joystick = new Joystick(0);

  @Override
  public void robotInit() {
    talons = new SendableChooser<>();
    talons.addDefault("Motor 0", new CANTalon(0));
    talons.addObject("Motor 1", new CANTalon(1));
    talons.addObject("Motor 2", new CANTalon(2));
    talons.addObject("Motor 3", new CANTalon(3));
    talons.addObject("Motor 4", new CANTalon(4));
    talons.addObject("Motor 5", new CANTalon(5));
    talons.addObject("Motor 6", new CANTalon(6));
    talons.addObject("Motor 7", new CANTalon(7));
    talons.addObject("Motor 8", new CANTalon(8));
    talons.addObject("Motor 9", new CANTalon(9));
    talons.addObject("Motor 10", new CANTalon(10));
    talons.addObject("Motor 11", new CANTalon(11));
    talons.addObject("Motor 12", new CANTalon(12));

    SmartDashboard.putNumber("Motor Output", 0.5);
  }

  @Override
  public void teleopPeriodic() {
    CANTalon talon = talons.getSelected();
    double output = SmartDashboard.getNumber("Motor Output", 0.5);

    if (joystick.getRawButton(0)) {
      talon.set(output);
    } else {
      talon.set(0);
    }
  }
}
