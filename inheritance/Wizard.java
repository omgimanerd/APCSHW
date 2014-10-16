import java.util.Random;

public class Wizard extends Adventurer {
  private int mana_;
  private Random rand_ = new Random();

  public Wizard() {
    this("Tim");
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

  public void attack(Adventurer target) {
    int damage = rand_.nextInt(3);
    target.setHP(target.getHP() - damage);
    System.out.println(this.getName() + " dealt " + damage +
        " damage to " + target.getName());
    System.out.println(this);
    System.out.println(target);
  }
  
  public void specialAttack(Adventurer target) {
    int damage = this.getINT() + 2 * rand_.nextInt(this.getINT());
    target.setHP(target.getHP() - damage);
    this.setMana(this.getMana() - damage);
    System.out.println(this.getName() + " dealt " + damage +
        " damage to " + target.getName());
    System.out.println(this);
    System.out.println(target);
  }
}
