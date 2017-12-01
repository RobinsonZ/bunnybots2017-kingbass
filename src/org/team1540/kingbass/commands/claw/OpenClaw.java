package org.team1540.kingbass.commands.claw;

import static org.team1540.kingbass.Robot.claw;
import static org.team1540.kingbass.Tuning.clawD;
import static org.team1540.kingbass.Tuning.clawI;
import static org.team1540.kingbass.Tuning.clawP;
import static org.team1540.lib.motionprofile.CSVProfileLoader.loadFromCSV;

import edu.wpi.first.wpilibj.command.Command;

public class OpenClaw extends Command {
  double[][] leftProfile = loadFromCSV("/home/lvuser/clawprofiles/claw_open_left.csv");
  double[][] rightProfile = loadFromCSV("/home/lvuser/clawprofiles/claw_open_right.csv");
  boolean done;

  public OpenClaw() {
    requires(claw);
  }

  @Override
  protected void initialize() {
    claw.setPID(clawP, clawI, clawD);
    done = false;

    claw.setMp(leftProfile, rightProfile);

    claw.startMp();
  }

  @Override
  protected void execute() {
    done = claw.controlMp();
  }


  @Override
  protected void end() {
    claw.stopMp();
  }


  @Override
  protected boolean isFinished() {
    return done;
  }
}
