import java.util.*;

public class MyQueue<T> {
  
  private MyLinkedList<T> linkedList_;
  
  public MyQueue() {
    linkedList_ = new MyLinkedList<T>();
  }

  public String name() {
    return "lin.alvin";
  }
  
  public boolean enqueue(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    return linkedList_.add(t);
  }

  public T element() {
    if (linkedList_.size() == 0) {
      throw new NoSuchElementException();
    }
    return linkedList_.peek();
  }

  public T dequeue() {
    if (linkedList_.size() == 0) {
      throw new NoSuchElementException();
    }
    return linkedList_.remove(0);
  }

  public String toString() {
    return linkedList_.toString();
  }

  public static void main(String[] args) {
    MyQueue<Integer> q = new MyQueue<Integer>();

    for (int i = 0; i < 10; ++i) {
      q.enqueue(i);
    }
    System.out.println(q);
    System.out.println(q.dequeue());
    System.out.println(q);
    System.out.println(q.dequeue());
    System.out.println(q);
    System.out.println(q.dequeue());
  }
}
