package org.team1540.kingbass;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.base.adjustables.AdjustableManager;
import org.team1540.kingbass.subsystems.Arm;

public class ArmTestRobot extends IterativeRobot {
  private Arm arm;
  private JoystickButton button;


  @Override
  public void robotInit() {
    AdjustableManager.getInstance().add(new Tuning());
    arm = new Arm();
    button = new JoystickButton(new Joystick(0), 1);
    SmartDashboard.putNumber("Position setpoint", arm.getPositionA());
  }

  @Override
  public void robotPeriodic() {
    AdjustableManager.getInstance().update();
    SmartDashboard.putNumber("A position", arm.getPositionA());
    SmartDashboard.putNumber("A current", arm.getCurrentA());
    SmartDashboard.putNumber("B current", arm.getCurrentB());
    SmartDashboard.putBoolean("Current limited", arm.isCurrentLimited());
    if (button.get()) {
      arm.zeroPosition();
    }
  }

  @Override
  public void teleopPeriodic() {
    arm.setPosition(SmartDashboard.getNumber("Position setpoint", arm.getPositionA()));
    if (button.get()) {
      arm.setPosition(0);
    }
  }
}
