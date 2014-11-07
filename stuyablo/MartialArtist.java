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

  public String attack(Adventurer target) {
    String output = "";
    int damage = rand_.nextInt(this.getSTR() + 5) +
        rand_.nextInt(this.getDEX() + 5);
    if (damage > 0) {
      target.setHP(target.getHP() - damage);
      output += this.getName() + " dealt " + damage + " damage to "
          + target.getName() + " with a strong punch.";
    } else {
      output += this.getName() + " tried to punch " + target.getName()
          + " but missed.";
    }
    return output;
  }

  public String specialAttack(Adventurer target) {
    String output = "";
    int damage = rand_.nextInt(this.getSTR() + 5) +
        rand_.nextInt(this.getDEX() + 5);
    if (damage > 0) {
      if (this.getExpendableStat() > damage) {
        this.setExpendableStat(this.getExpendableStat() - damage);
        if (rand_.nextInt(100) <= (2 * this.getDEX()) + this.getSTR()) {
          damage += this.getSTR();
          output += "Critical strike!\n";
        }
        target.setHP(target.getHP() - damage);
        output += this.getName() + " dealt " + damage + " damage to "
            + target.getName() + " by yelling Hadouken.";
      } else {
        output += this + " does not have enough qi!\n";
        output += this.attack(target);
      }
    } else {
      output += this.getName() + " tried to uppercut "
          + target.getName() + " but missed.";
    }
    return output;
  }
}
