import java.util.Random;

public class Warrior extends Adventurer {
  private Random rand_ = new Random();

  public Warrior() {
    this("Garen");
  }

  public Warrior(String name) {
    super(name);
  }
  
  public String getStats() {
    return super.getStats() + "\tRage: " + this.getExpendableStat();
  }

  public String attack(Adventurer target) {
    String output = "";
    int damage = rand_.nextInt((int)(this.getSTR() * 1.5) + 5);
    if (damage > 0) {
      target.setHP(target.getHP() - damage);
      output += this.getName() + " dealt " + damage +
          " damage to " + target.getName() + " with a strong whack.";
    } else {
      output += this.getName() + " tried to hit " +
          target.getName() + " but missed.";
    }
    return output;
  }
  
  public String specialAttack(Adventurer target) {
    String output = "";
    int damage = rand_.nextInt(this.getSTR() + 5) +
        rand_.nextInt(this.getDEX() + 5);
    if (damage > 0) {
      if (this.getExpendableStat() > damage) {
        this.setExpendableStat(this.getExpendableStat() - damage);
        if (rand_.nextInt(100) <= this.getDEX() + this.getSTR()) {
          damage += this.getSTR();
          output += "Critical strike!\n";
        }
        target.setHP(target.getHP() - damage);
        output += this.getName() + " dealt " + damage +
            " damage to " + target.getName() + " with a furious chop.";
      } else {
        output += this + " does not have enough rage!\n";
        output += this.attack(target);
      }
    } else {
      output += this.getName() + " tried to decapitate " +
          target.getName() + " but missed.";
    }
    return output;
  }
}
