import java.util.Random;
import java.util.Scanner;

public class Game {
  
  public static void out(String str) {
    System.out.println(str);
  }
  
  public static void out(Adventurer adv) {
    System.out.println(adv.toString());
  }
  
  public static void main(String[] args) {
    // Random number generator object.
    Random rand = new Random();
    Scanner input = new Scanner(System.in);
    out("Select a class to play as.");
    out("a. Wizard.");
    out("b. Warrior.");
    out("c. Rogue.");
    
    // Allows the user to select a class to play as.
    String classSelect = "";
    while (!classSelect.equals("a") && !classSelect.equals("b") && !classSelect.equals("c")) {
      classSelect = input.nextLine();
      if (!classSelect.equals("a") && !classSelect.equals("b") && !classSelect.equals("c")) {
        out("Invalid selection.");
      }
    }
    
    // Allows the user to name themselves.
    out("Name thyself.");
    String playerName = input.nextLine();
    Adventurer player = null;
    // Generates the player with some random stats.
    int expendableStat = 25 + rand.nextInt(10);
    int powerStat = 5 + rand.nextInt(5);
    switch (classSelect) {
      case "a":
        player = new Wizard(playerName, expendableStat, powerStat);
        out("Welcome to Stuyablo, Wizard " + playerName + ".");
        break;
      case "b":
        player = new Warrior(playerName, expendableStat, powerStat);
        out("Welcome to Stuyablo, Warrior " + playerName + ".");
        break;
      case "c":
        player = new Rogue(playerName, expendableStat, powerStat);
        out("Welcome to Stuyablo, Rogue " + playerName + ".");
    }
    
    // Randomly selects an opponent class.
    out("Your opponent has appeared.");
    int opponentSelect = rand.nextInt(3);
    int opponentExpendableStat = 25 + rand.nextInt(10);
    int opponentPowerStat = 5 + rand.nextInt(5);
    Adventurer opponent = null;
    switch (opponentSelect) {
      case 0:
        opponent = new Wizard("Xerath", opponentExpendableStat, opponentPowerStat);
        out("You will now combat the Wizard " + opponent + ".");
        break;
      case 1:
        opponent = new Warrior("Darius", opponentExpendableStat, opponentPowerStat);
        out("You will now combat the Warrior " + opponent + ".");
        break;
      case 2:
        opponent = new Rogue("Akali", opponentExpendableStat, opponentPowerStat);
        out("You will now combat the Rogue " + opponent + ".");
    }
    
    // Determines whether the player or the opponent attacks first.
    boolean turn = rand.nextBoolean();
    if (turn) {
      out("Your opponent attacked you first.");
      boolean attacktype = rand.nextBoolean();
      if (attacktype) {
        opponent.attack(player);
      } else {
        opponent.specialAttack(player);
      }
    } else {
      out("You shall attack your opponent first.");
    }
    out(player.getStats());
    
    // Determines the action that the user will undertake.
    String choice = "";
    while(player.getHP() > 0 && opponent.getHP() > 0) {
      out("What shall you do?");
      out("a. Attack.");
      out("b. Special Attack");
      out("c. Surrender");
      
      while (!choice.equals("a") && !choice.equals("b") && !choice.equals("c")) {
        choice = input.nextLine();
        if (!choice.equals("a") && !choice.equals("b") && !choice.equals("c")) {
          out("Invalid action.");
        }
      }
      
      switch(choice) {
        case "a":
          player.attack(opponent);
          break;
        case "b":
          player.specialAttack(opponent);
          break;
        case "c":
          player.setHP(0);
      }
      
      int ebolaChance = rand.nextInt(50);
      if (ebolaChance > 5) {
        out("You suddenly died from Ebola.");
        player.setHP(0);
      }
      
      // Loop will exit if either player has already died.
      if (opponent.getHP() <= 0 || player.getHP() <= 0) {
        break;
      }
      
      boolean opponentAction = rand.nextBoolean();
      if (opponentAction) {
        opponent.specialAttack(player);
      } else {
        opponent.attack(player);
      }
      
      // Resets the choice string to allow the next action to be read.
      choice = "";
    }
  
    // Outputs the state of the battle.
    if (player.getHP() <= 0) {
      out("You lose the battle.");
    } else {
      out("You win the battle.");
    }
    
    input.close();
  }
}
