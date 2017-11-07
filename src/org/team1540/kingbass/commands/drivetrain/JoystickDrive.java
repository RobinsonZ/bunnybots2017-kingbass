package org.team1540.kingbass.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.OI;
import org.team1540.kingbass.Robot;
import org.team1540.kingbass.Tuning;
import org.team1540.lib.Utilities;

/**
 * Drives the robot using the left and right joysticks to control motor speed.
 *
 * @author Zachary Robinson
 */
public class JoystickDrive extends Command {
  public JoystickDrive() {
    super("Drive with joysticks");
    requires(Robot.driveTrain);
  }

  @Override
  protected void execute() {
    Robot.driveTrain.setLeftMotors(Utilities.processAxisDeadzone(OI.getDriveLeftJoystick(),
        Tuning.getDeadzone()));
    Robot.driveTrain.setRightMotors(Utilities.processAxisDeadzone(OI.getDriveRightJoystick(),
        Tuning.getDeadzone()));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
