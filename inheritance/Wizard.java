import java.util.Random;

public class Wizard extends Adventurer {
  private Random rand_ = new Random();

  public Wizard() {
    this("Ryze");
  }
  
  public Wizard(String name) {
    super(name);
  }
  
  public String getStats() {
    return super.getStats() + "\tMana: " + this.getExpendableStat();
  }
  
  public String attack(Adventurer target) {
    String output = "";
    int damage = rand_.nextInt(this.getINT() / 2);
    if (damage > 0) {
      target.setHP(target.getHP() - damage);
      output += this.getName() + " dealt " + damage +
          " damage to " + target.getName() + " with a blast of mc^2";  
    } else {
      output += this.getName() + " tried to hit " +
          target.getName() + " but missed.";
    }
    return output;
  }
  
  public String specialAttack(Adventurer target) {
    String output = "";
    int damage = rand_.nextInt(this.getINT() + 5) + rand_.nextInt(this.getINT() + 5);
    if (damage > 0) {
      if (this.getExpendableStat() > damage) {
        this.setExpendableStat(this.getExpendableStat() - damage);
        if (rand_.nextInt(100) <= this.getINT()) {
          damage += this.getINT();
          output += "Critical blast!\n";
        }
        target.setHP(target.getHP() - damage);
        output += this.getName() + " dealt " + damage +
            " damage to " + target.getName() + " with a fireball.";
      } else {
        output += this + " does not have enough mana!\n";
        output += this.attack(target);
      }
    } else {
      output += this.getName() + " tried to blast " +
          target.getName() + " but missed.";
    }
    return output;
  }
}
