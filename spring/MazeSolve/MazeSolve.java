import java.util.*;
import java.io.*;

public class MazeSolve {
  
  private char[][] maze_;
  private int[] startPoint_;

  public MazeSolve(String filename) {
    getMaze(filename);
    startPoint_ = new int[2];
    verifyMaze();
  }

  /**
   * Precondition: the maze must be a rectangular thingy of text.
   * The maze cannot be empty
   * The file should have no trailing newline.
   * The maze must have an S and an E and cannot have @ or '.'
   */
  private void getMaze(String filename) {
    File file;
    Scanner read;
    try {
      file = new File(filename);
      read = new Scanner(file);
    } catch (Exception e) {
      throw new Error("File not found.");
    }
    
    ArrayList<String> lines = new ArrayList<String>();
    while (read.hasNextLine()) {
      String line = read.nextLine();
      lines.add(line.toUpperCase());
    }
    try {
      maze_ = new char[lines.size()][lines.get(0).length()];
      for (int i = 0; i < lines.size(); ++i) {
        for (int j = 0; j < lines.get(i).length(); ++j) {
          maze_[i][j] = lines.get(i).charAt(j);
        }
      }
    } catch (Exception e) {
      throw new Error("Invalid maze");
    }
  }

  private void outputMaze(boolean animate) {
    if (animate) {
      try {
        Thread.sleep(100);
        for (int i = 0; i < 50; ++i) {
          System.out.print("\n");
        }
      } catch (Exception e) {}
    }
    for (int i = 0; i < maze_.length; ++i) {
      for (int j = 0; j < maze_[i].length; ++j) {
        System.out.print(maze_[i][j]);
      }
      System.out.print("\n");
    }
  }

  private void verifyMaze() {
    int s = 0;
    int e = 0;
    for (int i = 0; i < maze_.length; ++i) {
      for (int j = 0; j < maze_[i].length; ++j) {
        if (maze_[i][j] == 'S') {
          s++;
          startPoint_[0] = i;
          startPoint_[1] = j;
        }
        if (maze_[i][j] == 'E') {
          e++;
        }
      }
    }
    if (s != 1 || e != 1) {
      throw new Error("Invalid maze");
    }
  }

  public boolean solve(int[] pStart, boolean animate) {
    int x = pStart[0];
    int y = pStart[1];

    outputMaze(animate);

    try {
      if (maze_[x][y] == 'E') {
        return true;
      }
      if (maze_[x][y] == ' ' || maze_[x][y] == 'S') {
        maze_[x][y] = '@';
        int[] candidate1 = new int[] {
          x + 1, y
        };
        int[] candidate2 = new int[] {
          x, y + 1
        };
        int[] candidate3 = new int[] {
          x - 1, y
        };
        int[] candidate4 = new int[] {
          x, y - 1
        };
        if (solve(candidate1, animate) || solve(candidate2, animate) ||
            solve(candidate3, animate) || solve(candidate4, animate)) {
          return true;
        }
        maze_[x][y] = '.';
      }
    } catch (Exception e) {}
    return false;
  }

  public int[] getStartPoint() {
    return startPoint_;
  }

  public char[][] getInternalMaze() {
    return maze_;
  }

  public static void main(String[] args) {
    MazeSolve m = new MazeSolve("maze2");
    m.solve(m.getStartPoint(), true);
  }
}
