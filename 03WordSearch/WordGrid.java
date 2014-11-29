import java.io.*;
import java.util.*;

public class WordGrid {

  private char[][] data_;
  private ArrayList<String> words = new ArrayList<String>();
  private int rows_, cols_;
  private Random rand_ = new Random();

  /**
   * Default constructor. Initializes the word search array with a height and
   * width of 10.
   */
  public WordGrid() {
    this(10, 10);
  }

  /**
   * Initialize the word search with the specified number of rows and columns
   * and sets the seed of the random number generator.
   * 
   * @param rows The number of rows (height) of the array.
   * @param cols The number of columns (width) of the array.
   */
  public WordGrid(int rows, int cols) {
    this.data_ = new char[rows][cols];
    this.rows_ = rows;
    this.cols_ = cols;
    this.clear();
  }

  /**
   * Sets the seed of the random number generator.
   * @param seed The number to seed the random number generator with.
   */
  public void setSeed(int seed) {
    this.rand_.setSeed(seed);
  }

  /**
   * Given a file name and a boolean, this method tries to fill the grid with as
   * many words as it can from the file.
   * 
   * @param filename The name of the file to read words from.
   * @param fillEmptySpaces Whether or not to fill the blank spaces with
   *        random characters.
   * @throws FileNotFoundException
   */
  public void loadFromFile(String filename, boolean fillEmptySpaces) {
    this.clear();
    File file;
    Scanner read;
    try {
      file = new File(filename);
      read = new Scanner(file);
    } catch (Exception e) {
      System.out.println("File not found.");
      return;
    }

    ArrayList<String> words = new ArrayList<String>();
    while (read.hasNextLine()) {
      String line = read.nextLine();
      words.add(line.toUpperCase());
    }

    for (int i = 0; i < words.size(); ++i) {
      boolean inserted = false;
      int action, row, col;
      for (int ii = 0; ii < 100; ++ii) {
        action = this.rand_.nextInt(8);
        row = this.rand_.nextInt(this.rows_);
        col = this.rand_.nextInt(this.cols_);
        switch (action) {
          case 0:
            inserted = this.addWordDiagonalDL(words.get(i), row, col);
            break;
          case 1:
            inserted = this.addWordDiagonalDR(words.get(i), row, col);
            break;
          case 2:
            inserted = this.addWordDiagonalUL(words.get(i), row, col);
            break;
          case 3:
            inserted = this.addWordDiagonalUR(words.get(i), row, col);
            break;
          case 4:
            inserted = this.addWordHorizontal(words.get(i), row, col);
            break;
          case 5:
            inserted = this.addWordHorizontalB(words.get(i), row, col);
            break;
          case 6:
            inserted = this.addWordVertical(words.get(i), row, col);
            break;
          case 7:
            inserted = this.addWordVerticalB(words.get(i), row, col);
            break;
        }
        if (inserted) {
          break;
        } else if (ii == 99) {
          words.set(i, null);
        }
      }
    }
    
    if (fillEmptySpaces) {
      this.fillEmptySpaces();
    }
  }

  /**
   * Fills the empty spaces in the word search grid with random letters. 
   */
  public void fillEmptySpaces() {
    for (int i = 0; i < this.rows_; ++i) {
      for (int ii = 0; ii < this.cols_; ++ii) {
        if (this.data_[i][ii] != ' ') {
          continue;
        }
        this.data_[i][ii] = (char)(this.rand_.nextInt(26) + 65);
      }
    }
  }
  
  /**
   * Generates a string to show the contents of the word search grid.
   * 
   * @return A string showing the contents of the word search grid.
   */
  public String toString() {
    String out = " ";
    for (int i = 0; i < this.cols_; ++i) {
      out += "__";
    }
    out += "_\n";
    for (int i = 0; i < this.rows_; ++i) {
      out += "| ";
      for (int ii = 0; ii < this.cols_; ++ii) {
        out += this.data_[i][ii] + " ";
      }
      out += "|\n";
    }
    out += "|";
    for (int i = 0; i < this.cols_; ++i) {
      out += "__";
    }
    out += "_|";
    return out;
  }

  /**
   * Generates a string to show the list of added words.
   * 
   * @return A string showing the contents of the word search grid.
   */
  public String wordsInPuzzle() {
    String out = "";
    for (int i = 0; i < words.size(); ++i) {
      out += words.get(i) + "\t";
      if (i % 4 == 0 && i != 0) {
        out += "\n";
      }
    }
    return out;
  }

