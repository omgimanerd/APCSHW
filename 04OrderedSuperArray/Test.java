import java.util.Random;

public class Test {
    public static void main(String[] args) {
    SuperArray L = new SuperArray();
    Random rand = new Random();
    for (int i = 0; i < 9999; ++i) {
      L.add("" + (char)(rand.nextInt(26) + 65));
    }
    L.insertionSort();
    System.out.println(L + " " + L.size());
  }
}
