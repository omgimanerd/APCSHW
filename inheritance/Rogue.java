import java.util.Random;

public class Rogue extends Adventurer {
  private int stamina_;
  private Random rand_ = new Random();
  
  public Rogue() {
    this("Herpity derp");
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

  public void attack(Adventurer target) {
    int damage = rand_.nextInt(this.getDEX());
    target.setHP(target.getHP() - damage);
    System.out.println(this.getName() + " dealt " + damage +
        " damage to " + target.getName());
    System.out.println(this);
    System.out.println(target);
  }
  
  public void specialAttack(Adventurer target) {
    int damage = this.getDEX() + rand_.nextInt(this.getDEX());
    if (rand_.nextInt(50) < this.getDEX()) {
      damage += 2 * this.getDEX();
    }
    target.setHP(target.getHP() - damage);
    this.setStamina(this.getStamina() - damage);
    System.out.println(this.getName() + " dealt " + damage +
        " damage to " + target.getName());
    System.out.println(this);
    System.out.println(target);
  }
  
}
