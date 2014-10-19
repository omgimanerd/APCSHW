import java.util.Random;

public class Adventurer {
  private String name_;
  private int HP_, STR_, DEX_, INT_;
  private Random rand_ = new Random();
  
  public Adventurer() {
    this("Lester");
  }

  public Adventurer(String name) {
    this(name, 30, 0, 0, 0);
  }

  public Adventurer(String name, int HP,
      int STR, int DEX, int INT) {
    this.setName(name);
    this.setHP(HP);
    this.setSTR(STR);
    this.setDEX(DEX);
    this.setINT(INT);
  }

  public String getName() {
    return this.name_;
  }

  public void setName(String name) {
    this.name_ = name;
  }

  public int getHP() {
    return this.HP_;
  }

  public void setHP(int HP) {
    this.HP_ = HP;
  }

  public int getSTR() {
    return this.STR_;
  }

  public void setSTR(int STR) {
    this.STR_ = STR;
  }

  public int getDEX() {
    return this.DEX_;
  }

  public void setDEX(int DEX) {
    this.DEX_ = DEX;
  }

  public int getINT() {
    return this.INT_;
  }

  public void setINT(int INT) {
    this.INT_ = INT;
  }

  public String toString() {
    return getName();
  }
  
  public String getStats() {
    return this.getName() + 
        "\tHP: " + this.getHP() +
        "\tSTR: " + this.getSTR() +
        "\tDEX: " + this.getDEX() +
        "\tINT: " + this.getINT();
  }
  
  public void attack(Adventurer target) {
    int damage = rand_.nextInt(3);
    if (damage > 0) {
      target.setHP(target.getHP() - damage);
      System.out.println(this.getName() + " dealt " + damage +
          " damage to " + target.getName());  
    } else {
      System.out.println(this.getName() + " tried to hit " +
          target.getName() + " but missed.");  
    }
    System.out.println(this.getStats());
    System.out.println(target.getStats());
  }
  
  public void specialAttack(Adventurer target) {
    int damage = rand_.nextInt(7);
    if (damage > 0) {
      target.setHP(target.getHP() - damage);
      System.out.println(this.getName() + " dealt " + damage +
          " damage to " + target.getName() + "in a furious assault.");
    } else {
      System.out.println(this.getName() + " tried to murder " +
          target.getName() + " but failed.");  
    }
    System.out.println(this.getStats());
    System.out.println(target.getStats());
  }
}
