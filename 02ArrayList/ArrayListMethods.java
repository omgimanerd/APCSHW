import java.util.*;

public class ArrayListMethods {
  public static void collapseDuplicates(ArrayList<Integer> L) {
    for (int i = 0; i < L.size() - 1; ++i) {
      if (L.get(i).equals(L.get(i + 1))) {
        L.remove(i);
        i--;
      }
    }
  }
  
  public static void randomize(ArrayList<Integer> L) {
    Random rand = new Random();
    for (int i = L.size(); i > 0; --i) {
      L.add(L.remove(rand.nextInt(i)));
    }
  }
  
  public static void randomize2(ArrayList<Integer> L) {
    Random rand = new Random();
    for (int i = 0; i < L.size(); ++i) {
      int toSwap = rand.nextInt(L.size() - i) + i;
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
    randomize2(L);
    System.out.println(L);
  }
}
