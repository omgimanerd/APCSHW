public class BankAccount {

  private float balance_;
  private int account_;
  private String username_;
  private String password_;
  private int pin_;

  public BankAccount (float balance, int account,
                      String username, String password,
                      int pin) {

    this.setBalance(balance);
    this.setAccount(account);
    this.setUsername(username);
    this.setPassword(password);
    this.setPin(pin);
  }

  public void setBalance(float balance) {
    this.balance_ = balance;
  }

  public void setAccount(int account) {
    if (account >= 100000000 && account < 999999999) {
      this.account_ = account;
    } else {
      this.account_ = 999999999;
      System.out.println("Invalid account number");
    }
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

  public boolean authenticate(int account, String password) {
    return this.account_ == account && this.password_ == password;
  }

  public boolean deposit(float amount) {
    if (amount > 0) {
      this.balance_ += amount;
      return true;
    }
    return false;
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
    BankAccount mine = new BankAccount(100, 111111111, "Alvin", "derp", 3128);
    mine.print();
    mine.setPassword("hi dere");
    mine.deposit(10);
    mine.print();

    System.out.println(mine.toString());
    mine.withdraw("Alvin", "hi dere", 50);
    mine.print();
    System.out.println(mine.authenticate(111111111, "hi dere"));
  }
}
