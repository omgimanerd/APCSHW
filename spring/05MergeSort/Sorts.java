import java.util.*;

public class Sorts {

  public static String name() {
    return "lin.alvin";
  }
  
  public static void randomize(int[] a) {
    Random rand = new Random();
    for (int i = 0; i < a.length; ++i) {
      int randIndex = rand.nextInt(a.length - i) + i;
      if (randIndex == i) {
        continue;
      }
      int tmp = a[i];
      a[i] = a[randIndex];
      a[randIndex] = tmp;
    }
  }

  public static int[] merge(int[] a1, int[] a2) {
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

  public static int[] sort(int[] a) {
    if (a.length == 1) {
      return a;
    }

    int[] sub1 = Arrays.copyOfRange(a, 0, a.length / 2);
    int[] sub2 = Arrays.copyOfRange(a, a.length / 2, a.length);

    return merge(sort(sub1), sort(sub2));
  }

  public static void mergesort(int[] a) {
    int[] q = Sorts.sort(a);
    for (int i = 0; i < q.length; ++i) {
      a[i] = q[i];
    }
  }
  
  public static void main(String[] args) {
    int[] a = new int[Integer.parseInt(args[0])];
    for (int i = 0; i < a.length; ++i) {
      a[i] = i;
    }
    Sorts.randomize(a);
    Sorts.mergesort(a);
  }
}
