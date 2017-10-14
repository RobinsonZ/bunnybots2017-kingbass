package org.team1540.kingbass.commands.claw;

import org.team1540.kingbass.Robot;
import org.team1540.kingbass.Tuning;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Closes the pneumatic claw.
 * 
 * @author Zachary Robinson
 *
 */
public class CloseClaw extends TimedCommand {
  Timer t = new Timer();

  /** Constructs a {@link CloseClaw}. */
  public CloseClaw() {
    super("Close claw", Tuning.getCloseClawTime());
    requires(Robot.claw);
  }

  @Override
  protected void initialize() {
    Robot.claw.startGrab();
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
