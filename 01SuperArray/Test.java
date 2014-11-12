public class Test {
  public static void main(String[] args) {
    SuperArray L = new SuperArray();
    System.out.println(L + " " + L.size());
    L.add(69);
    L.add(69);
    L.add(69);
    L.add(40);
    L.add(40);
    L.add(40);
    System.out.println(L + " " + L.size());
    L.collapseDuplicates();
    System.out.println(L + " " + L.size());
  }
}
