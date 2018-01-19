package org.team1540.kingbass;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.base.wrappers.ChickenTalon;

public class MotorTestRobot extends IterativeRobot {
  private SendableChooser<Integer> chooser;
  private ChickenTalon[] talons = new ChickenTalon[]{
      new ChickenTalon(0),
      new ChickenTalon(1),
      new ChickenTalon(2),
      new ChickenTalon(3),
      new ChickenTalon(4),
      new ChickenTalon(5),
      new ChickenTalon(6),
      new ChickenTalon(7),
      new ChickenTalon(8),
      new ChickenTalon(9),
      new ChickenTalon(10),
      new ChickenTalon(11),
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
    ChickenTalon talon = talons[chooser.getSelected()];
    double output = SmartDashboard.getNumber("Motor Output", 0.5);

    if (joystick.getRawButton(1)) {
      talon.set(output);
    } else {
      talon.set(0);
    }
    SmartDashboard.putNumber("Current", talon.getOutputCurrent());
  }
}
