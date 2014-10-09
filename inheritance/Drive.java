public class Drive {
  public static void main (String[] args) {
    Adventurer p1 = new Adventurer("bob");
    Warrior p2 = new Warrior();
    Wizard p3 = new Wizard();
    System.out.println(p1.getName());
    System.out.println(p2.getName());
    System.out.println(p3.getName());
    System.out.println(p1.getHP());
    System.out.println(p2.getHP());
    System.out.println(p3.getHP());
    System.out.println("Mana:"+p3.getMana());
  }
}
