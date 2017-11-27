package org.team1540.kingbass.commands.auto;

import static org.team1540.kingbass.Robot.driveTrain;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import java.util.LinkedList;
import org.team1540.base.ChickenCommand;
import org.team1540.kingbass.RobotInfo;
import org.team1540.kingbass.Tuning;
import org.team1540.kingbass.vision.Blob;


public class DriveToObject extends ChickenCommand {

  private NetworkTable table;
  private int minDistI = 0;
  private double minDist = Double.POSITIVE_INFINITY;
  private Blob lastBlob = new Blob(320, 240, 100);
  private Blob[] blobs;
  private Blob usedBlob;
  private LinkedList<Blob> usableBlobs = new LinkedList<>();
  int amtUsableBlobs = 0;

  public DriveToObject() {
    table = NetworkTable.getTable("GRIP/myBlobsReport");
    double centerX = RobotInfo.CAMERA_CENTER_X;
    double centerY = RobotInfo.CAMERA_CENTER_Y;
    addRequirement(driveTrain);
    setPriority(10);
  }

  @Override
  protected void execute() {
    double[] xArray = table.getNumberArray("x", new double[0]);
    double[] yArray = table.getNumberArray("y", new double[0]);
    double[] sizeArray = table.getNumberArray("size", new double[0]);
    blobs = new Blob[xArray.length];
    for (int i = 0; i < xArray.length; i++) {
      blobs[i] = new Blob(xArray[i], yArray[i], sizeArray[i]);
    }
    if (blobs.length > 0) {
      for (Blob blob : blobs) {
        if (blob.yDistFromCenter() < 80) {
          usableBlobs.add(blob);
        }
      }
      if (usableBlobs.size() > 1) {
        for (Blob blob : usableBlobs) {
          if (Math.abs(blob.xDistFromCenter()) > minDist) {
            usableBlobs.remove(blob);
          } else {
            usedBlob = blob;
            lastBlob = blob;
          }
        }
      } else {
        if (usableBlobs.size() == 1) {
          usedBlob = usableBlobs.get(0);
          lastBlob = usableBlobs.get(0);
        } else {
          usedBlob = lastBlob;
        }
      }
      driveTrain.setLeftMotors(0.5 - usedBlob.xDistFromCenter() * Tuning.visionP);
      driveTrain.setRightMotors(0.5 + usedBlob.xDistFromCenter() * Tuning.visionP);

    } else {
      driveTrain.setLeftMotors(0.5 - lastBlob.xDistFromCenter() * Tuning.visionP);
      driveTrain.setRightMotors(0.5 + lastBlob.xDistFromCenter() * Tuning.visionP);
    }

  }

  @Override
  protected boolean isFinished() {
    return lastBlob.size > Tuning.returnThreshold;
  }
}
