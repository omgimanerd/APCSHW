public class LNode<T> {

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
