package org.team1540.kingbass.commands.auto;

import static org.team1540.kingbass.Tuning.autoBackDistance;
import static org.team1540.kingbass.Tuning.autoDistance;
import static org.team1540.kingbass.Tuning.clawEndPoint;
import static org.team1540.kingbass.Tuning.clawLimit;
import static org.team1540.kingbass.Tuning.grabBucketPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team1540.kingbass.commands.arm.MoveArmToPosition;
import org.team1540.kingbass.commands.claw.MoveClawToPosition;

public class RunAutonomous extends CommandGroup {
  public RunAutonomous() {
    addParallel(new MoveClawToPosition(clawLimit)); // open claw
    addSequential(new TimedCommand(1)); // wait a sec for it to work

    addParallel(new MoveArmToPosition(0));
    addSequential(new PiDriveForward(autoDistance, 0.25));

    addSequential(new PiDriveForward(-autoBackDistance, 0.25));

    addParallel(new MoveClawToPosition(clawEndPoint));
    addSequential(new TimedCommand(1));

    addSequential(new MoveArmToPosition(grabBucketPosition));
  }
}
