import java.util.Random;

public class Test {
    public static void main(String[] args) {
    SuperArray L = new SuperArray();
    System.out.println(L + " " + L.size());
    L.clear();
    L.add("hi");
    L.add("test");
    System.out.println(L + " " + L.size());
    L.remove(0);
    System.out.println(L + " " + L.size());
    L.add("meh");
    L.add("trol");
    System.out.println(L + " " + L.size());
    L.add(3, "what");
    System.out.println(L + " " + L.size());
    System.out.println(L.find("trol"));
  }
}
