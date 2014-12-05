import java.util.*;

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
    this.array_[index] = string;

    return old;
  }

  public String remove(int index) {
    if (index < 0 || index >= this.size_) {
      throw new IndexOutOfBoundsException();
    }
    String removed = this.array_[index];
    while (index < this.size_) {
      this.array_[index] = this.array_[++index];
    }
    this.size_--;

    if (this.size_ <= this.array_.length / 4) {
      this.resize(this.array_.length / 2);
    }
    return removed;
  }

  public void clear() {
    this.size_ = 0;
  }

  public int size() {
    return this.size_;
  }

  public String toString() {
    String out = "[";
    for (int i = 0; i < this.size_; ++i) {
      out += this.array_[i];
      if (i != this.size_ - 1) {
        out += ", ";
      }
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
    if (index < 0 || index > this.size_) {
      throw new IndexOutOfBoundsException();
    }

    if (this.size_ == this.array_.length) {
      this.resize(this.size_ * 2);
    }
    for (int i = this.array_.length - 1; i > index; i--) {
      this.array_[i] = this.array_[i - 1];
    }
    this.array_[index] = string;
    this.size_++;
  }
  
  public void resize(int newCapacity) {
    String[] newArray = new String[newCapacity];
    int size = Math.min(newCapacity, this.size_);
    for (int i = 0; i < size; ++i) {
      newArray[i] = this.array_[i];
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
  
  public void arraysSort() {
    this.resize(this.size_);
    Arrays.sort(this.array_);
  }

  public void insertionSort() {
    for (int i = 1; i < this.size_; ++i) {
      String tmp = this.array_[i];
      int c = i;
      while (c > 0 && tmp.compareTo(this.array_[c - 1]) < 0) {
        this.array_[c] = this.array_[c - 1];
        c--;
      }
      this.array_[c] = tmp;
    }
  }

  public void selectionSort() {
    for (int i = 0; i < this.size_; ++i) {
      int minIndex = i;
      for (int j = i; j < this.size_; ++j) {
        if (this.array_[i].compareTo(this.array_[j]) > 0) {
          minIndex = j;
        }
      }
      String temp = this.array_[i];
      this.array_[i] = this.array_[minIndex];
      this.array_[minIndex] = temp;
    }
  }
      

  public int find(String string) {
    for (int i = 0; i < this.size_; ++i) {
      if (this.array_[i].equals(string)) {
        return i;
      }
    }
    return -1;
  }
}
