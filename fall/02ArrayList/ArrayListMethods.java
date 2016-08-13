import java.util.*;

public class ArrayListMethods {

  /**
   * Given an ArrayList L, deletes consecutive duplicate elements
   * in L.
   * @param L The ArrayList to run this method on.
   */
  public static void collapseDuplicates(ArrayList<Integer> L) {
    for (int i = 0; i < L.size() - 1; ++i) {
      if (L.get(i).equals(L.get(i + 1))) {
        L.remove(i);
        i--;
      }
    }
  }

  /**
   * Given an ArrayList L, randomizes all the elements in L.
   * This method takes longer than the randomize() method.
   * Use: time &lt;method&rt; to measure the time it takes to run.
   * @param L The ArrayList to run this method on.
   */
  public static void randomizeSlow(ArrayList<Integer> L) {
    Random rand = new Random();
    for (int i = L.size(); i > 0; --i) {
      L.add(L.remove(rand.nextInt(i)));
    }
  }

  /**
   * Given an ArrayList L, randomizes all the elements in L.
   * @param L The ArrayList to run this method on.
   */
  public static void randomize(ArrayList<Integer> L) {
    Random rand = new Random();
    for (int i = 0; i < L.size(); ++i) {
      int toSwap = rand.nextInt(L.size() - i) + i;
      if (toSwap == i) {
        continue;
      }
      int tmp = L.get(i);
      L.set(i, L.get(toSwap));
      L.set(toSwap, tmp);
    }
  }
}
