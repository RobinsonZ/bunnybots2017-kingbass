package org.team1540.kingbass.commands.groups;

import static org.team1540.kingbass.Tuning.clawEndPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team1540.kingbass.Tuning;
import org.team1540.kingbass.commands.arm.MoveArmToPosition;
import org.team1540.kingbass.commands.claw.MoveClawToPosition;

public class GrabBucket extends CommandGroup {
  public GrabBucket() {
    //TODO: Find correct numbers
    addSequential(new MoveClawToPosition(clawEndPoint));
    addSequential(new MoveArmToPosition(Tuning.grabBucketPosition));
  }
}
