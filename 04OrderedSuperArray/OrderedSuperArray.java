public class OrderedSuperArray extends SuperArray {

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

  // If I override set(), then the superclass uses this set method, how do?
  /*
  public String set(int index, String string) {
    String toReplace = this.array_[index];
    this.remove(index);
    this.add(string);
    return toReplace;
  }
  */

  public static void main(String[] args) {
    OrderedSuperArray a = new OrderedSuperArray();
    a.add("moo");
    a.add("zoo");
    a.add("hi");
    a.add("Mellow");
    a.add(0, "zygote");
    a.add("Apple");
    System.out.println(a);
  }
}
