import java.util.*;

public class MyStack<T> {
  private MyLinkedList<T> linkedList_;

  public MyStack() {
    linkedList_ = new MyLinkedList<T>();
  }

  public String name() {
    return "lin.alvin";
  }
  
  public void push(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    linkedList_.add(0, t);
  }

  public T peek() {
    if (linkedList_.size() == 0) {
      throw new NoSuchElementException();
    }
    return linkedList_.get(0);
  }

  public T pop() {
    if (linkedList_.size() == 0) {
      throw new NoSuchElementException();
    }
    return linkedList_.remove(0);
  }

  public int size() {
    return linkedList_.size();
  }

  public String toString() {
    return linkedList_.toString();
  }

  public static void main(String[] args) {
    MyStack<Integer> q = new MyStack<Integer>();
    for (int i = 0; i < 10; ++i) {
      q.push(i);
    }
    System.out.println(q);
    System.out.println(q.peek());
    System.out.println(q.pop());
    System.out.println(q);

    LinkedList<Integer> a = new LinkedList<Integer>();
    for (int i = 0; i < 10; ++i) {
      a.add(i);
    }
    while (a.size() != 0) {
      System.out.println(a.remove(a.size() - 1));
      System.out.println(a);
    }
  }
}
