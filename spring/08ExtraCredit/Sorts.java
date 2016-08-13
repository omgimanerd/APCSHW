import java.util.*;

public class Sorts {

  public static String name() {
    return "lin.alvin";
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

  private static int getPlaceDigit(int n, int place) {
    return Math.abs((n / (int) Math.pow(10, (double) place - 1)) % 10);
  }

  public static int getDigitsIn(int n) {
    return (n + "").length();
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

  private static void quicksortH(int[] a, int s, int e) {
    if (s >= e) {
      return;
    }
    int pivot = partition(a, s, e);
    quicksortH(a, s, pivot - 1);
    quicksortH(a, pivot + 1, e);
  }

  public static void quick(int[] a) {
    quicksortH(a, 0, a.length - 1);
  }

  private static int[] merge(int[] a1, int[] a2) {
    int[] mergedList = new int[a1.length + a2.length];
    int c1 = 0, c2 = 0;
    while (c1 <= a1.length && c2 <= a2.length) {
      if (c1 == a1.length) {
        for (; c2 < a2.length; ++c2) {
          mergedList[c1 + c2] = a2[c2];
        }
        return mergedList;
      }
      if (c2 == a2.length) {
        for (; c1 < a1.length; ++c1) {
          mergedList[c1 + c2] = a1[c1];
        }
        return mergedList;
      }
      if (a1[c1] < a2[c2]) {
        mergedList[c1 + c2] = a1[c1];
        c1++;
      } else {
        mergedList[c1 + c2] = a2[c2];
        c2++;
      }
    }
    return mergedList;
  }

  private static int[] mergesortH(int[] a) {
    if (a.length == 1) {
      return a;
    }

    int[] sub1 = Arrays.copyOfRange(a, 0, a.length / 2);
    int[] sub2 = Arrays.copyOfRange(a, a.length / 2, a.length);

    return merge(mergesortH(sub1), mergesortH(sub2));
  }

  public static void merge(int[] a) {
    int[] q = mergesortH(a);
    for (int i = 0; i < q.length; ++i) {
      a[i] = q[i];
    }
  }

  public static void main(String[] args) {
    int[] a = new int[10000];
    for (int i = 0; i < a.length; ++i) {
      a[i] = i;
    }
    randomize(a);
    System.out.println(Arrays.toString(a));
    merge(a);
    System.out.println(Arrays.toString(a));
  }
}
