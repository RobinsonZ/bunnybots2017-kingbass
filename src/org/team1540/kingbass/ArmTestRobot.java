package org.team1540.kingbass;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.kingbass.subsystems.Arm;

public class ArmTestRobot extends IterativeRobot {
  private Arm arm;
  private JoystickButton button;


  @Override
  public void robotInit() {
    arm = new Arm();
    button = new JoystickButton(new Joystick(0), 1);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("A position", arm.getPositionA());
    SmartDashboard.putNumber("B position", arm.getPositionB());
    if (button.get()) { arm.zeroPosition(); }
  }
}
