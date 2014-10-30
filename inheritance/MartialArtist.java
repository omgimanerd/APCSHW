import java.util.Random;

public class MartialArtist extends Adventurer {
  private Random rand_ = new Random();

  public MartialArtist() {
    this("Lee Sin");
  }

  public MartialArtist(String name) {
    super(name);
  }

  public String getStats() {
    return super.getStats() + "\tQi: " + this.getExpendableStat();
  }

  public void attack(Adventurer target) {
    int damage = rand_.nextInt(this.getSTR() + 5);
    if (damage > 0) {
      target.setHP(target.getHP() - damage);
      System.out.println(this.getName() + " dealt " + damage + " damage to "
          + target.getName() + " with a strong punch.");
    } else {
      System.out.println(this.getName() + " tried to punch " + target.getName()
          + " but missed.");
    }
  }

  public void specialAttack(Adventurer target) {
    int damage = rand_.nextInt(this.getSTR() + 5)
        + rand_.nextInt(this.getDEX() + 5);
    if (damage > 0) {
      if (this.getExpendableStat() > damage) {
        this.setExpendableStat(this.getExpendableStat() - damage);
        if (rand_.nextInt(70) < this.getDEX() + this.getSTR()) {
          damage += this.getSTR();
          System.out.println("Critical strike!");
        }
        target.setHP(target.getHP() - damage);
        System.out.println(this.getName() + " dealt " + damage + " damage to "
            + target.getName() + " by yelling Hadouken.");
      } else {
        System.out.println(this + " does not have enough qi!");
        this.attack(target);
      }
    } else {
      System.out.println(this.getName() + " tried to uppercut "
          + target.getName() + " but missed.");
    }
  }
}
