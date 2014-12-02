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

  public void set(int index, String string) {
    this.remove(index);
    this.add(string);
  }

  public static void main(String[] args) {
    OrderedSuperArray a = new OrderedSuperArray();
    a.add("moo");
    a.add("zoo");
    a.add("hi");
    a.add("Mellow");
    a.add(0, "zygote");
    a.add("Apple");
    System.out.println(a);
    a.set(5, "waffle");
    System.out.println(a);
  }
}
