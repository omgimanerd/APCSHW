import java.util.Random;

public class MartialArtist extends Adventurer {
  private int qi_;
  private Random rand_ = new Random();
  
  public MartialArtist() {
    this("Lee Sin");
  }
  
  public MartialArtist(String name) {
    this(name, 30, 5, 5);
  }
  
  public MartialArtist(String name, int qi, int DEX, int STR) {
    super(name);
    this.setQi(qi);
    this.setDEX(DEX);
    this.setSTR(STR);
  }

  public int getQi() {
    return this.qi_;
  }

  public void setQi(int qi) {
    this.qi_ = qi;
  }
  
  public String getStats() {
    return super.getStats() + "\tQi: " + this.getQi();
  }
  
  public void attack(Adventurer target) {
    int damage = rand_.nextInt(this.getSTR());
    if (damage > 0) {
      target.setHP(target.getHP() - damage);
      System.out.println(this.getName() + " dealt " + damage +
          " damage to " + target.getName());
    } else {
      System.out.println(this.getName() + " tried to punch " +
          target.getName() + " but missed.");
    }
  }
  
  public void specialAttack(Adventurer target) {
    int damage = this.getSTR() + rand_.nextInt(this.getDEX());
    if (damage > 0) {
      if (this.getQi() > damage) {
        this.setQi(this.getQi() - damage);
        if (rand_.nextInt(40) < this.getDEX() + this.getSTR()) {
          damage += this.getDEX() + this.getSTR();
          System.out.println("Critical strike!");
        }
        target.setHP(target.getHP() - damage);
        System.out.println(this.getName() + " dealt " + damage +
            " damage to " + target.getName() + " by yelling Hadouken.");
      } else {
        System.out.println(this + " does not have enough qi!");
        this.attack(target);
      }
    } else {
      System.out.println(this.getName() + " tried to uppercut " +
          target.getName() + " but missed.");
    }
  }
}
