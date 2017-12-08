
package org.team1540.kingbass;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1540.base.adjustables.AdjustableManager;
import org.team1540.kingbass.commands.arm.ZeroArmPosition;
import org.team1540.kingbass.commands.auto.DriveForward;
import org.team1540.kingbass.commands.claw.ZeroClawPosition;
import org.team1540.kingbass.subsystems.Arm;
import org.team1540.kingbass.subsystems.Claw;
import org.team1540.kingbass.subsystems.Controller;
import org.team1540.kingbass.subsystems.DriveTrain;
import org.team1540.kingbass.subsystems.Intake;
import org.team1540.kingbass.subsystems.Shifters;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the IterativeRobot documentation. If you change the name of this class
 * or the package after creating this project, you must also update the manifest file in the
 * resource directory.
 */
public class Robot extends IterativeRobot {
  public static Compressor compressor = new Compressor();
  public static Arm arm = new Arm();
  public static Claw claw = new Claw();
  public static DriveTrain driveTrain = new DriveTrain();
  public static Intake intake = new Intake();
  public static Shifters shifters = new Shifters();
  public static Controller controller = new Controller();

  private Command autonomousCommand;
  private SendableChooser<Command> chooser = new SendableChooser<>();


  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString code to get the auto name from the text box below the Gyro
   *
   * You can add additional auto modes by adding additional commands to the chooser code above (like
   * the commented example) or additional comparisons to the switch structure below with additional
   * strings & commands.
   */
  @Override
  public void autonomousInit() {
    autonomousCommand = chooser.getSelected();

    // schedule the autonomous command (example)
    if (autonomousCommand != null) { autonomousCommand.start(); }
  }

  /**
   * This function is called periodically during autonomous
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You can use it to reset
   * any subsystem information you want to clear when the robot is disabled.
   */
  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    AdjustableManager.getInstance().add(new Tuning());
    chooser.addObject("Drive forward 5 sec", new DriveForward(5));
    chooser.addObject("Drive forward 2.5 sec", new DriveForward(2.5));
    SmartDashboard.putData("Auto mode", chooser);
    SmartDashboard.putData("Compressor", compressor);
    SmartDashboard.putData(new ZeroClawPosition());
    SmartDashboard.putData(new ZeroArmPosition());
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Arm Position", arm.getPosition());
    SmartDashboard.putNumber("Arm A Current", arm.getCurrentA());
    SmartDashboard.putNumber("Arm B Current", arm.getCurrentB());
    arm.updatePIDs();
    AdjustableManager.getInstance().update();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) { autonomousCommand.cancel(); }
  }

  /**
   * This function is called periodically during operator control
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode
   */
  @Override
  public void testPeriodic() {
    LiveWindow.run();
  }
}
