import java.util.*;

public class Driver {
  public static void main(String[] args) {
    ArrayList<Integer> L = new ArrayList<Integer>();
    for (int i = 0; i < 99999; ++i) {
      L.add(i);
    }
    ArrayListMethods.randomize(L);
    ArrayListMethods.randomize(L);
    ArrayListMethods.randomize(L);
    ArrayListMethods.randomize(L);
    ArrayListMethods.randomize(L);
    ArrayListMethods.randomize(L);
    ArrayListMethods.randomize(L);
    ArrayListMethods.randomize(L);
  }
}
