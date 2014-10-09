public class Warrior extends Adventurer {
  private int bonusHealth_;

  public Warrior() {
    super("Carrot Ironfounderson");
    this.bonusHealth_ = 10;
  }

  public int getHP() {
    return this.bonusHealth_ + super.getHP();
  }
  
  public String warcry() {
    return "AaaaaaaaaaahahhahhhhhhghRawwr";
  }
}
