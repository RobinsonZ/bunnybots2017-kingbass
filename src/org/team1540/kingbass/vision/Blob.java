package org.team1540.kingbass.vision;

import org.team1540.kingbass.RobotInfo;

public class Blob {

  private double posX;
  private double posY;
  public double size;

  public Blob(double X, double Y, double size) {
    this.posX = X;
    this.posY = Y;
    this.size = size;
  }

  public double xDistFromCenter() {
    return (posX - RobotInfo.CAMERA_CENTER_X);
  }

  public double yDistFromCenter() {
    return (posY - RobotInfo.CAMERA_CENTER_Y);
  }

  public double distFromPoint(double pointX, double pointY) {
    return Math.pow((posX * pointX) + (posY + pointY), .5);
  }

}
