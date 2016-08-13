import java.util.*;

public class Sorts  {

  public static String name() {
    return "lin.alvin";
  }

  public static void randomize(int[] a) {
    Random rand = new Random();
    for (int i = 0; i < a.length; ++i) {
      int randIndex = rand.nextInt(a.length - i) + i;
      int tmp = a[i];
      a[i] = a[randIndex];
      a[randIndex] = tmp;
    }
  }

  // s and e are inclusive
  private static int partition(int[] a, int s, int e) {
    Random rand = new Random();
    int pivotIndex = rand.nextInt(e - s + 1) + s;

    int tmp = a[e];
    a[e] = a[pivotIndex];
    a[pivotIndex] = tmp;

    for (int i = s; i < e; ++i) {
      if (a[i] < a[e]) {
        tmp = a[s];
        a[s] = a[i];
        a[i] = tmp;
        s++;
      }
    }

    tmp = a[e];
    a[e] = a[s];
    a[s] = tmp;
    return s;
  }

  // Returns the kth smallest integer in array a
  public static int quickselect(int[] a, int k) {
    return quickselect(a, 0, a.length - 1, k - 1);
  }

  private static int quickselect(int[] a, int s, int e, int k) {
    int testPivot = partition(a, s, e);

    if (k < testPivot) {
      return quickselect(a, s, testPivot - 1, k);
    } else if (k > testPivot) {
      return quickselect(a, testPivot + 1, e, k);
    } else {
      return a[testPivot];
    }
  }

  private static void quicksortH(int[] a, int s, int e) {
    if (s >= e) {
      return;
    }
    int pivot = partition(a, s, e);
    quicksortH(a, s, pivot - 1);
    quicksortH(a, pivot + 1, e);
  }

  public static void quicksort(int[] a) {
    quicksortH(a, 0, a.length - 1);
  }

  public static void main(String[] args) {
    int[] a = new int[Integer.parseInt(args[0])];
    for (int i = 0; i < a.length; ++i) {
      a[i] = i;
    }
    int[] b = new int[] {
      1, 4, 7, 4, 2, 4, 5, 7
    };
    System.out.println(Arrays.toString(b));
    System.out.println(quickselect(b, 4));
    quicksort(a);
    System.out.println(Arrays.toString(a));
  }
}
