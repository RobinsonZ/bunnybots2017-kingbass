package org.team1540.kingbass.commands.claw;

import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team1540.kingbass.Robot;

/**
 *
 */
public class CloseClawForTime extends TimedCommand {

    public CloseClawForTime(double timeout) {
        super(timeout);
        requires(Robot.claw);
    }


    protected void execute() {
        Robot.claw.startGrab();
    }

    @Override
    protected void end() {
        Robot.claw.stop();
    }
}