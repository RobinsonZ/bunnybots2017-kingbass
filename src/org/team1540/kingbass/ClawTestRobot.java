package org.team1540.kingbass;

import static org.team1540.base.Utilities.processAxisDeadzone;
import static org.team1540.kingbass.OI.ARM_AXIS;
import static org.team1540.kingbass.OI.copilot;
import static org.team1540.kingbass.Robot.claw;
import static org.team1540.kingbass.Tuning.armMult;
import static org.team1540.kingbass.Tuning.deadzone;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.base.adjustables.AdjustableManager;

public class ClawTestRobot extends IterativeRobot {
  private JoystickButton button;
  private double position = 0;

  @Override
  public void robotInit() {
    AdjustableManager.getInstance().add(new Tuning());
    Joystick joystick = new Joystick(1);
    button = new JoystickButton(joystick, 3);
    SmartDashboard.putNumber("Position setpoint", claw.getPosition());

    Button copilotA = new JoystickButton(joystick, 1);
    Button copilotB = new JoystickButton(joystick, 2);
  }

  @Override
  public void robotPeriodic() {
    claw.updatePIDs();
    AdjustableManager.getInstance().update();
    SmartDashboard.putNumber("L Position", claw.getLeftPosition());
    SmartDashboard.putNumber("R position", claw.getRightPosition());
    if (button.get()) {
      claw.zeroPosition();
    }
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() {
    claw.setPosition(
        position += processAxisDeadzone(copilot.getRawAxis(ARM_AXIS), deadzone) * armMult);
    SmartDashboard.putNumber("Position setpoint", position);
    if (button.get()) {
      claw.setPosition(0);
    }
  }
}
