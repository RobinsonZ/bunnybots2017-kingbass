package org.team1540.kingbass.commands.auto;

import static org.team1540.kingbass.Robot.driveTrain;
import static org.team1540.kingbass.Tuning.driveD;
import static org.team1540.kingbass.Tuning.driveI;
import static org.team1540.kingbass.Tuning.driveP;
import static org.team1540.kingbass.Tuning.profileF;

import edu.wpi.first.wpilibj.command.Command;
import org.team1540.lib.motionprofile.CSVProfileLoader;

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
    driveTrain.setPID(driveP, driveI, driveD, profileF);

    driveTrain.setMp(CSVProfileLoader.loadFromCSV(profile + "_left"),
        CSVProfileLoader.loadFromCSV(profile + "_left"));

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
