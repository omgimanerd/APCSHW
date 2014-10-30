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
  
  public void attack(Adventurer target) {
    int damage = rand_.nextInt(5);
    if (damage > 0) {
      target.setHP(target.getHP() - damage);
      System.out.println(this.getName() + " dealt " + damage +
          " damage to " + target.getName() + " with a blast of mc^2");  
    } else {
      System.out.println(this.getName() + " tried to hit " +
          target.getName() + " but missed.");  
    }
  }
  
  public void specialAttack(Adventurer target) {
    int damage = rand_.nextInt(this.getINT() + 5) + rand_.nextInt(this.getINT() + 5);
    if (damage > 0) {
      if (this.getExpendableStat() > damage) {
        this.setExpendableStat(this.getExpendableStat() - damage);
        if (rand_.nextInt(100) <= this.getINT()) {
          damage += this.getINT();
          System.out.println("Critical blast!");
        }
        target.setHP(target.getHP() - damage);
        System.out.println(this.getName() + " dealt " + damage +
            " damage to " + target.getName() + " with a fireball.");
      } else {
        System.out.println(this + " does not have enough mana!");
        this.attack(target);
      }
    } else {
      System.out.println(this.getName() + " tried to blast " +
          target.getName() + " but missed.");
    }
  }
}
