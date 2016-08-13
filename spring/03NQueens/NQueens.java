import java.util.*;
import java.io.*;

public class NQueens {

  private final static String clear = "\033[2J";
  private final static String hide = "\033[?25l";
  private final static String show = "\033[?25h";

  private char[][] board_;

  public NQueens (int size) {
    if (size == 2 || size == 3) {
      throw new Error("Impossible to solve");
    }
    board_ = new char[size][size];
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        board_[i][j] = ' ';
      }
    }
  }

  public String name() {
    return "lin.alvin";
  }

  public void wait(int millis) {
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {}
  }

  public String toString() {
    String result = clear + "\n";
    for (int i = 0; i < board_.length * 2 + 1; ++i) {
      result += "_";
    }
    result += "\n";
    for (int i = 0; i < board_.length; ++i) {
      result += "|";
      for (int j = 0; j < board_[i].length; ++j) {
        if (board_[i][j] == 'Q' || board_[i][j] == 'x' || board_[i][j] == ' ') {
          result += board_[i][j] + "|";
        } else {
          result += board_[i][j] + " |";
        }
      }
      result += "\n";
    }
    for (int i = 0; i < board_.length * 2 + 1; ++i) {
      result += "-";
    } 
    result += "\n" + show;
    return result;
  }

  public void solve() {
    solve(0);
  }

  public boolean solve(int x) {
    return solve(x, 0, 0);
  }

  private boolean isValidSquare(int x, int y) {
    int len = board_.length;
    for (int i = -len; i < len; i++ ) {
      try {
        if (board_[x + i][y] == 'Q') {
          return false;
        }
      } catch (Exception e) {}
    }
    for (int i = -len; i < len; i++ ) {
      try {
        if (board_[x][y + i] == 'Q') {
          return false;
        }
      } catch (Exception e) {}
    }
    for (int i = -len; i < len; i++ ) {
      try {
        if (board_[x + i][y + i] == 'Q') {
          return false;
        }
      } catch (Exception e) {}
    }
    for (int i = -len; i < len; i++ ) {
      try {
        if (board_[x + i][y - i] == 'Q') {
          return false;
        }
      } catch (Exception e) {}
    }
    return true;
  }
 
  private void clearAll() {
    for (int i = 0; i < board_.length; ++i) {
      for (int j = 0; j < board_[i].length; ++j) {
        board_[i][j] = ' ';
      }
    }
  }

  public boolean solve(int x, int y, int numQueens) {
    if (numQueens == board_.length) {
      return true;
    }
    if (isValidSquare(x, y) && x < board_.length && y < board_.length) {
      board_[x][y] = 'Q';
      if (solve(0, y + 1, numQueens + 1)) {
        return true;
      }
      board_[x][y] = ' ';
    }
    if (x < board_.length) {
      return solve(x + 1, y, numQueens);
    } else {
      return false;
    }
  }

  public static void main(String[] args) {
    NQueens n = new NQueens(Integer.parseInt(args[0]));
    n.solve();
    System.out.println(n);
  }
}
