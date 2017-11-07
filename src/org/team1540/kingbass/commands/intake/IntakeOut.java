package org.team1540.kingbass.commands.intake;

import org.team1540.kingbass.Robot;
import org.team1540.kingbass.Tuning;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Runs the intake outwards.
 * 
 * @author Zachary Robinson
 *
 */
public class IntakeOut extends Command {

  /** Constructs an {@link IntakeOut}. */
  public IntakeOut() {
    super("Run Intake Out");
    requires(Robot.intake);
  }

  @Override
  protected void initialize() {
    Robot.intake.setMotor(-Tuning.getIntakeSetPoint());
  }

  @Override
  protected void interrupted() {
    Robot.intake.setMotor(0);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
