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

  public void attack(Adventurer target) {
    int damage = rand_.nextInt(this.getDEX() + 5) + rand_.nextInt(this.getSTR() + 5);
    if (damage > 0) {
      target.setHP(target.getHP() - damage);
      System.out.println(this.getName() + " dealt " + damage +
          " damage to " + target.getName() + " with a sharp stab.");
    } else {
      System.out.println(this.getName() + " tried to stab " +
          target.getName() + " but missed.");
    }
  }
  
  public void specialAttack(Adventurer target) {
    int damage = rand_.nextInt(this.getDEX() + 5) + rand_.nextInt(this.getDEX() + 5);
    if (damage > 0) {
      if (this.getExpendableStat() > damage) {
        this.setExpendableStat(this.getExpendableStat() - damage);
        if (rand_.nextInt(70) <= this.getDEX()) {
          damage += this.getDEX();
          System.out.println("Critical strike!");
        }
        target.setHP(target.getHP() - damage);
        System.out.println(this.getName() + " dealt " + damage +
            " damage to " + target.getName() + " with a shuriken.");
      } else {
        System.out.println(this + " does not have enough stamina!");
        this.attack(target);
      }
    } else {
      System.out.println(this.getName() + " tried to assassinate " +
          target.getName() + " but missed.");
    }
  }
  
}
