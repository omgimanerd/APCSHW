public class Test {
  public static void main(String[] args) {
    SuperArray L = new SuperArray();
    L.add(new Integer(99));
    L.add(new Integer(23876));
    L.add(new String("Hi"));
    System.out.println(L + " " + L.size());
    L.resize(3);
    System.out.println(L + " " + L.size());
    System.out.println(L.get(2));
    L.set(2, new Integer(79));
    System.out.println(L + " " + L.size());
    System.out.println(L.get(2));
    L.add(new Integer(182));
    System.out.println(L);
  }
}
