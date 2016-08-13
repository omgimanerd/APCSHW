public class Recursion {

  public String name() {
    return "Lin,Alvin";
  }

  public int fact(int n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    if (n == 0) {
      return 1;
    }
    return n <= 1 ? n : n * fact(n - 1);
  }

  public int fib(int n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    if (n == 0 || n == 1) {
      return n;
    }
    return fib(n - 1) + fib(n - 2);
  }

  private double newGuess(double n, double guess) {
    return ((n / guess) + guess) / 2.0;
  }

  private double squirt(double n, double guess) {
    if (Math.abs(newGuess(n, guess) - guess) < 0.01) {
      return newGuess(n, guess);
    }
    return squirt(n, newGuess(n, guess));
  }

  public double sqrt(double n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    return squirt(n, n / 2.0);
  }

  public  int sumDigits(int n) {
    n = Math.abs(n);
    return n == 0 ? n : (n % 10) + sumDigits(n / 10);
  }

  private int sumDigitsTailHelper(int sum, int n) {
    n = Math.abs(n);
    return n == 0 ? sum + n : sumDigitsTailHelper(n % 10 + sum, n / 10);
  }

  public int sumDigitsTail(int n) {
    return sumDigitsTailHelper(0, n);
  }

  private boolean primeHelper(int n, int c) {
    if (c * c > n) {
      return true;
    }
    return n % c == 0 ? false : primeHelper(n, c + 1);
  }

  public boolean prime(int n) {
    return n == 1 ? false : primeHelper(n, 2);
  }
}
