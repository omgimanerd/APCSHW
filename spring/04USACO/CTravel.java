import java.util.*;
import java.io.*;

public class CTravel {
  
  private char[][] map_;
  private int solutions_;
  private int[] pStart_;
  private int[] pEnd_;
  private int time_;

  private CTravel (char[][] map, int[] pStart, int[] pEnd, int time) {
    setNewPuzzle(map, pStart, pEnd, time);
    solutions_ = 0;
  }

  public static int parseFileForSolutions(String filename) {
    File file;
    Scanner read;
    try {
      file = new File(filename);
      read = new Scanner(file);
    } catch (Exception exception) {
      throw new Error("File not found.");
    }
    
    ArrayList<String> lines = new ArrayList<String>();
    while (read.hasNextLine()) {
      String line = read.nextLine();
      lines.add(line.toUpperCase());
    }

    String[] data = lines.get(0).split(" ");
    int n = Integer.parseInt(data[0]);
    int m = Integer.parseInt(data[1]);
    int t = Integer.parseInt(data[2]);
    
    char map[][] = new char[n][m];
    for (int i = 0; i < n; ++i) {
      String line = lines.get(i + 1);
      for (int j = 0; j < m; ++j) {
        map[i][j] = line.charAt(j);
      }
    }
    
    String[] coords = lines.get(n + 1).split(" ");
    int[] pStart = new int[] {
      Integer.parseInt(coords[0]) - 1, Integer.parseInt(coords[1]) - 1
    };
    int[] pEnd = new int[] {
      Integer.parseInt(coords[2]) - 1, Integer.parseInt(coords[3]) - 1
    };
    
    CTravel ctravel = new CTravel(map, pStart, pEnd, t);
    return ctravel.solve();
  }

  public int solve() {
    solve(pStart_, pEnd_, time_);
    return solutions_;
  }

  public void solve(int[] s, int[] e, int time) {
    try {
      if (time == 0 && s[0] == e[0] && s[1] == e[1]) {
        solutions_++;
        return;
      }
      if (map_[s[0]][ s[1]] == '*' || time == 0) {
        return;
      }
      int[] candidate1 = new int[] {
        s[0] + 1, s[1]
      };
      int[] candidate2 = new int[] {
        s[0], s[1] + 1
      };
      int[] candidate3 = new int[] {
        s[0] - 1, s[1]
      };
      int[] candidate4 = new int[] {
        s[0], s[1] - 1
      };
      solve(candidate1, e, time - 1);
      solve(candidate2, e, time - 1);
      solve(candidate3, e, time - 1);
      solve(candidate4, e, time - 1);
    } catch (Exception exception) {}
  }

  public void setNewPuzzle(char[][] map, int[] pStart, int[] pEnd, int time) {
    map_ = map;
    pStart_ = pStart;
    pEnd_ = pEnd;
    time_ = time;
    solutions_ = 0;
  }

  public static void main(String[] args) {
    System.out.print(CTravel.parseFileForSolutions(args[0]));
    System.out.println(" solutions.");
  }
}
