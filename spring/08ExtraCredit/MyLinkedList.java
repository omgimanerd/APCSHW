import java.util.*;

public class MyLinkedList<T> {
  
  private int size_;
  private LNode<T> head_;
  private LNode<T> tail_;

  public MyLinkedList() {
    size_ = 0;
  }

  public String name() {
    return "lin.alvin";
  }

  public boolean add(T data) {
    addLast(data);
    return true;
  }

  public void add(int index, T data) {
    if (index < 0 || index > size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    if (index == size_) {
      addLast(data);
      return;
    }
    LNode<T> current = head_;
    while (index != 0) {
      current = current.getNext();
      index--;
    }
    T tmp = current.getData();
    current.setData(data);
    current.setNext(new LNode<T>(tmp, current.getNext()));
    size_++;
  }

  public void addFirst(T data) {
    add(0, data);
  }

  public void addLast(T data) {
    if (size_ == 0) {
      head_ = new LNode<T>(data);
      tail_ = head_;
    } else {
      LNode<T> newTail = new LNode<T>(data);
      tail_.setNext(newTail);
      tail_ = tail_.getNext();
    }
    size_++;
  }

  public void clear() {
    size_ = 0;
    head_ = null;
  }

  public T get(int index) {
    if (index < 0 || index >= size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    LNode<T> current = head_;
    while (index != 0) {
      current = current.getNext();
      index--;
    }
    return current.getData();
  }

  public int indexOf(T value) {
    LNode<T> current = head_;
    int counter = 0;
    while (current != null) {
      if (current.getData().equals(value)) {
        return counter;
      }
      current = current.getNext();
      counter++;
    }
    return -1;
  }

  public T peek() {
    return head_.getData();
  }

  public T poll() {
    if (size_ == 0) {
      return null;
    }
    return remove(0);
  }

  public T remove() {
    return remove(0);
  }

  public T remove(int index) {
    if (index < 0 || index >= size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    T toReturn;
    if (index == 0) {
      toReturn = head_.getData();
      head_ = head_.getNext();
    } else if (index == size_ - 1) {
      toReturn = tail_.getData();
      tail_ = null;
      LNode<T> current = head_;
      while (current.getNext() != null) {
        current = current.getNext();
      }
      tail_ = current;
    } else {
      LNode<T> current = head_;
      while (index != 1) {
        current = current.getNext();
        index--;
      }
      toReturn = current.getNext().getData();
      current.setNext(current.getNext().getNext());
    }
    size_--;
    return toReturn;
  }

  public void set(int index, T data) {
    if (index < 0 || index >= size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    LNode<T> current = head_;
    while (index != 0) {
      current = current.getNext();
      index--;
    }
    current.setData(data);
  }

  public int size() {
    return size_;
  }

  public void swap(int index1, int index2) {
    T tmp = get(index1);
    set(index1, get(index2));
    set(index2, tmp);
  }

  public String toString() {
    if (size_ == 0) {
      return "[ ]";
    }
    String out = "[";
    LNode<T> current = head_;
    while (current.getNext() != null) {
      out += current.toString() + ", ";
      current = current.getNext();
    }
    out += current.toString();
    return out + "]";
  }

  public static void main(String[] args) {
    MyLinkedList<Integer> l = new MyLinkedList<Integer>();
    ArrayList<Integer> m = new ArrayList<Integer>();

    for (int i = 0; i < 100; ++i) {
      l.add(i);
      m.add(i);
    }

    l.remove(23);
    m.remove(23);

    l.set(4, 30);
    m.set(4, 30);

    System.out.println(l + " " + l.size());
    System.out.println(m + " " + m.size());
  }
}
