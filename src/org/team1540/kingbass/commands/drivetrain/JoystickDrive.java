package org.team1540.kingbass.commands.drivetrain;

import static org.team1540.base.Utilities.processAxisDeadzone;
import static org.team1540.kingbass.OI.getDriveLeftJoystick;
import static org.team1540.kingbass.OI.getDriveRightJoystick;
import static org.team1540.kingbass.Robot.driveTrain;
import static org.team1540.kingbass.Tuning.deadzone;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.OI;

/**
 * Drives the robot using the left and right joysticks to control motor speed.
 *
 * @author Zachary Robinson
 */
public class JoystickDrive extends Command {

  public JoystickDrive() {
    super("Drive with joysticks");
    requires(driveTrain);
  }

  @Override
  protected void execute() {
    double triggerInput = OI.getDriveRightTrigger() - OI.getDriveLeftTrigger();

    double left = processAxisDeadzone(getDriveLeftJoystick(),
        deadzone);
    driveTrain.setLeftMotors(left + triggerInput);
    double right = processAxisDeadzone(getDriveRightJoystick(),
        deadzone);
    driveTrain.setRightMotors(right + triggerInput);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
