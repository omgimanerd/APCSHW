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
    if (index < 0 || index >= this.size_) {
      throw new IndexOutOfBoundsException();
    }
    return this.array_[index];
  }
  
  public Object set(int index, Object object) {
    if (index < 0 || index >= this.size_) {
      throw new IndexOutOfBoundsException();
    }
    Object old = this.array_[index];
    if (object == null) {
      return this.remove(index);
    }
    this.array_[index] = object;
    
    return old;
  }
  
  public Object remove(int index) {
    if (index < 0 || index >= this.size_) {
      throw new IndexOutOfBoundsException();
    }
    Object removed = this.array_[index];
    for (int i = index; i < this.size_; ++i) {
      if (i == this.size_ - 1) {
        this.array_[i] = null;
      } else {
        this.array_[i] = this.array_[i + 1];
      }
    }
    this.size_--;
    if (this.size_ <= this.array_.length / 2) {
      this.resize(this.size_ * 2);
    }
    return removed;
  }
  
  public void clear() {
    this.resize(10);    
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
    this.resize(this.size_ * 2);
    this.add(object);
  }
  
  public void add(int index, Object object) {
    if (index > this.array_.length) {
      this.resize(this.array_.length * 2);
    }
    if (this.array_[index] == null) {
      this.size_++;
      this.array_[index] = object;
      return;
    } else {
      Object temp = this.array_[index];
      this.array_[index] = object;
      for (int i = index; i < this.array_.length; ++i) {
        if (i == this.size_ - 1) {
          this.add(temp);
          return;
        } else {
          this.size_++;
          temp = this.set(i, temp);
        }
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
