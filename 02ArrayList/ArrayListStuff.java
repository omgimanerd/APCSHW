import java.util.*;

public class ArrayListStuff {
  public static void collapseDuplicates(ArrayList<Integer> L) {
    for (int i = 0; i < L.size() - 1; ++i) {
      if (L.get(i).equals(L.get(i + 1))) {
        L.remove(i);
        i--;
      }
    }
  }
  
  public static void main(String[] args) {
    ArrayList<Integer> L = new ArrayList<Integer>();
    L.add(10);
    L.add(10);
    L.add(10);
    L.add(10);
    L.add(10);
    L.add(20);
    L.add(20);
    L.add(20);
    L.add(20);
    L.add(20);
    L.add(20);
    System.out.println(L);
    collapseDuplicates(L);
    System.out.println(L);
    L.add(10);
    L.add(20);
    L.add(20);
    L.add(20);
    L.add(10, 20);
    System.out.println(L);
  }
}
