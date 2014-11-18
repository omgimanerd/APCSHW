public class TwoDimensionalArrays {
  
  public static boolean contains(int value, int[] array) {
    for (int i = 0; i < array.length; ++i) {
      if (array[i] == value) {
        return true;
      }
    }
    return false;
  }

  public static boolean contains(int value, int[][]array) {
    for (int i = 0; i < array.length; ++i) {
      if (contains(value, array[i])) {
        return true;
      }
    }
    return false;
  }
  
  public static void main(String args[]) {
    int[][] a = new int[3][3];
    
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        a[i][j] = 0;
        System.out.println(a[i][j]);
      }
    }
    a[0][2] = 3;

    System.out.println(contains(3, a));
  } 
}
