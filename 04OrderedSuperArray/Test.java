import java.util.Random;

public class Test {
    public static void main(String[] args) {
    OrderedSuperArray L = new OrderedSuperArray();
    System.out.println(L + " " + L.size());
    L.add("hi");
    L.add("test");
    L.add("meh");
    L.add("trol");
    L.add("trol");
    L.add("trol");
    L.add("trol");
    L.add("trol");
    L.add("trol");
    L.add("trol");
    L.add("1more");
    L.add("zeta");
    System.out.println(L.find("trol"));
    System.out.println(L.find("1more"));
    System.out.println(L.find("hum"));
    System.out.println(L.find("zeta"));
    System.out.println(L + " " + L.size());
  }
}
