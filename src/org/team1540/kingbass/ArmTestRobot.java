package org.team1540.kingbass;

import static org.team1540.base.Utilities.processAxisDeadzone;
import static org.team1540.kingbass.OI.ARM_AXIS;
import static org.team1540.kingbass.OI.copilot;
import static org.team1540.kingbass.Tuning.armMult;
import static org.team1540.kingbass.Tuning.deadzone;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.base.adjustables.AdjustableManager;
import org.team1540.kingbass.subsystems.Arm;

public class ArmTestRobot extends IterativeRobot {
  private Arm arm;
  private JoystickButton button;
  private double position = 0;

  @Override
  public void robotInit() {
    AdjustableManager.getInstance().add(new Tuning());
    arm = new Arm();
    button = new JoystickButton(new Joystick(0), 1);
    SmartDashboard.putNumber("Position setpoint", arm.getPosition());
  }

  @Override
  public void robotPeriodic() {
    arm.updatePIDs();
    AdjustableManager.getInstance().update();
    SmartDashboard.putNumber("A position", arm.getPosition());
    SmartDashboard.putNumber("A current", arm.getCurrentA());
    SmartDashboard.putNumber("B current", arm.getCurrentB());
    SmartDashboard.putBoolean("Current limited", arm.isCurrentLimited());
    if (button.get()) {
      arm.zeroPosition();
    }
  }

  @Override
  public void teleopPeriodic() {
    arm.setPosition(
        position += processAxisDeadzone(copilot.getRawAxis(ARM_AXIS), deadzone) * armMult);
    SmartDashboard.putNumber("Position setpoint", position);
    if (button.get()) {
      arm.setPosition(0);
    }
  }
}
