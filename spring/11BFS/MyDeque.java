import java.util.*;

public class MyDeque<T> {

  private final int DEFAULT_SIZE = 10;

  private T[] items_;
  private int[] priorities_;
  private int head_;
  private int tail_;
  private int size_;
  private boolean isPriorityList_;
  
  @SuppressWarnings("unchecked")
  public MyDeque() {
    items_ = (T[]) (new Object[DEFAULT_SIZE]);
    priorities_ = new int[DEFAULT_SIZE];
    head_ = 0;
    tail_ = DEFAULT_SIZE - 1;
    size_ = 0;
    isPriorityList_ = false;
  }

  public String name() {
    return "lin.alvin";
  }
  
  private int normalize(int n) {
    while (n < items_.length) {
      n += items_.length;
    }
    return n % items_.length;
  }

  @SuppressWarnings("unchecked")
  private void resize() {
    int newSize = size_;
    if (size_ == items_.length) {
      newSize = size_ * 2;
    } else {
      return;
    }
    
    T[] newItems = (T[]) (new Object[newSize]);
    int[] newPriorities = new int[newSize];
    int copyCounter = 0;
    if (head_ <= tail_) {
      for (int i = head_; i <= tail_; ++i) {
        newItems[copyCounter] = items_[i];
        newPriorities[copyCounter] = priorities_[i];
        copyCounter++;
      }
    } else {
      for (int i = head_; i < items_.length; ++i) {
        newItems[copyCounter] = items_[i];
        newPriorities[copyCounter] = priorities_[i];
        copyCounter++;
      }
      for (int i = 0; i <= tail_; ++i) {
        newItems[copyCounter] = items_[i];
        newPriorities[copyCounter] = priorities_[i];
        copyCounter++;
      }
    }
    head_ = 0;
    tail_ = size_ - 1;
    items_ = newItems;
    priorities_ = newPriorities;
  }

  public boolean add(T item) {
    if (isPriorityList_) {
      throw new IllegalStateException();
    }
    addLast(item);
    return true;
  }

  public void add(T item, int priority) {
    addLast(item);
    priorities_[tail_] = priority;
    isPriorityList_ = true;
  }

  public void addFirst(T item) {
    resize();
    head_ = normalize(head_ - 1);
    items_[head_] = item;
    priorities_[head_] = 1;
    size_++;
  }

  public void addLast(T item) {
    resize();
    tail_ = normalize(tail_ + 1);
    items_[tail_] = item;
    priorities_[tail_] = 1;
    size_++;
  }

  public T getFirst() {
    if (isPriorityList_) {
      throw new IllegalStateException();
    }
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    return items_[head_];
  }

  public T getLast() {
    if (isPriorityList_) {
      throw new IllegalStateException();
    }
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    return items_[tail_];
  }

  public T removeSmallest() {
    if (!isPriorityList_) {
      throw new IllegalStateException();
    }
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    size_--;
    int smallestPriorityIndex = 0;
    for (int i = 0; i < priorities_.length; ++i) {
      if (priorities_[i] == 1) {
        smallestPriorityIndex = i;
        break;
      }
      if (priorities_[i] < priorities_[smallestPriorityIndex]) {
        smallestPriorityIndex = i;
      }
    }

    T toReturn = items_[smallestPriorityIndex];
    items_[smallestPriorityIndex] = items_[head_];
    priorities_[smallestPriorityIndex] = priorities_[smallestPriorityIndex];
    head_ = normalize(head_ + 1);
    return toReturn;
  }

  public T removeLargest() {
    if (!isPriorityList_) {
      throw new IllegalStateException();
    }
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    size_--;
    int largestPriorityIndex = 0;
    for (int i = 0; i < priorities_.length; ++i) {
      if (priorities_[i] > priorities_[largestPriorityIndex]) {
        largestPriorityIndex = i;
      }
    }

    T toReturn = items_[largestPriorityIndex];
    items_[largestPriorityIndex] = items_[head_];
    priorities_[largestPriorityIndex] = priorities_[largestPriorityIndex];
    head_ = normalize(head_ + 1);
    return toReturn;
  }

  public T removeFirst() {
    if (isPriorityList_) {
      throw new IllegalStateException();
    }
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    size_--;
    int index = head_;
    head_ = normalize(head_ + 1);
    return items_[index];
  }

  public T removeLast() {
    if (isPriorityList_) {
      throw new IllegalStateException();
    }
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    size_--;
    int index = tail_;
    tail_ = normalize(tail_ - 1);
    return items_[index];
  }

  public int size() {
    return size_;
  }

  public String toString() {
    if (size_ == 0) {
      return "[ ]";
    }
    String dataOut = "[ ";
    String priorityOut = "[ ";
    if (head_ <= tail_) {
      for (int i = head_; i <= tail_; ++i) {
        dataOut += items_[i] + " ";
        priorityOut += priorities_[i] + " ";
      }
    } else {
      for (int i = head_; i < items_.length; ++i) {
        dataOut += items_[i] + " ";
        priorityOut += priorities_[i] + " ";
      }
      for (int i = 0; i <= tail_; ++i) {
        dataOut += items_[i] + " ";
        priorityOut += priorities_[i] + " ";
      }
    }
    if (isPriorityList_) {
      return dataOut + "]" + "\n" + priorityOut + "]";
    }
    return dataOut + "]";
  }

  public static void main(String[] args) {
    MyDeque<Integer> q = new MyDeque<Integer>();
    q.add(1, 1);
    q.add(2, 10);
    q.add(3, 5);
    System.out.println(q);
    System.out.println(q.removeLargest());
  }
}
