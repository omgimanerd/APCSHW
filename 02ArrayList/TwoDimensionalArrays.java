public class TwoDimensionalArrays {
  
  public boolean contains(int value, int[] array) {
    for (int i = 0; i < array.length; ++i) {
      if (array[i] == value) {
        return true;
      }
    }
    return false;
  }

  public boolean contains(int value, int[][]array) {
    for (int i = 0; i < array.length; ++i) {
      if (contains(value, array[i])) {
        return true;
      }
    }
    return false;
  }
  
  public static void main(String args[]) {
    int[][] a = new int[3][3];
  } 
}
