public class Adventurer {
  private String name_;
  private int HP_;

  public Adventurer() {
    this("Lester");
  }
  
  public Adventurer(String name) {
    this(name, 20);
  }

  public Adventurer(String name, int HP) {
    this.setName(name);
    this.setHP(HP);
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

  public String toString() {
    return "Name: "+this.name_+"\tHP: "+this.HP_;
  }
}
