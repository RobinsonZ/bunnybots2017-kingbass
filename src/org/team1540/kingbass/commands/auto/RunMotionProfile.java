package org.team1540.kingbass.commands.auto;

import static org.team1540.kingbass.Robot.driveTrain;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.kingbass.Tuning;
import org.team1540.kingbass.motion.MotionProfileLoader;

public class RunMotionProfile extends Command {
  boolean done;
  String profile;

  public RunMotionProfile(String profile) {
    super("Run motion profile");
    requires(driveTrain);
    this.profile = profile;
  }


  @Override
  protected void end() {
    driveTrain.stopMp();
  }


  @Override
  protected void execute() {
    done = driveTrain.controlMp();
  }


  @Override
  protected void initialize() {
    done = false;
    driveTrain.setPID(Tuning.getProfileP(), Tuning.getProfileI(), Tuning.getProfileD(),
        Tuning.getProfileF());

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
