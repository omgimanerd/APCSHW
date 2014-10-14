import java.util.Random;

public class Warrior extends Adventurer {
  private int rage_;
  private int strength_;
  private Random rand_ = new Random();

  public Warrior() {
    this("Carrot Ironfounderson");
  }

  public Warrior(String name) {
    this(name, 30, 5);
  }

  public Warrior(String name, int rage, int strength) {
    super(name);
    this.setRage(rage);
    this.setStrength(strength);
  }

  public void attack(Adventurer target) {
    int damage = this.getStrength() + rand_.nextInt(5);
    target.setHP(target.getHP() - damage);
    this.setRage(this.getRage() - damage);
    System.out.println(this.getName() + "dealt " + damage +
        " damage to " + target.getName());
    System.out.println(this);
    System.out.println(target);
  }
  
  public String warcry() {
    return "AaaaaaaaaaahahhahhhhhhghRawwr";
  }

  public int getStrength() {
    return strength_;
  }

  public void setStrength(int strength) {
    this.strength_ = strength;
  }

  public int getRage() {
    return rage_;
  }

  public void setRage(int rage) {
    this.rage_ = rage;
  }
  
  public String toString() {
    return super.toString() + "\tRage: " + this.getRage() +
        "\tSTR: " + this.getStrength();
  }
}
