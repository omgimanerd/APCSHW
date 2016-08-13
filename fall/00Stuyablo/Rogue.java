import java.util.Random;

public class Rogue extends Adventurer {
  private Random rand_ = new Random();
  
  public Rogue() {
    this("Zed");
  }

  public Rogue(String name) {
    super(name);
  }
  
  public String getStats() {
    return super.getStats() + "\tStamina: " + this.getExpendableStat();
  }

  public String attack(Adventurer target) {
    String output = "";
    int damage = rand_.nextInt(this.getDEX() + 5) +
        rand_.nextInt(this.getSTR() + 5);
    if (damage > 0) {
      target.setHP(target.getHP() - damage);
      output += this.getName() + " dealt " + damage +
          " damage to " + target.getName() + " with a sharp stab.";
    } else {
      output += this.getName() + " tried to stab " +
          target.getName() + " but missed.";
    }
    return output;
  }
  
  public String specialAttack(Adventurer target) {
    String output = "";
    int damage = rand_.nextInt(this.getDEX() + 5) +
        rand_.nextInt((int)(this.getDEX() / 2) + 5);
    if (damage > 0) {
      if (this.getExpendableStat() > damage) {
        this.setExpendableStat(this.getExpendableStat() - damage);
        if (rand_.nextInt(75) <= this.getDEX()) {
          damage += this.getDEX();
          output += "Critical strike!\n";
        }
        target.setHP(target.getHP() - damage);
        output += this.getName() + " dealt " + damage +
            " damage to " + target.getName() + " with a shuriken.";
      } else {
        output += this + " does not have enough stamina!\n";
        output += this.attack(target);
      }
    } else {
      output += this.getName() + " tried to assassinate " +
          target.getName() + " but missed.";
    }
    return output;
  }
  
}
