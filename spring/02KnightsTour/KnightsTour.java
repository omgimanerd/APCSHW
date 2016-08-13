import java.util.*;
import java.io.*;

public class KnightsTour {
  final static String clear = "\033[2J";
  final static String hide = "\033[?25l";
  final static String show = "\033[?25h";

  private int[][] board_;

  public KnightsTour(int size) {
    if (size > 10) {
      throw new Error("Size too big to do in a timely manner.");
    }
    board_ = new int[size][size];
  }

  public String name() {
    return "lin.alvin";
  }

  private String go(int x, int y) {
    return ("\033[" + x + ";" + y + "H");
  }
 
  public void wait(int millis) {
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {}
  }

  public String toString() {
    String result = clear + go(0,0) + "\n";
    for (int i = 0; i < board_.length * 3 + 1; ++i) {
      result += "_";
    }
    result += "\n";
    for (int i = 0; i < board_.length; ++i) {
      result += "|";
      for (int j = 0; j < board_[i].length; ++j) {
        if (board_[i][j] / 10 == 0) {
          result += board_[i][j] + " |";
        } else {
          result += board_[i][j] + "|";
        }
      }
      result += "\n";
    }
    for (int i = 0; i < board_.length * 3 + 1; ++i) {
      result += "-";
    } 
    result += "\n" + show;
    return result;
  }

  public void solve() {
    solve(0, 0);
  }

  public void solve(int x, int y) {
    int[] pStart = new int[] {
      x, y
    };
    solve(pStart, 1);
  }

  public boolean solve(int[] pStart, int currentMoveNumber) {
    int x = pStart[0];
    int y = pStart[1];

    try {
      if (board_[x][y] != 0) {
        return false;
      }
      if (board_.length * board_.length == currentMoveNumber) {
        return true;
      }
      board_[x][y] = currentMoveNumber;
      int[] candidate1 = new int[] {
        x + 1, y + 2
      };
      int[] candidate2 = new int[] {
        x + 1, y - 2
      };
      int[] candidate3 = new int[] {
        x - 1, y + 2
      };
      int[] candidate4 = new int[] {
        x - 1, y - 2
      };
      int[] candidate5 = new int[] {
        x + 2, y + 1
      };
      int[] candidate6 = new int[] {
        x + 2, y - 1
      };
      int[] candidate7 = new int[] {
        x - 2, y + 1
      };
      int[] candidate8 = new int[] {
        x - 2, y - 1
      };
      if (solve(candidate1, currentMoveNumber + 1) ||
          solve(candidate2, currentMoveNumber + 1) ||
          solve(candidate3, currentMoveNumber + 1) ||
          solve(candidate4, currentMoveNumber + 1) ||
          solve(candidate5, currentMoveNumber + 1) ||
          solve(candidate6, currentMoveNumber + 1) ||
          solve(candidate7, currentMoveNumber + 1) ||
          solve(candidate8, currentMoveNumber + 1)) {
        return true;
      }
      board_[x][y] = 0;
    }
    catch (Exception e) {}
    return false;
  }

  public static void main(String[] args) {
    KnightsTour k = new KnightsTour(Integer.parseInt(args[0]));
    k.solve();
    System.out.println(k);
  }
}
