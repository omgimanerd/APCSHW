public class Test {
  public static void main(String[] args) {
    SuperArray L = new SuperArray();
    System.out.println(L + " " + L.size());
    L.add("hi");
    L.add("hi");
    L.add("hi");
    L.add("hi");
    L.add("hi");
    System.out.println(L + " " + L.size());
    L.add(0, "test");
    System.out.println(L + " " + L.size());
    L.add(0, "test2");
    System.out.println(L + " " + L.size());
  }
}
