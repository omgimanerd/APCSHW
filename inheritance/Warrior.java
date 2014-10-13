public class Warrior extends Adventurer {
  private int rage_;
  private int strength_;

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
