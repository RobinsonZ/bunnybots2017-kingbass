package org.team1540.kingbass.commands.drivetrain;

import static org.team1540.base.Utilities.processAxisDeadzone;
import static org.team1540.kingbass.OI.getDriveLeftJoystick;
import static org.team1540.kingbass.OI.getDriveRightJoystick;
import static org.team1540.kingbass.Robot.driveTrain;
import static org.team1540.kingbass.Tuning.deadzone;

import org.team1540.base.ChickenCommand;

/**
 * Drives the robot using the left and right joysticks to control motor speed.
 *
 * @author Zachary Robinson
 */
public class JoystickDrive extends ChickenCommand {
  public JoystickDrive() {
    super("Drive with joysticks");
    addRequirement(driveTrain);
    setPriority(10);
  }

  @Override
  protected void execute() {
    driveTrain.setLeftMotors(processAxisDeadzone(getDriveLeftJoystick(),
        deadzone));
    driveTrain.setRightMotors(processAxisDeadzone(getDriveRightJoystick(),
        deadzone));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
