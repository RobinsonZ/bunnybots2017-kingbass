package org.team1540.kingbass.commands.arm;

import static org.team1540.kingbass.Robot.arm;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command disables the software limits on the arm position. In a perfect world this shouldn't
 * exist, but there's a chance of the arm being zeroed improperly. If that happens, the drivers can
 * cci
 */
public class DisableArmLimits extends Command {
  @Override
  protected void initialize() {
    arm.setUseLimits(false);
  }

  @Override
  protected void end() {
    arm.setUseLimits(true);
  }

  @Override
  protected boolean isFinished() {
    return false; // disable via the SmartDashboard
  }
}
