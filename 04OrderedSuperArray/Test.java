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
    L.add("cow");
    L.add("dog");
    L.add("mosf");
    L.add("zohf");
    L.add("meteor");
    L.add("llama");
    System.out.println(L + " " + L.size());
    L.insertionSort();
    System.out.println(L + " " + L.size());
  }
}
