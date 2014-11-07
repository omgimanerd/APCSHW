public class Test {
  public static void main(String[] args) {
    SuperArray L = new SuperArray();
    L.add(new Integer(99));
    L.add(new Integer(23876));
    L.add(new String("Hi"));
    System.out.println(L + " " + L.size());
    L.resize(3);
    System.out.println(L + " " + L.size());
    System.out.println(L.get(2) + " " + L.size());
    L.add(new Integer(182));
    System.out.println(L + " " + L.size());
    L.add(1, new Integer(69));
    System.out.println(L + " " + L.size());
    L.add(new Integer(98));
    System.out.println(L + " " + L.size());
    L.add(0, new Integer(69));
    System.out.println(L + " " + L.size());
    L.remove(1);
    System.out.println(L + " " + L.size());
    L.set(3, new Integer(14));
    System.out.println(L + " " + L.size());
    L.set(4, new Integer(10));
    System.out.println(L + " " + L.size());
    L.add(new String("test"));
    System.out.println(L + " " + L.size());
  }
}
