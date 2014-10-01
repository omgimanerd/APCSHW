public class Stars {

  public static String tri(int s) {
    String out = "";
    s++;
    for (int i = 0; i < s; ++i) {
      out += rect(1, i);
    }
    return out;
  }

  public static String triback(int s) {
    String out = "";
    s++;
    for (int i = 0; i < s; ++i) {
      for (int q = 0; q < s - i - 1; ++q) {
        out += " ";
      }
      for (int j = 0; j < i; ++j) {
        out += "*";
      }
      out += "\n";
    }
    return out;
  }


  public static String rect(int h, int w) {
    String out = "";
    int c1 = 0;
    int c2 = 0;
    while (c1 < h) {
      while (c2 < w) {
        out += "*";
        c2++;
      }
      out += "\n";
      c1++;
      c2 = 0;
    }
    return out;
  }

  public static String forRect(int h, int w) {
    String out = "";
    for (int i = 0; i < h; ++i) {
      for (int j = 0; j < w; ++j) {
        out += "*";
      }
      out += "\n";
    }
    return out;
  }

  public static void main(String[] args) {
    System.out.println(rect(2, 3));
    System.out.println(rect(1, 4));
    System.out.println(tri(8));
    System.out.println(triback(5));
  }
}
