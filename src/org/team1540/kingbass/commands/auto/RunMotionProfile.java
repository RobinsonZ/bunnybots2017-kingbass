package org.team1540.kingbass.commands.auto;

import static org.team1540.kingbass.Robot.driveTrain;
import static org.team1540.kingbass.Tuning.profileD;
import static org.team1540.kingbass.Tuning.profileF;
import static org.team1540.kingbass.Tuning.profileI;
import static org.team1540.kingbass.Tuning.profileP;

import org.team1540.base.ChickenCommand;
import org.team1540.kingbass.motion.MotionProfileLoader;

public class RunMotionProfile extends ChickenCommand {
  boolean done;
  String profile;

  public RunMotionProfile(String profile) {
    super("Run motion profile");
    addRequirement(driveTrain);
    setPriority(10);
    this.profile = profile;
  }


  @Override
  protected void end() {
    super.end();
    driveTrain.stopMp();
  }


  @Override
  protected void execute() {
    done = driveTrain.controlMp();
  }


  @Override
  protected void initialize() {
    super.initialize();
    done = false;
    driveTrain.setPID(profileP, profileI, profileD, profileF);

    driveTrain.setMp(MotionProfileLoader.loadFromCSV(profile + "_left"),
        MotionProfileLoader.loadFromCSV(profile + "_left"));

    driveTrain.startMp();
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
