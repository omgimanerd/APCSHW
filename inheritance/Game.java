import java.util.*;

public class Game {
  
  public static void out(String str) {
    System.out.println(str);
  }
  
  public static void out(Adventurer adventurer) {
    System.out.println(adventurer.toString());
  }
  
  public static Adventurer userSelectClass() {
    Random rand = new Random();
    Scanner input = new Scanner(System.in);
    out("Select a class:");
    out("a. Wizard.");
    out("b. Warrior.");
    out("c. Rogue.");
    out("d. Martial Artist.");
    
    // Allows the user to select a class.
    String classSelect = "";
    while (
        !classSelect.equals("a") &&
        !classSelect.equals("b") &&
        !classSelect.equals("c") &&
        !classSelect.equals("d")) {
      classSelect = input.nextLine();
      if (!classSelect.equals("a") &&
          !classSelect.equals("b") &&
          !classSelect.equals("c") &&
          !classSelect.equals("d")) {
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
    int secondaryPowerStat = 5 + rand.nextInt(5);
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
        break;
      case "d":
        player = new MartialArtist(playerName, expendableStat, powerStat, secondaryPowerStat);
        out("Welcome to Stuyablo, Martial Artist " + playerName + ".");
    }
    
    input.close();
    return player;
  }
  
  public static Adventurer randomSelectOpponentClass() {
    Random rand = new Random();
    // Randomly selects an opponent class.
    out("Your opponent has appeared.");
    int opponentSelect = rand.nextInt(4);
    int opponentExpendableStat = 25 + rand.nextInt(10);
    int opponentPowerStat = 5 + rand.nextInt(5);
    int opponentSecondaryPowerStat = 5 + rand.nextInt(5);
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
        break;
      case 3:
        opponent = new MartialArtist("Lee Sin", opponentExpendableStat,
            opponentPowerStat, opponentSecondaryPowerStat);
    }
    
    return opponent;
  }
  
  public static void playerCombat(Adventurer player, Adventurer[] opponents) {
    Scanner input = new Scanner(System.in);
    String userInput = "";
    
    out("Who will " + player + " attack?");
    
    while(!userInput.equals("") && opponents[Integer.parseInt(userInput)].getHP() < 0) {
      userInput = input.nextLine();
      if (opponents[Integer.parseInt(userInput)].getHP() < 0) {
        out(opponents[Integer.parseInt(userInput)].toString() + "is already dead.");
      }
    }
    
    out("What shall you do?");
    out("a. Attack");
    out("b. Special Attack");
    out("c. Commit suicide");

    while (!userInput.equals("a") && !userInput.equals("b") && !userInput.equals("c")) {
      userInput = input.nextLine();
      if (!userInput.equals("a") && !userInput.equals("b") && !userInput.equals("c")) {
        out("Invalid action.");
      }
    }
    switch(userInput) {
      case "a":
        player.attack(opponents[Integer.parseInt(userInput)]);
        break;
      case "b":
        player.specialAttack(opponents[Integer.parseInt(userInput)]);
        break;
      case "c":
        player.setHP(0);
    }
    input.close();
  }
  
  public static void opponentCombat(Adventurer opponent, Adventurer[] players) {
    Random rand = new Random();
    
    for (int i = 0; i < players.length; ++i) {
      if (players[i].getHP() > 0) {
        boolean action = rand.nextBoolean();
        if (action) {
          opponent.specialAttack(players[i]);
        } else {
          opponent.attack(players[i]);
        }
        return;
      }
    }
  }
  
  public static void outputLivingCombatants(Adventurer[] players, Adventurer[] opponents) {
    out("Your party:");
    for (int i = 0; i < players.length; ++i) {
      if (players[i].getHP() > 0) {
        out(players[i]);
      }
    }
    out("Opponents:");
    for (int i = 0; i < opponents.length; ++i) {
      if (opponents[i].getHP() > 0) {
        out(opponents[i]);
      }
    }
  }
  
  public static void main(String[] args) {
    Random rand = new Random();
    Scanner input = new Scanner(System.in);
    
    out("Welcome to Stuyablo, create a party of 3.");
    
    Adventurer[] playerParty = new Adventurer[3];
    for (int i = 0; i < playerParty.length; ++i) {
      playerParty[i] = userSelectClass();
      out(playerParty[i] + " has joined your party.");
    }
    
    Adventurer[] opponentParty = new Adventurer[3];
    for (int i = 0; i < opponentParty.length; ++i) {
      opponentParty[i] = randomSelectOpponentClass();
      out("You will fight " + opponentParty[i]);
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
    
    // Determines the action that the user will undertake.
    while(player.getHP() > 0 && opponent.getHP() > 0) {
      
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
