import java.util.Random;

public class Warrior extends Adventurer {
  private int rage_;
  private Random rand_ = new Random();

  public Warrior() {
    this("Carrot Ironfounderson");
  }

  public Warrior(String name) {
    this(name, 30, 5);
  }

  public Warrior(String name, int rage, int STR) {
    super(name);
    this.setRage(rage);
    this.setSTR(STR);
  }
  
  public int getRage() {
    return rage_;
  }

  public void setRage(int rage) {
    this.rage_ = rage;
  }

  public void attack(Adventurer target) {
    int damage = rand_.nextInt(this.getSTR());
    target.setHP(target.getHP() - damage);
    System.out.println(this.getName() + " dealt " + damage +
        " damage to " + target.getName());
    System.out.println(this);
    System.out.println(target);
  }
  
  public void specialAttack(Adventurer target) {
    int damage = this.getSTR() + rand_.nextInt(this.getSTR());
    target.setHP(target.getHP() - damage);
    this.setRage(this.getRage() - damage);
    System.out.println(this.getName() + " dealt " + damage +
        " damage to " + target.getName());
    System.out.println(this);
    System.out.println(target);
  }
  
  public String warcry() {
    return "DEMACIA!";
  }
}
