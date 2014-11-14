import java.util.*;

public class ArrayListMethods {

  /**
   * Given an ArrayList L, deletes consecutive duplicate elements
   * in L.
   * @param L An arraylist to delete duplicate
   *   elements in.
   */
  public static void collapseDuplicates(ArrayList<Integer> L) {
    for (int i = 0; i < L.size() - 1; ++i) {
      if (L.get(i).equals(L.get(i + 1))) {
        L.remove(i);
        i--;
      }
    }
  }

  // 5 iterations of this runs for ~7600 ms on my computer.
  /*
  public static void randomize(ArrayList<Integer> L) {
    Random rand = new Random();
    for (int i = L.size(); i > 0; --i) {
      L.add(L.remove(rand.nextInt(i)));
    }
  }
  */

  // 5 iterations of this runs for ~500 ms on my computer.
  /**
   * Given an ArrayList L, randomizes all the elements in L.
   * @param L An ArrayList to randomize the elements
   *   for.
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

  public static void main(String[] args) {
    ArrayList<Integer> L = new ArrayList<Integer>();
    for (int i = 0; i < 99999; ++i) {
      L.add(i);
    }
    randomize(L);
    randomize(L);
    randomize(L);
    randomize(L);
    randomize(L);
    randomize(L);
    randomize(L);
    randomize(L);
    randomize(L);
    randomize(L);
  }
}
