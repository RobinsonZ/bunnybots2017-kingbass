package org.team1540.kingbass.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team1540.kingbass.commands.arm.MoveArmForTime;
import org.team1540.kingbass.commands.claw.CloseClawForTime;

public class GrabBucket extends CommandGroup {
    public GrabBucket() {
        addSequential(new CloseClawForTime(2));
        addSequential(new MoveArmForTime(.5, -1));
    }
}
