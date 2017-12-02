package org.team1540.kingbass.commands.claw;

import static org.team1540.kingbass.Robot.claw;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Wrapper command to allow it to be a button on the SmartDashboard.
 */
public class ZeroClawPosition extends InstantCommand {

  public ZeroClawPosition() {
    setRunWhenDisabled(true);
  }
  @Override
  protected void initialize() {
    claw.zeroPosition();
  }
}
