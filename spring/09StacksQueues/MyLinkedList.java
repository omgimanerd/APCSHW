import java.util.*;

public class MyLinkedList<T> implements Iterable<T> {

  private class LNode<T> {
    private T data_;
    private LNode<T> next_;

    public LNode(T data) {
      this(data, null);
    }

    public LNode(T data, LNode<T> next) {
      data_ = data;
      next_ = next;
    }

    public String toString() {
      return data_.toString();
    }

    public LNode<T> getNext() {
      return next_;
    }

    public void setNext(LNode<T> next) {
      next_ = next;
    }

    public T getData() {
      return data_;
    }

    public void setData(T data) {
      data_ = data;
    }
  }

  private class MyLinkedListIterator<T> implements Iterator<T> {
    private LNode<T> current_;

    public MyLinkedListIterator(LNode<T> current) {
      current_ = current;
    }

    public boolean hasNext() {
      return current_.getNext() != null;
    }

    public T next() {
      T data =  current_.getData();
      current_ = current_.getNext();
      return data;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  
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
    if (index == size_ - 1) {
      return tail_.getData();
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

  public Iterator<T> iterator() {
    return new MyLinkedListIterator<T>(head_);
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
      tail_ = head_;
      while (tail_.getNext() != null) {
        tail_ = tail_.getNext();
      }
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
    LinkedList<Integer> a = new LinkedList<Integer>();
    for (int i = 0; i < 10; ++i) {
      a.add(i);
    }
    System.out.println(a.remove(a.size() - 1));
    System.out.println(a);
  }
}
