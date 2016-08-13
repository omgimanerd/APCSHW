import java.util.*;

/**
 * Deque with priority removal.
 */
public class PriorityDeque<T> {

  private final int DEFAULT_SIZE = 10;

  private T[] items_;
  private int[] priorities_;
  private int head_;
  private int tail_;
  private int size_;

  @SuppressWarnings("unchecked")
  public PriorityDeque() {
    items_ = (T[]) (new Object[DEFAULT_SIZE]);
    priorities_ = new int[DEFAULT_SIZE];
    head_ = 0;
    tail_ = DEFAULT_SIZE - 1;
    size_ = 0;
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
    for (int i = head_; i < head_ + size_; ++i) {
      newItems[i - head_] = items_[i % items_.length];
      newPriorities[i - head_] = priorities_[i % priorities_.length];
    }

    head_ = 0;
    tail_ = size_ - 1;
    items_ = newItems;
    priorities_ = newPriorities;
  }

  public boolean add(T item) {
    addLast(item);
    return true;
  }

  public void add(T item, int priority) {
    addLast(item);
    priorities_[tail_] = priority;
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
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    return items_[head_];
  }

  public T getLast() {
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    return items_[tail_];
  }

  private T priorityRemove(boolean gt) {
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    int priorityIndex = head_;
    for (int i = head_; i < head_ + size_ ; ++i) {
      if (gt) {
        if (priorities_[i % priorities_.length] >
            priorities_[priorityIndex]) {
          priorityIndex = i % priorities_.length;
        }
      } else {
        if (priorities_[i % priorities_.length] <
            priorities_[priorityIndex]) {
          priorityIndex = i % priorities_.length;
        }
      }
    }
    size_--;

    T toReturn = items_[priorityIndex];
    items_[priorityIndex] = items_[head_];
    priorities_[priorityIndex] = priorities_[head_];
    head_ = normalize(head_ + 1);
    return toReturn;
  }

  public T removeSmallest() {
    return priorityRemove(false);
  }

  public T removeLargest() {
    return priorityRemove(true);
  }

  public T removeFirst() {
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    size_--;
    int index = head_;
    head_ = normalize(head_ + 1);
    return items_[index];
  }

  public T removeLast() {
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
    for (int i = head_; i < head_ + size_; ++i) {
      dataOut += items_[i % items_.length] + " ";
      priorityOut += priorities_[i % priorities_.length] + " ";
    }
    return dataOut + "]" + " " + priorityOut + "]";
  }

  public static void main(String[] args) {
    PriorityDeque<Integer> q = new PriorityDeque<Integer>();
    for (int i = 0; i < 20; ++i) {
      q.add(i, i);
    }
    System.out.println(q);
    System.out.println(q.removeSmallest());
    System.out.println(q);
    System.out.println(q.removeSmallest());
    System.out.println(q);
    System.out.println(q.removeSmallest());
    System.out.println(q);
    System.out.println(q.removeLargest());
    System.out.println(q);
    System.out.println(q.removeLargest());
  }
}
