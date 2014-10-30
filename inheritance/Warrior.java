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

  public void attack(Adventurer target) {
    int damage = rand_.nextInt(this.getSTR() + 5);
    if (damage > 0) {
      target.setHP(target.getHP() - damage);
      System.out.println(this.getName() + " dealt " + damage +
          " damage to " + target.getName() + " with a strong whack.");
    } else {
      System.out.println(this.getName() + " tried to hit " +
          target.getName() + " but missed.");
    }
  }
  
  public void specialAttack(Adventurer target) {
    int damage = this.getSTR() + rand_.nextInt(this.getDEX() + 5);
    if (damage > 0) {
      if (this.getExpendableStat() > damage) {
        this.setExpendableStat(this.getExpendableStat() - damage);
        if (rand_.nextInt(40) <= this.getDEX()) {
          damage += this.getDEX();
          System.out.println("Critical strike!");
        }
        target.setHP(target.getHP() - damage);
        System.out.println(this.getName() + " dealt " + damage +
            " damage to " + target.getName() + " with a furious chop.");
      } else {
        System.out.println(this + " does not have enough rage!");
        this.attack(target);
      }
    } else {
      System.out.println(this.getName() + " tried to decapitate " +
          target.getName() + " but missed.");
    }
  }
}
