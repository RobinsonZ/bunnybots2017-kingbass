package org.team1540.kingbass;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.kingbass.subsystems.Claw;

public class ClawTestRobot extends IterativeRobot {

  private Claw claw;
  private JoystickButton aButton;

  @Override
  public void robotInit() {
    claw = new Claw();
    aButton = new JoystickButton(new Joystick(0), 1);
  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("Left position", claw.getLeftPosition());
    SmartDashboard.putNumber("Right position", claw.getRightPosition());
    if (aButton.get()) {
      claw.zeroPosition();
    }
  }
}
