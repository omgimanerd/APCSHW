import java.util.Random;

public class OrderedSuperArray extends SuperArray {

  public void superadd(String string) {
    super.add(string);
  }

  public void add(String string) {
    if (this.size_ == 0) {
      super.add(string);
      return;
    }
    for (int i = 0; i < this.size_; ++i) {
      if (string.compareTo(this.array_[i]) <= 0) {
        super.add(i, string);
        return;
      }
    }
    super.add(string);
  }

  public void add(int index, String string) {
    this.add(string);
  }

  // Lazy way
  public String set(int index, String string) {
    String removed = this.remove(index);
    this.add(string);
    return removed;
  }

  public void badInsertionSort(){
    OrderedSuperArray c = new OrderedSuperArray();
    while (this.size() > 0) {
      c.add(this.remove(0));
    }
    System.out.println(c + " " + c.size());
    System.out.println(this + " " + this.size());
    
    while (c.size() > 0) {
      superadd(c.remove(0));
    }
  }

  public static void main(String[] args) {
    OrderedSuperArray L = new OrderedSuperArray();

    L.add("Test");
    L.add("aest");
    L.add("yest");
    L.add("qest");
    L.add("best");
    L.add("fest");
    System.out.println(L + " " + L.size());
    L.badInsertionSort();
    
    /*
    Random rand = new Random();
    for (int i = 0; i < 1000; ++i) {
      L.superadd("" + (char)(rand.nextInt(26) + 65));
    }
    System.out.println(L + " " + L.size());
    L.badInsertionSort();
    System.out.println(L + " " + L.size());*/
  }
}
