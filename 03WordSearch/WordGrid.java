public class WordGrid {

  private char[][] data_;
  private int rows_, cols_;

  /**
   * Default constructor. Initializes the word search array with
   * a height and width of 10.
   */
  public WordGrid() {
    this(10, 10);
  }

  /**
   * Initialize the word search with the specified number of rows and
   * columns.
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
   * Returns the string form of the word grid array.
   */
  public String toString() {
    String out = " ";
    for (int i = 0; i < this.rows_; ++i) {
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
    for (int i = 0; i < this.rows_; ++i) {
      out += "__";
    }
    out += "_|";
    return out;
  }

  private void clear() {
    for (int i = 0; i < this.rows_; ++i) {
      for (int ii = 0; ii < this.cols_; ++ii) {
        this.data_[i][ii] = ' ';
      }
    }
  }

  /**
   * Attempts to add a given word horizontally at the specified row and
   * column.
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *   cannot be added.
   */
  public boolean addWordHorizontal(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row][col + i] == ' ' ||
            this.data_[row][col + i] == word.charAt(i)) {
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
    return true;
  }

  /**
   * Attempts to add a given word vertically at the specified row and column.
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *   cannot be added.
   */
  public boolean addWordVertical(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row + i][col] == ' ' ||
            this.data_[row + i][col] == word.charAt(i)) {
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
    return true;
  }

  /**
   * Attempts to add a given word diagonally down and right at the specified
   * row and column.
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *   cannot be added.
   */
  public boolean addWordDiagonalDR(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row + i][col + i] == ' ' ||
            this.data_[row + i][col + i] == word.charAt(i)) {
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
    return true;
  }

  /**
   * Attempts to add a given word diagonally down and left at the specified
   * row and column.
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *   cannot be added.
   */
  public boolean addWordDiagonalDL(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row + i][col - i] == ' ' ||
            this.data_[row + i][col - i] == word.charAt(i)) {
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
    return true;
  }

  /**
   * Attempts to add a given word diagonally up and right at the specified
   * row and column.
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *   cannot be added.
   */
  public boolean addWordDiagonalUR(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row - i][col - i] == ' ' ||
            this.data_[row - i][col - i] == word.charAt(i)) {
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
    return true;
  }

  /**
   * Attempts to add a given word diagonally up and left at the specified
   * row and column.
   * @param word A specified word.
   * @param row The vertical location to add the word at.
   * @param col The horizontal location to add the word at.
   * @return true when the word is added successfully and false if the word
   *   cannot be added.
   */
  public boolean addWordDiagonalUL(String word, int row, int col) {
    try {
      for (int i = 0; i < word.length(); ++i) {
        if (this.data_[row - i][col + i] == ' ' ||
            this.data_[row - i][col + i] == word.charAt(i)) {
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
    return true;
  }

  public static void main(String[] args) {
    WordGrid grid = new WordGrid(12, 12);
    grid.addWordHorizontal("test", 1, 1);
    grid.addWordHorizontal("konstans", 3, 4);
    grid.addWordVertical("test", 1, 1);
    grid.addWordDiagonalDR("tornado", 1, 1);

    System.out.println(grid);
  }
}
