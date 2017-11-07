package org.team1540.kingbass.commands.auto;

import org.team1540.kingbass.Robot;
import org.team1540.kingbass.Tuning;
import org.team1540.kingbass.motion.MotionProfileLoader;
import org.team1540.kingbass.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;

public class RunMotionProfile extends Command {
  boolean done;
  String profile;

  // makes code less ugly
  DriveTrain dt = Robot.driveTrain;

  public RunMotionProfile(String profile) {
    super("Run motion profile");
    requires(Robot.driveTrain);
    this.profile = profile;
  }



  @Override
  protected void end() {
    dt.stopMp();
  }



  @Override
  protected void execute() {
    done = dt.controlMp();
  }



  @Override
  protected void initialize() {
    done = false;
    dt.setPID(Tuning.getProfileP(), Tuning.getProfileI(), Tuning.getProfileD(),
        Tuning.getProfileF());
    
    dt.setMp(MotionProfileLoader.loadFromCSV(profile + "_left"),
        MotionProfileLoader.loadFromCSV(profile + "_left"));

    dt.startMp();
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
