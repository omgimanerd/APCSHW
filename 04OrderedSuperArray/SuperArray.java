public class SuperArray {

  protected String[] array_;

  protected int size_;

  public SuperArray() {
    this(10);
  }

  public SuperArray(int length) {
    this.array_ = new String[length];
  }

  public String get(int index) {
    if (index < 0 || index >= this.size_) {
      throw new IndexOutOfBoundsException();
    }
    return this.array_[index];
  }

  public String set(int index, String string) {
    if (index < 0 || index >= this.size_) {
      throw new IndexOutOfBoundsException();
    }
    String old = this.array_[index];
    if (string == null) {
      return this.remove(index);
    }
    this.array_[index] = string;

    return old;
  }
  
  public String remove(int index) {
    if (index < 0 || index >= this.size_) {
      throw new IndexOutOfBoundsException();
    }
    String removed = this.array_[index];
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
    for (int i = 0; i < this.size_; ++i) {
      out += this.array_[i] + " ";
    }
    out += "]";
    return out;
  }

  public void add(String string) {
    if (this.size_ < this.array_.length) {
      this.array_[this.size_] = string;
      this.size_++;
    } else {
      this.resize(this.size_ * 2);
      this.add(string);
    }
  }
  
  public void add(int index, String string) {
    if (index < 0 || index >= this.size_) {
      throw new IndexOutOfBoundsException();
    }
    if (index == this.array_.length) {
      this.add(string);
    } else {
      String temp = this.array_[index];
      this.array_[index] = string;
      this.size_++;
      for (int i = index + 1; i < this.size_; ++i) {
        if (i == this.size_) {
          this.add(temp);
          return;
        } else {
          temp = this.set(i, temp);
        }
      }
    }
  }
  
  public void resize(int newCapacity) {
    String[] newArray = new String[newCapacity];
    for (int i = 0; i < newCapacity; ++i) {
      if (i < this.array_.length) {
        newArray[i] = this.array_[i];
      }
    }
    this.array_ = newArray;
  }

  public void collapseDuplicates() {
    for (int i = 0; i < this.size_ - 1; ++i) {
      if (this.array_[i].equals(this.array_[i + 1])) {
        this.remove(i);
        i--;
      }
    }
  }
}
