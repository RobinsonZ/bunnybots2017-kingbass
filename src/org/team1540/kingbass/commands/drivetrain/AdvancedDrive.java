package org.team1540.kingbass.commands.drivetrain;

import static org.team1540.kingbass.Robot.driveTrain;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.OI;
import org.team1540.kingbass.Tuning;

/**
 * Drive using concepts from a <a href="https://www.chiefdelphi.com/media/papers/3402">paper</a> by
 * FRC Team 449 the Blair Robot Project.
 */
public class AdvancedDrive extends Command {
  private static AdvancedDrive instance;

  private AdvancedDrive() {
    requires(driveTrain);
  }

  public static AdvancedDrive getInstance() {
    if (instance == null) { instance = new AdvancedDrive(); }

    return instance;
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    double leftStick = OI.getDriveLeftJoystick();
    double rightStick = OI.getDriveRightJoystick();
    // create separate things in order to make signs work correctly
    double leftVIntercept = Math.copySign(Tuning.vIntercept, leftStick);
    double rightVIntercept = Math.copySign(Tuning.vIntercept, rightStick);

    double leftVoltage = ((Tuning.kV * leftStick) + Tuning.vIntercept);
    driveTrain.setLeftVoltage(leftVoltage);

    double rightVoltage = ((Tuning.kV * rightStick) + Tuning.vIntercept);
    driveTrain.setRightVoltage(rightVoltage);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
