import java.io.*;
import java.util.*;

public class BSTree <T extends Comparable> {

  private class BSTreeNode<T extends Comparable> {

    private T data_;
    private int tally_;
    private BSTreeNode<T> left_;
    private BSTreeNode<T> right_;

    public BSTreeNode(T data) {
      data_ = data;
      tally_ = 1;
      left_ = right_ = null;
    }

    public T getData() {
      return data_;
    }
    @SuppressWarnings("unchecked")
    public int compareData(BSTreeNode<T> data) {
      return data_.compareTo(data.getData());
    }
    @SuppressWarnings("unchecked")
    public int compareData(T data) {
      return data_.compareTo(data);
    }
    public BSTreeNode<T> getLeft() {
      return left_;
    }
    public boolean hasLeft() {
      return left_ != null;
    }
    public BSTreeNode<T> getRight() {
      return right_;
    }
    public boolean hasRight() {
      return right_ != null;
    }

    public void setData(T data) {
      data_ = data;
    }
    public void setLeft(BSTreeNode<T> left) {
      left_ = left;
    }
    public void setRight(BSTreeNode<T> right) {
      right_ = right;
    }
    public boolean isLeaf() {
      return (left_ == null && right_ == null);
    }

    public int getTally() {
      return tally_;
    }
    public void incrementCount() {
      tally_++;
    }
    public void decrementCount() {
      tally_--;
    }
    
    public String toString() {
      return data_ + "";
    }
  }
  
  private BSTreeNode<T> root_;

  public BSTree() {
    root_ = null;
  }

  public String name() {
    return "lin.alvin";
  }
  
  public boolean isEmpty() {
    return root_ == null;
  }
  
  public void add(T value) {
    root_ = add(root_, new BSTreeNode<T>(value));
  }

  private BSTreeNode<T> add(BSTreeNode<T> root, BSTreeNode<T> node) {
    if (root == null) {
      return node;
    } else if (root.compareData(node) > 0) {
      root.setLeft(add(root.getLeft(), node));
    } else {
      root.setRight(add(root.getRight(), node));
    }
    return root;
  }

  public void remove(T value) {
    root_ = remove(root_, value);
  }

  public BSTreeNode<T> findReplacement(BSTreeNode<T> current) {
    if (current == null) {
      return null;
    }
    return current.hasLeft() ? findReplacement(current.getLeft()) : current;
  }
  
  private BSTreeNode<T> remove(BSTreeNode<T> root, T value) {
    if (root == null) {
      return root;
    } else if (root.isLeaf() && root.compareData(value) == 0) {
      return null;
    } else if (root.compareData(value) > 0) {
      root.setLeft(remove(root.getLeft(), value));
    } else if (root.compareData(value) < 0) {
      root.setRight(remove(root.getRight(), value));
    } else {
      root.setData(findReplacement(root.getRight()).getData());
      root.setRight(remove(root.getRight(), root.getData()));
    }
    return root;
  }

  public void inOrder() {
    inOrderHelper(root_);
    System.out.println();
  }

  public void inOrderHelper(BSTreeNode<T> current) {
    if (current == null) {
      return;
    } else if (current.isLeaf()) {
      System.out.println(current);
      return;
    }
    inOrderHelper(current.getLeft());
    System.out.println(current);
    inOrderHelper(current.getRight());
  }
  
  public int getHeight(){
    return getHeight(root_);
  }

  private int getHeight(BSTreeNode<T> branch){
    if (branch == null) {
      return 0;
    } else {
      return 1 + Math.max(getHeight(branch.getLeft()),
                          getHeight(branch.getRight()));
    }
  }

  /** STOLEN CODE **/
  private int maxLength() {
    if (root_ == null)
      return 0;
    return maxLength(root_);
  }

  private int maxLength(BSTreeNode<T> curr) {
    int max = curr.toString().length();
    int temp;
    if (curr.getLeft() != null) {
      temp = maxLength(curr.getLeft());
      if (temp > max)
        max = temp;
    }
    if (curr.getRight() != null) {
      temp = maxLength(curr.getRight());
      if (temp > max)
        max = temp;
    }
    return max;
  }

  private String spaces(double n) {
    String result = "";
    for (int i = 0; i < n; i++)
      result += " ";
    return result;
  }

  private String getLevel(BSTreeNode<T> curr,
                          int currLevel,
                          int targetLevel,
                          int height,
                          int wordLength) {
    if (currLevel == 1){
      return curr.toString() + 
        spaces(wordLength - curr.toString().length()) +
        spaces(wordLength * 
               Math.pow(2, height - targetLevel + 1) - 
               wordLength);
    }
    String result = "";
    if (curr.getLeft() != null) {
      result += getLevel(curr.getLeft(),
                         currLevel - 1,
                         targetLevel,
                         height,
                         wordLength);
    } else {
      result += spaces(wordLength *
                       Math.pow(2, height - targetLevel + currLevel - 1));
    }
    if (curr.getRight() != null) {
      result += getLevel(curr.getRight(),
                         currLevel - 1,
                         targetLevel,
                         height,
                         wordLength);
    } else {
      result += spaces(wordLength *
                       Math.pow(2, height - targetLevel + currLevel - 1));
    }
    return result;
  }
		
  public String toString() {
    if (root_ == null) {
      return "";
    }
    String result = "";
    int height = getHeight();
    int wordLength = maxLength();
    for (int level = 1; level < height; level++){
      result += spaces(wordLength *
                       Math.pow(2, height - level) - wordLength) +
        getLevel(root_, level, level, height, wordLength)
        .replaceFirst("\\s+$", "") + "\n";
    }
    result += getLevel(root_, height, height, height, wordLength)
      .replaceFirst("\\s+$", "");
				
    return result;
  }
  /** END STOLEN CODE **/
  
  public static void main( String[] args ) {
    BSTree<Integer> t = new BSTree<Integer>();
    t.add(3);
    t.add(5);
    t.add(4);
    t.add(8);
    t.add(1);
    t.inOrder();
    t.remove(5);
    System.out.println(t);
  }
}
