package org.team1540.kingbass.commands.auto;

import static org.team1540.kingbass.Tuning.clawLimit;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;
import org.team1540.kingbass.commands.claw.MoveClawToPosition;

public class RunAutonomous extends CommandGroup {
  public RunAutonomous() {
    addParallel(new MoveClawToPosition(clawLimit)); // open claw
    addSequential(new TimedCommand(0.5)); // wait a sec for it to work
  }
}
