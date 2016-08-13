/**
 * Static class that manages user input.
 */
public static class Input {
  
  public static boolean W;
  public static boolean A;
  public static boolean S;
  public static boolean D;
  public static boolean SPACE;
  public static boolean C;
  public static boolean X;
  
  public static void onKeyPressed(int code) {
    switch (code) {
      case 87:
        W = true;
        break;
      case 68:
        A = true;
        break;
      case 83:
        S = true;
        break;
      case 65:
        D = true;
        break;
      case 32:
        SPACE = true;
        break;
      case 67:
        C = true;
        break;
      case 88:
        X = true;
        break;
    }
  }
  
  public static void onKeyReleased(int code) {
    switch (code) {
      case 87:
        W = false;
        break;
      case 68:
        A = false;
        break;
      case 83:
        S = false;
        break;
      case 65:
        D = false;
        break;
      case 32:
        SPACE = false;
        break;
      case 67:
        C = false;
        break;
      case 88:
        X = false;
        break;
    }
  }
}

