package org.team1540.kingbass.commands.auto;

import org.team1540.kingbass.Robot;
import org.team1540.kingbass.motion.MotionProfiles;
import org.team1540.kingbass.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;

public class RunMotionProfile extends Command {
  boolean done;

  // makes code less ugly
  DriveTrain dt = Robot.driveTrain;

  public RunMotionProfile() {
    super("Run motion profile");
    requires(Robot.driveTrain);
  }



  @Override
  protected void initialize() {
    done = false;
    dt.setMp(MotionProfiles.left1, MotionProfiles.right1);
    dt.startMp();
  }



  @Override
  protected void execute() {
    done = dt.controlMp();
  }



  @Override
  protected void end() {
    dt.stopMp();
  }



  @Override
  protected void interrupted() {
    end();
  }



  @Override
  protected boolean isFinished() {
    return done;
  }

}
