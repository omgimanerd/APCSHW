import java.util.Random;

public class Rogue extends Adventurer {
  private int stamina_;
  private Random rand_ = new Random();
  
  public Rogue() {
    this("Zed");
  }
  
  public Rogue(String name) {
    this(name, 30, 5);
  }
  
  public Rogue(String name, int stamina, int DEX) {
    super(name);
    this.setStamina(stamina);
    this.setDEX(DEX);
  }
  public int getStamina() {
    return this.stamina_;
  }

  public void setStamina(int stamina) {
    this.stamina_ = stamina;
  }
  
  public String getStats() {
    return super.getStats() + "\tStamina: " + this.getStamina();
  }

  public void attack(Adventurer target) {
    int damage = rand_.nextInt(this.getDEX());
    if (damage > 0) {
      target.setHP(target.getHP() - damage);
      System.out.println(this.getName() + " dealt " + damage +
          " damage to " + target.getName());
    } else {
      System.out.println(this.getName() + " tried to stab " +
          target.getName() + " but missed.");
    }
    System.out.println(this.getStats());
    System.out.println(target.getStats());
  }
  
  public void specialAttack(Adventurer target) {
    int damage = this.getDEX() + rand_.nextInt(this.getDEX());
    if (damage > 0) {
      if (this.getStamina() > damage) {
        this.setStamina(this.getStamina() - damage);
        if (rand_.nextInt(40) < this.getDEX()) {
          damage += 3 * this.getDEX();
          System.out.println("Critical strike!");
        }
        target.setHP(target.getHP() - damage);
        System.out.println(this.getName() + " dealt " + damage +
            " damage to " + target.getName() + " with a shuriken.");
        System.out.println(this.getStats());
        System.out.println(target.getStats());
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
