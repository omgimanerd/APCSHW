import java.util.Random;

public class Test {
    public static void main(String[] args) {
    SuperArray L = new SuperArray();
    for (int i = 0; i < 500000; ++i) {
      L.add("" + i);
    }
    if (args.length == 0) {
      System.out.println("Usage: java Test <method>");
      System.out.println("methods: arraysSort, insertionSort, selectionSort");
    } else if (args[0].equals("arraysSort")) {
      L.arraysSort();
      System.out.println("Sorted");
    } else if (args[0].equals("insertionSort")) {
      L.insertionSort();
      System.out.println("Sorted");
    } else if (args[0].equals("selectionSort")) {
      L.selectionSort();
      System.out.println("Sorted");
    } else {
      System.out.println("Usage: java Test <method>");
      System.out.println("methods: arraysSort, insertionSort, selectionSort");
    }
  }
}
