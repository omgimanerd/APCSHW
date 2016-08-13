import java.util.*;

public class Sorts {

  public static String name() {
    return "lin.alvin";
  }
  
  /**
   * Swaps two elements in an array.
   * @param a The array of elements.
   * @param i1 The index of the first element to swap.
   * @param i2 The index of the second element to swap.
   */
  private static void swap(int[] a, int i1, int i2) {
    int tmp = a[i1];
    a[i1] = a[i2];
    a[i2] = tmp;
  }
  
  /**
   * Randomizes all the elements in an array.
   * @param a An array of elements to randomize.
   */
  public static void randomize(int[] a) {
    Random rand = new Random();
    for (int i = 0; i < a.length; ++i) {
      int randIndex = rand.nextInt(a.length - i) + i;
      swap(a, i, randIndex);
    }
  }

  /**
   * Selection sorts a given array of integers.
   * @param a The array of integers to sort.
   */
  public static void selection(int[] a) {
    for (int i = 0; i < a.length - 1; ++i) {
      int minIndex = i;
      for (int j = i + 1; j < a.length; ++j) {
        if (a[j] < a[minIndex]) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        swap(a, i, minIndex);
      }
    }
  }

  /**
   * Insertion sorts a given array of integers.
   * @param a The array of integers to sort.
   */
  public static void insertion(int[] a) {
    int tmp, track;
    for (int i = 1; i < a.length; ++i) {
      tmp = a[i];
      track = i;
      while (track > 0 && tmp < a[track - 1]) {
        a[track] = a[track - 1];
        track--;
      }
      a[track] = tmp;
    }
  }

  // QUICKSORT AND QUICKSELECT
  
  /**
   * Partition function for the quicksort
   * @param a The array to partition.
   * @param s The starting index (inclusive).
   * @param e The end index (inclusive).
   * @return The index of the pivot.
   */
  private static int partition(int[] a, int s, int e) {
    Random rand = new Random();
    int pivotIndex = rand.nextInt(e - s + 1) + s;

    swap(a, e, pivotIndex);
    for (int i = s; i < e; ++i) {
      if (a[i] < a[e]) {
        swap(a, s, i);
        s++;
      }
    }
    swap(a, e, s);
    return s;
  }

  /**
   * Returns the kth smallest integer in given array of ints.
   * @param a The array to search through
   * @param k The kth smallest number to find.
   * @return The kth smallest integer in array a.
   */
  public static int quickselect(int[] a, int k) {
    return quickselect(a, 0, a.length - 1, k - 1);
  }

  /**
   * Private recursive helper method for the quickselect.
   * @param a The array to search through.
   * @param s The starting index (inclusive).
   * @param e The ending index (inclusive).
   * @param k The kth smallest number to find.
   */
  private static int quickselect(int[] a, int s, int e, int k) {
    int testPivot = partition(a, s, e);

    if (k < testPivot) {
      return quickselect(a, s, testPivot - 1, k);
    } else if (k > testPivot) {
      return quickselect(a, testPivot + 1, e, k);
    } else {
      return a[testPivot];
    }
  }

  /**
   * Private helper method for the quicksort.
   * @param a The array of integers to sort.
   * @param s The starting index (inclusive).
   * @param e The ending index (inclusive).
   */
  private static void quicksortH(int[] a, int s, int e) {
    if (s >= e) {
      return;
    }
    int pivot = partition(a, s, e);
    quicksortH(a, s, pivot - 1);
    quicksortH(a, pivot + 1, e);
  }

  /**
   * Quicksorts a given array of integers.
   * @param a The array of integers to sort.
   */
  public static void quicksort(int[] a) {
    quicksortH(a, 0, a.length - 1);
  }

  // MERGESORT
  
  /**
   * Merges two sorted arrays.
   * @param a1 The first sorted array.
   * @param a2 The second sorted array.
   * @return An array contained all the elements of a1 and a2, sorted.
   */
  private static int[] merge(int[] a1, int[] a2) {
    int[] mergedList = new int[a1.length + a2.length];
    int c1 = 0, c2 = 0;
    while (c1 <= a1.length && c2 <= a2.length) {
      if (c1 == a1.length) {
        for (; c2 < a2.length; ++c2) {
          mergedList[c1 + c2] = a2[c2];
        }
        return mergedList;
      }
      if (c2 == a2.length) {
        for (; c1 < a1.length; ++c1) {
          mergedList[c1 + c2] = a1[c1];
        }
        return mergedList;
      }
      if (a1[c1] < a2[c2]) {
        mergedList[c1 + c2] = a1[c1];
        c1++;
      } else {
        mergedList[c1 + c2] = a2[c2];
        c2++;
      }
    }
    return mergedList;
  }

