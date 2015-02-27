/**
 * @author Alvin Lin (alvin.lin@stuypulse.com)
 * This class is a utility class containing methods that make
 * our lives easier when calculating distances and collisions.
 */

public static class Maf {
  
  public static float NEAR_THRESHOLD = 25;
  
  // Returns an array with the coordinates of the midpoint between coords1 and coords2.
  // Works for both 2D and 3D.
  public static float[] getMidPoint(float[] coords1, float[] coords2) {
    if (coords1.length != 2 || coords1.length != 3 || coords1.length != coords2.length) {
      throw new Error("Invalid points");
    }
    if (coords1.length == 2) {
      float[] midpoint = new float[2];
      midpoint[0] = (coords1[0] + coords2[0]) / 2;
      midpoint[1] = (coords1[1] + coords2[1]) / 2;
      return midpoint;
    } else {
      float[] midpoint = new float[3];
      midpoint[0] = (coords1[0] + coords2[0]) / 3;
      midpoint[1] = (coords1[1] + coords2[1]) / 3;
      midpoint[2] = (coords1[2] + coords2[2]) / 3;
      return midpoint;
    }
  }
  
  // Returns the rectilinear distance between coords1 and coords2.
  // AKA Manhattan distance.
  // Works for both 2D and 3D.
  public static float distanceL1(float[] coords1, float[] coords2) {
    if (coords1.length == 2 && coords2.length == 2) {
      return abs(coords1[0] - coords2[0]) +
             abs(coords1[1] - coords2[1]);
    } else if (coords1.length == 3 && coords2.length == 3) {
      return abs(coords1[0] - coords2[0]) +
             abs(coords1[1] - coords2[1]) +
             abs(coords1[2] - coords2[2]);
    } else {
      throw new Error("Invalid points");
    }
  }
  
  // Returns the Euclidean distance between coords1 and coords2.
  // Works for both 2D and 3D.
  public static float distanceL2(float[] coords1, float[] coords2) {
    if (coords1.length == 2 && coords2.length == 2) {
      return (float) Math.sqrt(((coords1[0] - coords2[0]) * (coords1[0] - coords2[0])) +
                               ((coords1[1] - coords2[1]) * (coords1[1] - coords2[1])));
    } else if (coords1.length == 3 && coords2.length == 3) {
      return (float) Math.sqrt(((coords1[0] - coords2[0]) * (coords1[0] - coords2[0])) +
                               ((coords1[1] - coords2[1]) * (coords1[1] - coords2[1])) +
                               ((coords1[2] - coords2[2]) * (coords1[2] - coords2[2])));
    } else {
      throw new Error("Invalid points");
    }
  }
  
  public static boolean almostEqual(float a, float b, float threshold) {
    return abs(a - b) < threshold;
  }
  
  public static boolean almostEqual(float a, float b) {
    return Maf.almostEqual(a, b, Maf.NEAR_THRESHOLD);
  }
}
