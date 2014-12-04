import java.util.Random;

public class OrderedSuperArray extends SuperArray {

  public void superadd(String string) {
    super.add(string);
  }

  public void add(String string) {
    if (size() == 0) {
      super.add(string);
      return;
    }
    for (int i = 0; i < size(); ++i) {
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
    while (c.size() > 0) {
      this.add(c.remove(0));
    }
    System.out.println(c + " " + c.size());
    System.out.println(this + " " + this.size());
  }

  public static void main(String[] args) {
    OrderedSuperArray L = new OrderedSuperArray();
    Random rand = new Random();
    for (int i = 0; i < 5000; ++i) {
      L.superadd("" + (char)(rand.nextInt(26) + 65));
    }
    System.out.println(L + " " + L.size());
    L.badInsertionSort();
    System.out.println(L + " " + L.size());
  }
}
