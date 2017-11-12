package org.team1540.kingbass.commands.claw;

import static org.team1540.kingbass.Robot.claw;

import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team1540.kingbass.Tuning;

/**
 * Opens the pneumatic claw.
 *
 * @author Zachary Robinson
 */
public class OpenClaw extends TimedCommand {

  /**
   * Constructs an {@link OpenClaw}.
   */
  public OpenClaw() {
    super("Open claw", Tuning.getOpenClawTime());
    requires(claw);
  }

  @Override
  protected void end() {
    claw.stop();
  }

  @Override
  protected void initialize() {
    claw.startRelease();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
