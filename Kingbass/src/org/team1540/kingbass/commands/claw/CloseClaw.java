package org.team1540.kingbass.commands.claw;

import org.team1540.kingbass.Robot;
import org.team1540.kingbass.Tuning;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Closes the pneumatic claw.
 * 
 * @author Zachary Robinson
 *
 */
public class CloseClaw extends Command {
  Timer t = new Timer();

  /** Constructs a {@link CloseClaw}. */
  public CloseClaw() {
    super("Close claw");
    requires(Robot.claw);
  }

  @Override
  protected void initialize() {
    t.reset();
    t.start();
    Robot.claw.startGrab();
  }

  @Override
  protected boolean isFinished() {
    return t.hasPeriodPassed(Tuning.CLOSE_CLAW_TIME);
  }

  @Override
  protected void end() {
    Robot.claw.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }

}
