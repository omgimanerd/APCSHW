import java.util.*;

public class Sorts {

  public static String name() {
    return "Lin,Alvin";
  }

  public static int period() {
    return 7;
  }

  public static void randomize(int[] c) {
    Random rand = new Random();
    int toSwap, tmp;
    for (int i = 0; i < c.length; ++i) {
      toSwap = rand.nextInt(c.length - i) + i;
      if (toSwap == i) {
        continue;
      }
      tmp = c[i];
      c[i] = c[toSwap];
      c[toSwap] = tmp;
    }
  }

  public static void bubble(int[] c) {
    int tmp = 0;
    for (int i = c.length - 1; i > 0; --i) {
      for (int j = 0; j < i; ++j) {
        if (c[j] > c[j + 1]) {
          tmp = c[j + 1];
          c[j + 1] = c[j];
          c[j] = tmp;
        }
      }
    }
  }

  public static void insertion(int[] c) {
    int tmp, track;
    for (int i = 1; i < c.length; ++i) {
      tmp = c[i];
      track = i;
      while (track > 0 && tmp < c[track - 1]) {
        c[track] = c[track - 1];
        track--;
      }
      c[track] = tmp;
    }
  }

  public static void selection(int[] c) {
    for (int i = 0; i < c.length - 1; ++i) {
      int minIndex = i;
      for (int j = i + 1; j < c.length; ++j) {
        if (c[j] < c[minIndex]) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        int tmp = c[i];
        c[i] = c[minIndex];
        c[minIndex] = tmp;
      }
    }
  }

  private static int getPlaceDigit(int n, int place) {
    return Math.abs((n / (int) Math.pow(10, (double) place - 1)) % 10);
  }

  private static int getDigitsIn(int n) {
    int digits = 0;
    while (Math.abs(n) > 0) {
      n /= 10;
      digits++;
    }
    return digits;
  }

  public static void radix(int[] c) {
    ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
    for (int i = 0; i < 10; ++i) {
      buckets.add(new ArrayList<Integer>());
    }

    int passes = getDigitsIn(c[0]);
    for (int i = 1; i < c.length; ++i) {
      passes = Math.max(passes, c[i]);
    }

    for (int place = 0; place < passes; ++place) {
      for (int i = 0; i < c.length; ++i) {
        buckets.get(getPlaceDigit(c[i], place + 1)).add(c[i]);
      }

      int counter = 0;
      for (int i = 0; i < buckets.size(); ++i) {
        for (int j = 0; j < buckets.get(i).size(); ++j) {
          c[counter] = buckets.get(i).get(j);
          counter++;
        }
        buckets.get(i).clear();
      }
    }
  }

  public static void out(int[] c) {
    String out = "[ ";
    for (int i = 0; i < c.length - 1; ++i) {
      out += c[i] + ", ";
    }
    out += c[c.length - 1] + " ]";
    System.out.println(out);
  }

  public static void main(String[] args) {
    int[] c = new int[100];
    for (int i = 0; i < c.length; ++i) {
      c[i] = i - 50;
    }
    randomize(c);
    out(c);
    radix(c);
    out(c);
  }
}
