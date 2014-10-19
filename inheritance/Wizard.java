import java.util.Random;

public class Wizard extends Adventurer {
  private int mana_;
  private Random rand_ = new Random();

  public Wizard() {
    this("Ryze");
  }
  
  public Wizard(String name) {
    this(name, 30, 5);
  }
  
  public Wizard(String name, int mana, int INT) {
    super(name);
    this.setMana(mana);
    this.setINT(INT);
  }
  
  public int getMana() {
    return this.mana_;
  }

  public void setMana(int mana) {
    this.mana_ = mana;
  }
  
  public String getStats() {
    return super.getStats() + "\tMana: " + this.getMana();
  }
  
  public void specialAttack(Adventurer target) {
    int damage = this.getINT() + 2 * rand_.nextInt(this.getINT());
    if (damage > 0) {
      if (this.getMana() > damage) {
        target.setHP(target.getHP() - damage);
        this.setMana(this.getMana() - damage);
        System.out.println(this.getName() + " dealt " + damage +
            " damage to " + target.getName() + " with a fireball.");
        System.out.println(this.getStats());
        System.out.println(target.getStats());
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
