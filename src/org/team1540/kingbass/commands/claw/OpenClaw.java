package org.team1540.kingbass.commands.claw;

import org.team1540.kingbass.Robot;
import org.team1540.kingbass.Tuning;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Opens the pneumatic claw.
 * 
 * @author Zachary Robinson
 *
 */
public class OpenClaw extends TimedCommand {

  /** Constructs an {@link OpenClaw}. */
  public OpenClaw() {
    super("Open claw", Tuning.getOpenClawTime());
    requires(Robot.claw);
  }

  @Override
  protected void end() {
    Robot.claw.stop();
  }
  
  @Override
  protected void initialize() {
    Robot.claw.startRelease();
  }

  @Override
  protected void interrupted() {
    end();
  }

}
