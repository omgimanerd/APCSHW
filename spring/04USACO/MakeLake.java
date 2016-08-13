import java.util.*;
import java.io.*;

public class MakeLake {

  private int[][] elevations_;
  private int waterLevel_;

  public MakeLake(int[][] elevations, int waterLevel) {
    elevations_ = elevations;
    waterLevel_ = waterLevel;
  }

  public static int parseFileForVolume(String filename) {
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

    String[] data = lines.get(0).split(" ");
    int r = Integer.parseInt(data[0]);
    int c = Integer.parseInt(data[1]);
    int e = Integer.parseInt(data[2]);
    int n = Integer.parseInt(data[3]);

    int[][] elevations = new int[r][c];
    for (int i = 0; i < r; ++i) {
      String[] line = lines.get(1 + i).split(" ");
      for (int j = 0; j < c; ++j) {
        elevations[i][j] = Integer.parseInt(line[j]);
      }
    }

    MakeLake lake = new MakeLake(elevations, e);
    System.out.println(lake);
    
    for (int i = r + 1; i < r + 1 + n; ++i) {
      String[] stomp = lines.get(i).split(" ");
      int stompR = Integer.parseInt(stomp[0]) - 1;
      int stompC = Integer.parseInt(stomp[1]) - 1;
      int stompD = Integer.parseInt(stomp[2]);
      lake.stomp(stompR, stompC, stompD);
      System.out.println(lake);
    }

    return lake.getVolume();
  }    

  public String toString() {
    String output = "";
    for (int i = 0; i < elevations_.length; ++i) {
      for (int j = 0; j < elevations_[i].length; ++j) {
        output += elevations_[i][j] + " ";
      }
      output += "\n";
    }
    return output;
  }

  private int findHighestElevation(int r, int c) {
    int max = elevations_[r][c];
    try {
      for (int i = r; i < r + 3; ++i) {
        for (int j = c; j < c + 3; ++j) {
          if (elevations_[i][j] > max) {
            max = elevations_[i][j];
          }
        }
      }
    } catch (Exception e) {}
    return max;
  }

  public void stomp(int r, int c, int d) {
    int highestElevation = findHighestElevation(r, c) - d;
    try {
      for (int i = r; i < r + 3; ++i) {
        for (int j = c; j < c + 3; ++j) {
          if (elevations_[i][j] > highestElevation) {
            elevations_[i][j] = highestElevation;
          }
        }
      }
    } catch (Exception e) {}
  }
  
  public int getVolume() {
    int aggregatedDepth = 0;
    for (int i = 0; i < elevations_.length; ++i) {
      for (int j = 0; j < elevations_[i].length; ++j) {
        if (elevations_[i][j] < waterLevel_) {
          aggregatedDepth += waterLevel_ - elevations_[i][j];
        }
      }
    }
    return aggregatedDepth * 72 * 72;
  }

  public static void main(String[] args) {
    System.out.println(MakeLake.parseFileForVolume(args[0]));
  }
}
