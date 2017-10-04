package org.team1540.kingbass.commands.claw;

import org.team1540.kingbass.Robot;
import org.team1540.kingbass.Tuning;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Opens the pneumatic claw.
 * 
 * @author Zachary Robinson
 *
 */
public class OpenClaw extends Command {
  Timer t = new Timer();

  /** Constructs an {@link OpenClaw}. */
  public OpenClaw() {
    super("Open claw");
    requires(Robot.claw);
  }

  @Override
  protected void initialize() {
    t.reset();
    t.start();
    Robot.claw.startRelease();
  }

  @Override
  protected boolean isFinished() {
    return t.hasPeriodPassed(Tuning.getOpenClawTime());
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
