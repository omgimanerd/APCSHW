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
    L.add(1, 30);
    System.out.println(L + " " + L.size());
    L.add(0, 14);
    System.out.println(L + " " + L.size());
  }
}
