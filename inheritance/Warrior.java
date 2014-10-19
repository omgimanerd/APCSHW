import java.util.Random;

public class Warrior extends Adventurer {
  private int rage_;
  private Random rand_ = new Random();

  public Warrior() {
    this("Garen");
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
  
  public String getStats() {
    return super.getStats() + "\tRage: " + this.getRage();
  }

  public void attack(Adventurer target) {
    int damage = rand_.nextInt(this.getSTR());
    if (damage > 0) {
      target.setHP(target.getHP() - damage);
      System.out.println(this.getName() + " dealt " + damage +
          " damage to " + target.getName() + " with a strong whack.");
    } else {
      System.out.println(this.getName() + " tried to hit " +
          target.getName() + " but missed.");
    }
    System.out.println(this.getStats());
    System.out.println(target.getStats());
  }
  
  public void specialAttack(Adventurer target) {
    int damage = this.getSTR() + rand_.nextInt(this.getSTR());
    if (damage > 0) {
      if (this.getRage() > damage) {
        target.setHP(target.getHP() - damage);
        this.setRage(this.getRage() - damage);
        System.out.println(this.getName() + " dealt " + damage +
            " damage to " + target.getName() + " with a furious chop.");
        System.out.println(this.getStats());
        System.out.println(target.getStats());
      } else {
        System.out.println(this + " does not have enough rage!");
        this.attack(target);
      }
    } else {
      System.out.println(this.getName() + " tried to decapitate " +
          target.getName() + " but missed.");
    }
  }
  
  public String warcry() {
    return "DEMACIA!";
  }
}
