public class Test {
  public static void main(String[] args) {
    SuperArray L = new SuperArray();
    System.out.println(L + " " + L.size());
    L.add(new Integer(69));
    L.resize(4);
    System.out.println(L + " " + L.size());
    L.add(2, new Integer(4));
    System.out.println(L + " " + L.size());
    L.add(new Integer(3));
    System.out.println(L + " " + L.size());
    L.resize(5);
    System.out.println(L + " " + L.size());
    L.remove(2);
    System.out.println(L + " " + L.size());
    L.add(new Integer(3));
    System.out.println(L + " " + L.size());
    L.add(new Integer(3));
    System.out.println(L + " " + L.size());
    L.add(new Integer(3));
    System.out.println(L + " " + L.size());
    L.clear();
    System.out.println(L + " " + L.size());
  }
}
