public class Wizard extends Adventurer {
  private int mana_;
  private int intelligence_;

  public Wizard() {
    this("Tim");
  }
  
  public Wizard(String name) {
    this(name, 30, 5);
  }
  
  public Wizard(String name, int mana, int intelligence) {
    super(name);
    this.setMana(mana);
    this.setIntelligence(intelligence);
  }

  public int getMana() {
    return this.mana_;
  }

  public void setMana(int mana) {
    this.mana_ = mana;
  }

  public int getIntelligence() {
    return intelligence_;
  }

  public void setIntelligence(int intelligence) {
    this.intelligence_ = intelligence;
  }
  
  public String toString() {
    return super.toString() + "\tMana: " + this.getMana() +
        "\tINT: " + this.getIntelligence();
  }
}
