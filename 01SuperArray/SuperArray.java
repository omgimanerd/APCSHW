public class SuperArray {
  
  private Object[] array_;
  
  private int size_;
  
  public SuperArray() {
    this(10);
  }
  
  public SuperArray(int length) {
    this.array_ = new Object[length];
  }

  public Object get(int index) {
    if (index < 0 || index > this.array_.length) {
      return null;
    }
    return this.array_[index];
  }
  
  public void set(int index, Object object) {
    if (this.array_[index] == null) {
      this.size_++;
    }
    this.array_[index] = object;
  }
  
  public void clear() {
    for (int i = 0; i < this.array_.length; ++i) {
      this.array_[i] = null;
    }
    this.size_ = 0;
  }
  
  public int size() {
    return this.size_;
  }
  
  public String toString() {
    String out = "[ ";
    for (int i = 0; i < this.array_.length; ++i) {
      out += this.array_[i] + " ";
    }
    out += "]";
    return out;
  }
  
  public void add(Object object) {
    for (int i = 0; i < this.array_.length; ++i) {
      if (this.array_[i] == null) {
        this.array_[i] = object;
        return;
      }
    }
    this.resize(this.array_.length + 1);
    this.add(object);
  }
  
  public void resize(int newCapacity) {
    Object[] newArray = new Object[newCapacity];
    for (int i = 0; i < newCapacity; ++i) {
      if (i < this.array_.length) {
        newArray[i] = this.array_[i];
      }
    }
    this.array_ = newArray;

    this.size_ = 0;
    for (int i = 0; i < newCapacity; ++i) {
      if (this.array_[i] != null) {
        this.size_++;
      }
    }
  }
}
