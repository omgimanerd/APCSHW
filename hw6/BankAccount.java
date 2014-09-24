public class BankAccount {

  private float balance_;
  private int account_;
  private String username_;
  private String password_;
  private int pin_;

  public BankAccount (float balance, int account,
                      String username, String password,
                      int pin) {

    this.balance_ = balance;
    this.account_ = account;
    this.username_ = username;
    this.password_ = password;
    this.pin_ = pin;
  }

  public void setBalance(float balance) {
    this.balance_ = balance;
  }

  public void setAccount(int account) {
    this.account_ = account;
  }

  public void setUsername(String username) {
    this.username_ = username;
  }

  public void setPassword(String password) {
    this.password_ = password;
  }

  public void setPin(int pin) {
    this.pin_ = pin;
  }

  public void deposit(float amount) {
    if (amount > 0) {
      this.balance_ += amount;
    } else {
      throw new Error("Herpity derp wut are you doing");
    }
  }

  public boolean withdraw(String username, String password, float amount) {
    if (this.username_ == username && this.password_ == password) {
      if (amount > 0 && amount < this.balance_) {
        this.balance_ -= amount;
        return true;
      }
    }
    return false;
  }
  
  public void print() {
    System.out.println("Balance: " + this.balance_);
    System.out.println("Account: " + this.account_);
    System.out.println("Username: " + this.username_);
    
    String pass = "";
    for (int i = 0; i < this.password_.length(); i++) {
      pass+= "*";
    }

    System.out.println("Password: " + pass);
    System.out.println("Pin: " + this.pin_);
  }

  public String toString() {
    return this.username_ + "\t" + this.account_ + "\t" + this.balance_;
  }

  public static void main(String[] args) {
    BankAccount mine = new BankAccount(100, 1, "Alvin", "derp", 3128);
    mine.print();
    mine.setPassword("hi dere");
    mine.deposit(10);
    mine.print();
    System.out.println(mine.toString());
    mine.withdraw("Alvin", "hi dere", 50);
    mine.print();
  }
}
