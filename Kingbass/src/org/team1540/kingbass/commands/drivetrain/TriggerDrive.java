package org.team1540.kingbass.commands.drivetrain;

import org.team1540.kingbass.OI;
import org.team1540.kingbass.Robot;
import org.team1540.kingbass.Utilities;
import edu.wpi.first.wpilibj.command.Command;

/**
 * <b>Warning: Pretty much useless except maybe for a racing-based challenge.</b>
 * <p>
 * This is a form of "arcade drive" using the triggers and the x-axis of the driver's left joystick,
 * analogous to a car in a video game. The combined left and right joysticks set the speed, while
 * the x-axis determines the steering. For example, with the x-axis pushed halfway to the right and
 * the triggers at 0.5, the left motor will be running at 50% and the right motor at 0%; when the
 * joystick is all the way to the right, the motors will be running in opposite directions at 50%.
 * 
 * @author Zachary Robinson
 */
public class TriggerDrive extends Command {
  public TriggerDrive() {
    super("Drive with triggers");
    requires(Robot.driveTrain);
  }

  @Override
  public void execute() {
    double combinedTriggerValue = Utilities.processAxisDeadzone(OI.getDriveRightTrigger())
        - Utilities.processAxisDeadzone(OI.getDriveLeftTrigger());

    // motors[0] is left, motors[1] is right
    double[] motors = processSteering(Utilities.processAxisDeadzone(OI.getDriveLeftJoystickX()),
        combinedTriggerValue);

    Robot.driveTrain.setLeftMotors(motors[0]);
    Robot.driveTrain.setRightMotors(motors[1]);
  }

  private static double[] processSteering(double xAxis, double motorValue) {
    double[] motors = new double[2];

    if (xAxis <= 0) {
      motors[0] = ((xAxis + 0.5) * 2) * motorValue;
      motors[1] = motorValue;
    } else {
      motors[0] = motorValue;
      motors[1] = ((-xAxis + 0.5) * 2) * motorValue;
    }

    return motors;
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

}
