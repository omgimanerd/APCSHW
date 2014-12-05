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

  // Runtime for a SuperArray of 5000 elements: 0.
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

  // Binary search lol
  // ez pz lemon sqezy
  public int find(String string) {
    int lowerLimit = 0;
    int upperLimit = this.size();

    int search = (int) Math.ceil((lowerLimit + upperLimit) / 2.0);
    while (!this.array_[search].equals(string)) {
      if (this.array_[search].compareTo(string) > 0) {
        upperLimit = search;
      } else {
        lowerLimit = search;
      }
      search = (int) Math.ceil((lowerLimit + upperLimit) / 2.0);
    }
    
    while (search - 1 >= 0 && this.array_[search - 1].equals(string)) {
      search--;
    }
    
    return search;
  }

  public static void main(String[] args) {
    OrderedSuperArray L = new OrderedSuperArray();
    System.out.println(L + " " + L.size());
    L.add("hi");
    L.add("test");
    L.add("meh");
    L.add("trol");
    L.add("trol");
    L.add("1more");
    System.out.println(L.find("trol"));
    System.out.println(L + " " + L.size());
  }
}
