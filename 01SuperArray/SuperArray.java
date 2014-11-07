public class SuperArray {
  
  private Object[] array_;
  
  private int size_;
  
  public SuperArray() {
    this.array_ = new Object[0];
  }

  public Object get(int index) {
    if (index < 0 || index >= this.size_) {
      System.out.println("Index out of range.");
      return null;
    }
    return this.array_[index];
  }
  
  public Object set(int index, Object object) {
    if (index < 0 || index >= this.size_) {
      System.out.println("Index out of range.");
      return null;
    }
    Object old = this.array_[index];
    this.array_[index] = object;
    
    return old;
  }
  
  public Object remove(int index) {
    if (index < 0 || index >= this.size_) {
      System.out.println("Index out of range.");
      return null;
    }
    Object removed = this.array_[index];
    for (int i = index; i < this.size_; ++i) {
      if (i == this.size_ - 1) {
        this.array_[i] = null;
      } else {
        this.array_[i] = this.array_[i + 1];
      }
    }
    this.resize(this.size_ - 1);
    return removed;
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
        this.size_++; 
        this.array_[i] = object;
        return;
      }
    }
    this.resize(this.array_.length + 1);
    this.add(object);
  }
  
  public void add(int index, Object object) {
    Object temp = this.array_[index];
    this.array_[index] = object;
    for (int i = index; i < this.size_; ++i) {
      if (i == this.size_) {
        this.add(temp);
        return;
      } else {
        temp = this.set(i, temp);
      }
    }
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