  /**
   * Private helper method for the merge sort.
   * @param a The array to merge sort.
   * @return Returns the sorted version of array a.
   */
  private static int[] mergesortH(int[] a) {
    if (a.length == 1) {
      return a;
    }
    int[] sub1 = Arrays.copyOfRange(a, 0, a.length / 2);
    int[] sub2 = Arrays.copyOfRange(a, a.length / 2, a.length);

    return merge(mergesortH(sub1), mergesortH(sub2));
  }

  /**
   * Mergesorts a given array of integers.
   * @param a The array of integers to sort.
   */
  public static void mergesort(int[] a) {
    int[] q = mergesortH(a);
    for (int i = 0; i < q.length; ++i) {
      a[i] = q[i];
    }
  }

  // HEAPSORT

  /**
   * Given the index of a node in a heap tree's internal array representation,
   * returns the index of the left child of that node in the heap tree.
   * @param node The index of the starting node.
   * @return The index of the left child of the starting node.
   */
  private static int getLeft(int node) {
    return (2 * node) + 1;
  }

  /**
   * Given the index of a node in a heap tree's internal array representation,
   * returns the index of the right child of that node in the heap tree.
   * @param node The index of the starting node.
   * @return The index of the right child of the starting node.
   */
  private static int getRight(int node) {
    return 2 * (node + 1);
  }

  /**
   * Given the index of a node in a heap tree's internal array representation,
   * returns the index of the parent of the given node in the heap tree.
   * @param node The index of the starting node.
   * @return The index of the parent of the starting node.
   */
  private static int getParent(int node) {
    return (node - 1) / 2;
  }

  /**
   * Pushes up the element at the given index to turn the given array into the
   * valid internal representation of a max-heap.
   * @param a The given array to turn into a max-heap.
   * @param node The index of the element to push up.
   */
  private static void pushUp(int[] a, int node) {
    if (node == 0) {
      return;
    } else if (a[node] > a[getParent(node)]) {
      swap(a, node, getParent(node));
      pushUp(a, getParent(node));
    }
  }

  /**
   * Pushes down the element at the given index to its correct position in
   * the given array given the consideration that the array is the internal
   * representation of a max-heap. Takes a third parameter to indicate the
   * lowest position that the element will go to.
   * @param a The given array representing a valid max-heap.
   * @param node The index of the element to push down.
   * @param max The maximum index to push the element to (exclusive).
   */
  private static void pushDown(int[] a, int node, int max) {
    if (getLeft(node) >= max) {
      return;
    } else if (a[node] < a[getRight(node)]) {
      swap(a, node, getRight(node));
      //      System.out.println(Arrays.toString(a));
      pushDown(a, getRight(node), max);
    } else if (a[node] < a[getLeft(node)]) {
      swap(a, node, getLeft(node));
      //      System.out.println(Arrays.toString(a));
      pushDown(a, getLeft(node), max);
    }
  }
  
  /**
   * Given an array of integers, turns it into a valid internal representation
   * of a heap.
   * @param a The array to turn into a heap.
   */
  private static void heapify(int[] a) {
    for (int i = a.length - 1; i >= 0; --i) {
      pushUp(a, i);
    }
  }

  /**
   * Extracts an array of sorted integers from a valid heap.
   * @param a The internal array representation of a valid heap.
   */
  private static void extractSortedFromHeap(int[] a) {
    for (int i = 0; i < a.length; ++i) {
      swap(a, 0, a.length - 1 - i);
      System.out.println(Arrays.toString(a));
      pushDown(a, 0, a.length - 1 - i);
      System.out.println(Arrays.toString(a));
    }
  }

  /**
   * Heapsorts a given array of integers.
   * @param a The array of integers to sort.
   */
  public static void heapsort(int[] a) {
    heapify(a);
    extractSortedFromHeap(a);
  }

  public static void main(String[] args) {
    int[] a = new int[] {
      3, 4, 2, 5, 6, 1, 10
    };
    Sorts.heapify(a);
    System.out.println(Arrays.toString(a));
    Sorts.extractSortedFromHeap(a);
  }
}
