public class SuperArray {
  
  private Object[] array_;
  
  private int length_;
  
  public SuperArray() {
    this(10);
  }
  
  public SuperArray(int length) {
    this.length_ = length;
    this.array_ = new Object[length];
  }
  
  public int size() {
    return this.length_;
  }
  
  public String toString() {
    String out = "[ ";
    for (int i = 0; i < this.length_; ++i) {
      out += this.array_[i] + " ";
    }
    out += "]";
    return out;
  }
  
  public void add(Object object) {
    for (int i = 0; i < this.length_; ++i) {
      if (this.array_[i] == null) {
        this.array_[i] = object;
        return;
      }
    }
    this.resize(this.length_ + 1);
    this.add(object);
  }
  
  public void resize(int newCapacity) {
    this.length_ = newCapacity;
    Object[] newArray = new Object[newCapacity];
    for (int i = 0; i < newCapacity; ++i) {
      if (i < this.array_.length) {
        newArray[i] = this.array_[i];
      }
    }
    this.array_ = newArray;
  }
  
  public void clear() {
    for (int i = 0; i < this.length_; ++i) {
      this.array_[i] = null;
    }
  }
  
  public Object get(int index) {
    return this.array_[index];
  }
  
  public void set(int index, Object object) {
    this.array_[index] = object;
  }
}
