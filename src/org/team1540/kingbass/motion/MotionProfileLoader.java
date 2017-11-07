package org.team1540.kingbass.motion;

import edu.wpi.first.wpilibj.DriverStation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class MotionProfileLoader {
  public static double[][] loadFromCSV(String filename) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      Stream<String> lines = reader.lines();
      Iterator<String> lineIter = lines.iterator();
      List<double[]> points = new ArrayList<double[]>();

      while (lineIter.hasNext()) {
        String[] stringPoint = lineIter.next().split(",");
        points.add(new double[]{
            Double.valueOf(stringPoint[0]), Double.valueOf(stringPoint[1]),
            Double.valueOf(stringPoint[2])
        });
      }

      return points.toArray(new double[3][points.size()]);
    } catch (IOException e) {
      DriverStation.reportError("Error while reading motion profile: " + e.getMessage(), false);
      return null;
    }
  }
}
