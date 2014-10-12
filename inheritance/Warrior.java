public class Warrior extends Adventurer {
  private int strength_;

  public Warrior() {
    super("Carrot Ironfounderson");
    this.setStrength(5);
    this.setHP(this.getHP() + 10);
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
}
