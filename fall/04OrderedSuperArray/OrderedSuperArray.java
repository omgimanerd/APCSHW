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

    int search = (lowerLimit + upperLimit) / 2;
    int maxTries = (int) Math.ceil(Math.log(this.size_));
    System.out.println("Max tries" + maxTries);
    int trials = 0;
    while (!this.array_[search].equals(string)) {
      if (trials > maxTries) {
        return -1;
      }
      if (this.array_[search].compareTo(string) > 0) {
        upperLimit = search;
      } else {
        lowerLimit = search;
      }
      search = (lowerLimit + upperLimit) / 2;
      trials++;
    }
    
    while (search - 1 >= 0 && this.array_[search - 1].equals(string)) {
      search--;
    }
    
    return search;
  }
}
