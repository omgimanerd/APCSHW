import java.util.Random;

public class Wizard extends Adventurer {
  private int mana_;
  private int intelligence_;
  private Random rand_ = new Random();

  public Wizard() {
    this("Tim");
  }
  
  public Wizard(String name) {
    this(name, 30, 5);
  }
  
  public Wizard(String name, int mana, int intelligence) {
    super(name);
    this.setMana(mana);
    this.setIntelligence(intelligence);
  }

  public void attack(Adventurer target) {
    int damage = this.getIntelligence() + rand_.nextInt(5);
    target.setHP(target.getHP() - damage);
    this.setMana(this.getMana() - damage);
    System.out.println(this.getName() + " dealt " + damage +
        " damage to " + target.getName());
    System.out.println(this);
    System.out.println(target);
  }
  
  public int getMana() {
    return this.mana_;
  }

  public void setMana(int mana) {
    this.mana_ = mana;
  }

  public int getIntelligence() {
    return this.intelligence_;
  }

  public void setIntelligence(int intelligence) {
    this.intelligence_ = intelligence;
  }
  
  public String toString() {
    return super.toString() + "\tMana: " + this.getMana() +
        "\tINT: " + this.getIntelligence();
  }
}
