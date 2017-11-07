package org.team1540.kingbass.commands.shifters;

import org.team1540.kingbass.Robot;
import org.team1540.kingbass.Tuning;
import org.team1540.kingbass.Utilities;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses an auto-shifting algorithm to control the gearbox.
 * 
 * @author Zachary Robinson
 *
 */
public class AutoShift extends Command {
  private int weight;
  private boolean gear;

  private int ticksUntilShiftUpdate;


  public AutoShift() {
    super("Auto shift");
    requires(Robot.shifters);
  }

  @Override
  protected void execute() {
    // if (weight < Tuning.MAX_AUTO_SHIFTING_WEIGHT
    // && Robot.driveTrain.getAvgCurrentDraw() < Tuning.AUTO_SHIFT_DEVIATION_THRESHOLD) {
    // weight++;
    // if (weight > 0)
    // weight = Tuning.MAX_AUTO_SHIFTING_WEIGHT;
    // // Set it all the way over to prevent the drivetrain from flipping back and forth.
    // } else if (weight > -Tuning.MAX_AUTO_SHIFTING_WEIGHT
    // && Robot.driveTrain.getAvgCurrentDraw() >= Tuning.AUTO_SHIFT_DOWN_CURRENT_THRESHOLD) {
    // weight--;
    // if (weight <= 0)
    // weight = -Tuning.MAX_AUTO_SHIFTING_WEIGHT;
    // }
    //
    // if (weight > 0) {
    // Robot.shifters.shiftUp();
    // } else {
    // Robot.shifters.shiftDown();
    // }

    // process autoshifting weight
    if (Utilities.calcRPMDeviation(gear, Robot.driveTrain.getLeftMainTalon().get(),
        Robot.driveTrain.getLeftMainTalon()) > Tuning.getAutoShiftDeviationThreshold()
        || Utilities.calcRPMDeviation(gear, Robot.driveTrain.getRightMainTalon().get(),
            Robot.driveTrain.getRightMainTalon()) > Tuning.getAutoShiftDeviationThreshold()) {
      weight--;
    } else {
      weight++;
    }

    // every several ticks update the shifters
    if (ticksUntilShiftUpdate == 0) {
      if (weight < 0) {
        Robot.shifters.shiftDown();
      } else if (weight > 0) {
        Robot.shifters.shiftUp();
      } // if weight is 0 keep things the way they are

      ticksUntilShiftUpdate = Tuning.getAutoshiftCooldown();
      
      // slightly bias the weight towards the last shift level
      weight = Tuning.getAutoshiftCooldown() / 2;
    } else {
      ticksUntilShiftUpdate--;
    }

  }

  @Override
  protected void initialize() {
    weight = 0;
    ticksUntilShiftUpdate = 0;
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
