import java.util.*;
import java.io.*;

public class Maze {
  
  private class MoveNode {
    public int x_;
    public int y_;
    public MoveNode prev_;

    public MoveNode(int[] xy, MoveNode prev) {
      this(xy[0], xy[1], prev);
    }

    public MoveNode(int x, int y, MoveNode prev) {
      x_ = x;
      y_ = y;
      prev_ = prev;
    }

    public String toString() {
      return "(" + x_ + "," + y_ + ")";
    }
  }

  private static final String CLEAR = "\033[2J";
  private static final String HIDE = "\033[?25l";
  private static final String SHOW = "\033[?25h";
  private static final String RESET = "\033[0;0H";
  private static final int BREADTH_FIRST_MODE = 0;
  private static final int DEPTH_FIRST_MODE = 1;
  private static final int BEST_FIRST_MODE = 2;
  private static final int A_STAR_MODE = 3;

  private String filename_;
  private char[][] maze_;
  private int[] startPoint_;
  private int[] endPoint_;
  private LinkedList<Integer> solution_;

  public Maze(String filename) {
    filename_ = filename;
    getMaze(filename);
    verifyMaze();
    solution_ = new LinkedList<Integer>();
  }

  public String name() {
    return "lin.alvin";
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

  private void clearCrapFromMaze() {
    for (int i = 0; i < maze_.length; ++i) {
      for (int j = 0; j < maze_[i].length; ++j) {
        if (maze_[i][j] == '.') {
          maze_[i][j] = ' ';
        }
      }
    }
  }

  public String toString() {
    String out = "";
    for (char[] i : maze_) {
      for (char j : i) {
        out += j;
      }
      out += "\n";
    }
    return out;
  }

  public String toString(boolean animate) {
    if (animate) {
      try {
        Thread.sleep(100);
      }
      catch (Exception e) {}
      return CLEAR + RESET + toString();
    }
    return toString();
  }
  
  private void outputMaze(boolean animate) {
    System.out.println(toString(animate));
  }

  private void verifyMaze() {
    int s = 0;
    int e = 0;
    for (int i = 0; i < maze_.length; ++i) {
      for (int j = 0; j < maze_[i].length; ++j) {
        if (maze_[i][j] == 'S') {
          s++;
          startPoint_ = new int[] { i, j };
        }
        if (maze_[i][j] == 'E') {
          e++;
          endPoint_ = new int[] { i, j };
        }
      }
    }
    if (s != 1 || e != 1) {
      throw new Error("Invalid maze");
    }
  }

  private boolean verifySquare(int[] xy) {
    return verifySquare(xy[0], xy[1]);
  }

  private boolean verifySquare(int x, int y) {
    try {
      return maze_[x][y] != '#' && maze_[x][y] != '.' && maze_[x][y] != 'S';
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    }
  }

  private int getPriorityDistance(int[] point) {
    return Math.abs(point[0] - endPoint_[0]) + Math.abs(point[1] - endPoint_[1]);
  }

  private boolean solve(boolean animate, int mode) {
    PriorityDeque<MoveNode> moves = new PriorityDeque<MoveNode>();
    moves.add(new MoveNode(startPoint_[0], startPoint_[1], null));

    while (moves.size() != 0) {
      int numCurrentMoves = moves.size();
      for (int d = 0; d < numCurrentMoves; ++d) {

        // Gets the next MoveNode depending on which solve algorithm
        // we're using.
        MoveNode first;
        if (mode == BREADTH_FIRST_MODE) {
          first = moves.removeFirst();
        } else if (mode == DEPTH_FIRST_MODE) {
          first = moves.removeLast();
        } else if (mode == BEST_FIRST_MODE || mode == A_STAR_MODE) {
          first = moves.removeSmallest();
        } else {
          throw new Error("Invalid mode");
        }

        // These are all the possible candidates for movement.
        int[][] candidates = new int[][] {
          new int[] { first.x_ + 1, first.y_ },
          new int[] { first.x_ - 1, first.y_ },
          new int[] { first.x_, first.y_ + 1 },
          new int[] { first.x_, first.y_ - 1}
        };

        // Goes through all four candidates to try and find a valid
        // solution.
        for (int[] candidate : candidates) {
          try {
            if (maze_[candidate[0]][candidate[1]] == 'E') {
              while (first.prev_ != null) {
                maze_[first.x_][first.y_] = 'P';
                solution_.addFirst(first.x_);
                solution_.add(1, first.y_);
                first = first.prev_;
              }
              clearCrapFromMaze();
              outputMaze(animate);
              return true;
            }
            if (verifySquare(candidate)) {
              maze_[candidate[0]][candidate[1]] = '.';
              if (mode == BEST_FIRST_MODE) {
                moves.add(new MoveNode(candidate, first),
                          getPriorityDistance(candidate));
              } else if (mode == A_STAR_MODE) {
                moves.add(new MoveNode(candidate, first),
                          getPriorityDistance(candidate) + numCurrentMoves);
              } else {
                moves.add(new MoveNode(candidate, first));
              }
            }
          } catch (ArrayIndexOutOfBoundsException e) {}
        }

      }
      System.out.println(moves);
      outputMaze(animate);
    }
    return false;
  }
    

  public boolean solveBFS(boolean animate) {
    return solve(animate, BREADTH_FIRST_MODE);
  }

  public boolean solveBFS() {
    return solveBFS(false);
  }

  public boolean solveDFS(boolean animate) {
    return solve(animate, DEPTH_FIRST_MODE);
  }

  public boolean solveDFS() {
    return solveDFS(false);
  }

  public boolean solveBestFirst(boolean animate) {
    return solve(animate, BEST_FIRST_MODE);
  }

  public boolean solveBestFirst() {
    return solveBestFirst(false);
  }

  public boolean solveAStar(boolean animate) {
    return solve(animate, A_STAR_MODE);
  }

  public boolean solveAStar() {
    return solveAStar(false);
  }

  public int[] solutionCoordinates() {
    int[] out = new int[solution_.size()];
    int c = 0;
    for (int i : solution_) {
      out[c] = i;
      c++;
    }
    return out;
  }

  public static void main(String[] args) {
    if (args.length != 2) {
      throw new Error("Usage: java Maze <bfs|dfs|bestfirst|a*> <maze>");
    }
    Maze m = new Maze(args[1]);
    if (args[0].equals("bfs")) {
      m.solveBFS(true);
    } else if (args[0].equals("dfs")) {
      m.solveDFS(true);
    } else if (args[0].equals("bestfirst")) {
      m.solveBestFirst(true);
    } else if (args[0].equals("a*")) {
      m.solveAStar(true);
    } else {
      throw new Error("Invalid method");
    }
    System.out.println(Arrays.toString(m.solutionCoordinates()));
  }
}
