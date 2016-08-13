/**
 * Static class of utility methods and math methods.
 */
public static class Util {
  
  public static boolean DEBUG_MODE = false;
  
  public static float getEuclideanDistance2(float x1, float y1,
                                            float x2, float y2) {
    return ((x1 - x2) * (x1 - x2)) +
           ((y1 - y2) * (y1 - y2));
  }
  
  public static float getEuclideanDistance2(float x1, float y1, float z1,
                                            float x2, float y2, float z2) {
    return ((x1 - x2) * (x1 - x2)) +
           ((y1 - y2) * (y1 - y2)) +
           ((z1 - z2) * (z1 - z2));
  }
  
  public static float getEuclideanDistance(float x1, float y1,
                                           float x2, float y2) {
    return sqrt(getEuclideanDistance2(x1, y1, x2, y2));
  }
  
  public static float getEuclideanDistance(float x1, float y1, float z1, 
                                           float x2, float y2, float z2) {
    return sqrt(getEuclideanDistance2(x1, y1, z1, x2, y2, z2));
  }
  
  public static int getSign(int num) {
    return getSign((float) num);
  }
  
  public static int getSign(float num) {
    if (num == 0) {
      return (int) num;
    }
    return (int) (num / abs(num));
  }
  
  public static float normalizeRadians(float angle) {
    while (angle < 0) {
      angle += TWO_PI;
    }
    return angle % TWO_PI;
  }
  
  public static float normalizeRadians2(float angle) {
    while (angle < -PI) {
      angle += TWO_PI;
    }
    return angle % PI;
  }
  
  public static float getAngleBetween(float x1, float y1, float x2, float y2) {
    return atan2(y2 - y1, x2 - x1);
  }
  
  public static float getLateralAngleBetween(PVector v1, PVector v2) {
    return getAngleBetween(v1.x, v1.z, v2.x, v2.z);
  }
  
  public static float getVerticalAngleBetween(PVector v1, PVector v2) {
    return atan2(v2.y - v1.y, getEuclideanDistance(v1.x, v1.z, v2.x, v2.z));
  }
  
  public static float average(int... numbers) {
    float sum = 0;
    for (float number : numbers) {
      sum += number;
    }
    return sum / numbers.length;
  }
  
  public static float average(float... numbers) {
    float sum = 0;
    for (float number : numbers) {
      sum += number;
    }
    return sum / numbers.length;
  }
  
  public static boolean lineSphereIntersection(PVector p1, PVector p2,
                                               PVector c, float r) {
    float a = sq(p2.x - p1.x) + sq(p2.y - p1.y) + sq(p2.z - p1.z);
    float b = 2 * ((p2.x - p1.x) * (p1.x - c.x) +
                   (p2.y - p1.y) * (p1.y - c.y) +
                   (p2.z - p1.z) * (p1.z - c.z));
    float c_ = sq(p1.x - c.x) + sq(p1.y - c.y) + sq(p1.z - c.z) - sq(r);
    float d = sq(b) - 4 * a * c_;
    return d >= 0;
  }
  
  public static float getLinePointPerpendicularDistance(PVector l1, PVector l2, PVector p) {
    return PVector.sub(l2, l1).cross(PVector.sub(l1, p)).mag() / PVector.sub(l2, l1).mag();
  }
}

