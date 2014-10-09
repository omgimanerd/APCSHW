public class Adventurer {
  private String name_;
  private int XP_,HP_;

  public Adventurer() {
    this("Lester");
  }
  
  public Adventurer(String name) {
    this.name_ = name;
    this.XP_ = 0;
    this.HP_ = 20;
  }
  
  public String getName() {
    return this.name_;
  }
   
  public void setName(String name) {
    this.name_ = name;
  }

  public int getHP() {
    return HP_;
  }

  public void setHP(int HP) {
    this.HP_ = HP;
  }
}
