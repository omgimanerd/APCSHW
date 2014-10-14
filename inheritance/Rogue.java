import java.util.Random;

public class Rogue extends Adventurer {
  private int stamina_;
  private int dexterity_;
  private Random rand_ = new Random();
  
  public Rogue() {
    this("Herpity derp");
  }
  
  public Rogue(String name) {
    this(name, 30, 5);
  }
  
  public Rogue(String name, int stamina, int dexterity) {
    super(name);
    this.setStamina(stamina);
    this.setDexterity(dexterity);
  }

  public void attack(Adventurer target) {
    int damage = this.getDexterity() + rand_.nextInt(5);
    target.setHP(target.getHP() - damage);
    this.setStamina(this.getStamina() - damage);
    System.out.println(this.getName() + "dealt " + damage +
        " damage to " + target.getName());
    System.out.println(this);
    System.out.println(target);
  }
  
  public int getStamina() {
    return this.stamina_;
  }

  public void setStamina(int stamina) {
    this.stamina_ = stamina;
  }

  public int getDexterity() {
    return this.dexterity_;
  }

  public void setDexterity(int dexterity) {
    this.dexterity_ = dexterity;
  }
  
  public String toString() {
    return super.toString() + "\tStamina: " + this.getStamina() +
        "\tDEX: " + this.getDexterity();
  }
}
