public class RunningMedian {

  private MyHeap smallHalf_;
  private MyHeap largeHalf_;
  
  public RunningMedian() {
    smallHalf_ = new MyHeap(true);
    largeHalf_ = new MyHeap(false);
  }

  public String name() {
    return "lin.alvin";
  }
  
  private void rebalance() {
    while (largeHalf_.size() - smallHalf_.size() > 1) {
      smallHalf_.add(largeHalf_.remove());
    }
    while (smallHalf_.size() - largeHalf_.size() > 1) {
      largeHalf_.add(smallHalf_.remove());
    }
  }
  
  public void add(int value) {
    if (smallHalf_.size() == 0 && largeHalf_.size() == 0) {
      smallHalf_.add(value);
    } else if (value < getMedian()) {
      smallHalf_.add(value);
    } else {
      largeHalf_.add(value);
    }
    rebalance();
  }
  
  public double getMedian() {
    if (smallHalf_.size() == 0) {
      return (double) largeHalf_.peek();
    } else if (largeHalf_.size() == 0) {
      return (double) smallHalf_.peek();
    } else if ((largeHalf_.size() + smallHalf_.size()) % 2 == 0) {
      return (smallHalf_.peek() + largeHalf_.peek()) / 2.0;
    } else if (largeHalf_.size() > smallHalf_.size()) {
      return largeHalf_.peek();
    } else {
      return smallHalf_.peek();
    }
  }

  public String toString() {
    return smallHalf_ + " " + largeHalf_;
  }

  public static void main(String args[]) {
    RunningMedian q = new RunningMedian();
    q.add(1);
    System.out.println(q + " " + q.getMedian());
    q.add(2);
    System.out.println(q + " " + q.getMedian());
    q.add(6);
    System.out.println(q + " " + q.getMedian());
    q.add(0);
    System.out.println(q + " " + q.getMedian());
    q.add(13);
    System.out.println(q + " " + q.getMedian());
    q.add(14);
    System.out.println(q + " " + q.getMedian());
    q.add(356);
    System.out.println(q + " " + q.getMedian());
    q.add(-1);
    System.out.println(q + " " + q.getMedian());
    q.add(4);
    System.out.println(q + " " + q.getMedian());
  }
}

