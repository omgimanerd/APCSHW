import java.util.Random;

public class Adventurer {
  private String name_;
  private int HP_;
  private int STR_, DEX_, INT_;

  public Adventurer() {
    this("Lester");
  }

  public Adventurer(String name) {
    this(name, 20, 0, 0, 0);
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
    return "Name: " + this.getName() + "\tHP: " + this.getHP() +
        "\tSTR: " + this.getSTR() +
        "\tDEX: " + this.getDEX() +
        "\tINT: " + this.getINT();
  }
  
  public void attack(Adventurer target) {
    throw new Error("attack() method not overriden");
  }
  
  public void specialAttack(Adventurer target) {
    throw new Error("specialAttack() method not overriden");
  }
}