  private void clear() {
    words.clear();
    for (int i = 0; i < this.rows_; ++i) {
      for (int ii = 0; ii < this.cols_; ++ii) {
        this.data_[i][ii] = ' ';
      }
    }
  }

  /**
   * Attempts to add a given word horizontally at the specified row and column.
   * 
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *         cannot be added.
   */
  private boolean addWordHorizontal(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row][col + i] == ' '
            || this.data_[row][col + i] == word.charAt(i)) {
          continue;
        }
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    for (int i = 0; i < word.length(); ++i) {
      this.data_[row][col + i] = word.charAt(i);
    }
    this.words.add(word);
    return true;
  }

  /**
   * Attempts to add a given word horizontally backward at the specified row and
   * column.
   * 
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *         cannot be added.
   */
  private boolean addWordHorizontalB(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row][col - i] == ' '
            || this.data_[row][col - i] == word.charAt(i)) {
          continue;
        }
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    for (int i = 0; i < word.length(); ++i) {
      this.data_[row][col - i] = word.charAt(i);
    }
    this.words.add(word);
    return true;
  }

  /**
   * Attempts to add a given word vertically at the specified row and column.
   * 
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *         cannot be added.
   */
  private boolean addWordVertical(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row + i][col] == ' '
            || this.data_[row + i][col] == word.charAt(i)) {
          continue;
        }
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    for (int i = 0; i < word.length(); ++i) {
      this.data_[row + i][col] = word.charAt(i);
    }
    this.words.add(word);
    return true;
  }

  /**
   * Attempts to add a given word vertically backward at the specified row and
   * column.
   * 
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *         cannot be added.
   */
  private boolean addWordVerticalB(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row - i][col] == ' '
            || this.data_[row - i][col] == word.charAt(i)) {
          continue;
        }
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    for (int i = 0; i < word.length(); ++i) {
      this.data_[row - i][col] = word.charAt(i);
    }
    this.words.add(word);
    return true;
  }

  /**
   * Attempts to add a given word diagonally down and right at the specified row
   * and column.
   * 
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *         cannot be added.
   */
  private boolean addWordDiagonalDR(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row + i][col + i] == ' '
            || this.data_[row + i][col + i] == word.charAt(i)) {
          continue;
        }
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    for (int i = 0; i < word.length(); ++i) {
      this.data_[row + i][col + i] = word.charAt(i);
    }
    this.words.add(word);
    return true;
  }

  /**
   * Attempts to add a given word diagonally down and left at the specified row
   * and column.
   * 
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *         cannot be added.
   */
  private boolean addWordDiagonalDL(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row + i][col - i] == ' '
            || this.data_[row + i][col - i] == word.charAt(i)) {
          continue;
        }
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    for (int i = 0; i < word.length(); ++i) {
      this.data_[row + i][col - i] = word.charAt(i);
    }
    this.words.add(word);
    return true;
  }

  /**
   * Attempts to add a given word diagonally up and right at the specified row
   * and column.
   * 
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *         cannot be added.
   */
  private boolean addWordDiagonalUR(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row - i][col - i] == ' '
            || this.data_[row - i][col - i] == word.charAt(i)) {
          continue;
        }
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    for (int i = 0; i < word.length(); ++i) {
      this.data_[row - i][col - i] = word.charAt(i);
    }
    this.words.add(word);
    return true;
  }

  /**
   * Attempts to add a given word diagonally up and left at the specified row
   * and column.
   * 
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *         cannot be added.
   */
  private boolean addWordDiagonalUL(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row - i][col + i] == ' '
            || this.data_[row - i][col + i] == word.charAt(i)) {
          continue;
        }
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    for (int i = 0; i < word.length(); ++i) {
      this.data_[row - i][col + i] = word.charAt(i);
    }
    this.words.add(word);
    return true;
  }

  public static void main(String[] args) {
    if (args.length != 4 && args.length != 5) {     
      System.out.println("Usage:");
      System.out.println("java WordGrid <rows> <cols> <filename> <filled> [seed]");
      return;
    }
    try {
      WordGrid grid = new WordGrid(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
      if (args.length == 5) {
        grid.setSeed(Integer.parseInt(args[4]));
      }
      if (args[3].equals("0")) {
        grid.loadFromFile(args[2], false);
      } else {
        grid.loadFromFile(args[2], true);
      }
      System.out.println("Words to find:");
      System.out.println(grid.wordsInPuzzle());
      System.out.println(grid);
    }
    catch (Exception e) {
      System.out.println("Usage:");
      System.out.println("java WordGrid <rows> <cols> <filename> <filled> [seed]");
      e.printStackTrace();
    }
  }
}
