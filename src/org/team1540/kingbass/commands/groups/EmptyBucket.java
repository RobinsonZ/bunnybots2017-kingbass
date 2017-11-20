package org.team1540.kingbass.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team1540.kingbass.commands.arm.MoveArmForTime;
import org.team1540.kingbass.commands.claw.OpenClawForTime;

public class EmptyBucket extends CommandGroup {
    public EmptyBucket() {
        addSequential(new MoveArmForTime(2, -1));
        addSequential(new OpenClawForTime(1));
        addSequential(new MoveArmForTime(2, 1));
    }
}
