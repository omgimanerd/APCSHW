public class Drive {
  public static void main(String[] args) {
    Rogue p1 = new Rogue("Zed");
    Warrior p2 = new Warrior("Garen");
    Wizard p3 = new Wizard("Ryze");

    System.out.println(p1.getStats());
    System.out.println(p2.getStats());
    System.out.println(p3.getStats());
    p3.attack(p1);
    p1.attack(p3);
    p2.attack(p1);
    p3.specialAttack(p1);
    p2.specialAttack(p1);
    p1.specialAttack(p3);
  }
}
