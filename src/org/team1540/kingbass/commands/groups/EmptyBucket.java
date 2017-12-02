package org.team1540.kingbass.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team1540.kingbass.commands.arm.MoveArmToPosition;

public class EmptyBucket extends CommandGroup {
  public EmptyBucket() {
    addSequential(new MoveArmToPosition(0.7));
    //addSequential(new OpenClawForTime(1));
    addSequential(new MoveArmToPosition(0));
  }
}
