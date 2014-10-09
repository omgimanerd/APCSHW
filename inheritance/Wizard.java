public class Wizard extends Adventurer {
  private int mana_;
    
  public Wizard() {
    super("Tim");
    this.mana_ = 25;
  }

  public int getMana() {
    return this.mana_;
  }

  public void setMana(int mana) {
    this.mana_ = mana;
  }
}
