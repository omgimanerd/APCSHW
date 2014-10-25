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
    @SuppressWarnings("resource")
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
        out("Wizard " + playerName + " has joined your party.");
        break;
      case "b":
        player = new Warrior(playerName, expendableStat, powerStat);
        out("Warrior " + playerName + " has joined your party.");
        break;
      case "c":
        player = new Rogue(playerName, expendableStat, powerStat);
        out("Rogue " + playerName + " has joined your party.");
        break;
      case "d":
        player = new MartialArtist(playerName, expendableStat, powerStat, secondaryPowerStat);
        out("Martial Artist " + playerName + " has joined your party.");
    }
    
    return player;
  }
  
  public static Adventurer randomSelectOpponentClass() {
    Random rand = new Random();
    // Randomly selects an opponent class.
    int opponentSelect = rand.nextInt(4);
    int opponentExpendableStat = 25 + rand.nextInt(10);
    int opponentPowerStat = 5 + rand.nextInt(5);
    int opponentSecondaryPowerStat = 5 + rand.nextInt(5);
    Adventurer opponent = null;
    switch (opponentSelect) {
      case 0:
        opponent = new Wizard("Xerath", opponentExpendableStat, opponentPowerStat);
        out("You will combat the Wizard " + opponent + ".");
        break;
      case 1:
        opponent = new Warrior("Darius", opponentExpendableStat, opponentPowerStat);
        out("You will combat the Warrior " + opponent + ".");
        break;
      case 2:
        opponent = new Rogue("Akali", opponentExpendableStat, opponentPowerStat);
        out("You will combat the Rogue " + opponent + ".");
        break;
      case 3:
        opponent = new MartialArtist("Lee Sin", opponentExpendableStat,
            opponentPowerStat, opponentSecondaryPowerStat);
        out("You will combat the Martial Artist " + opponent + ".");
    }
    
    return opponent;
  }
  
  public static void playerCombat(Adventurer player, Adventurer[] opponents) {
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);
    
    String target = "";
    out("Who will " + player + " attack?");
    String selectionTitle = "abc";
    for (int i = 0; i < opponents.length; ++i) {
      System.out.println(selectionTitle.charAt(i) + ". " + opponents[i].getStats());
    }
    
    while (!target.equals("a") && !target.equals("b") && !target.equals("c")) {
      target = input.nextLine();
      if (target.equals("a") || target.equals("b") || target.equals("c")) {
        if (opponents[selectionTitle.indexOf(target)].getHP() <= 0) {
          out(opponents[selectionTitle.indexOf(target)].toString() + " is already dead.");
          out("Select another target.");
          target = "";
        }
      }
    }
    
    out("What shall you do?");
    out("a. Attack");
    out("b. Special Attack");
    out("c. Commit suicide");

    String action = "";
    while (!action.equals("a") && !action.equals("b") && !action.equals("c")) {
      action = input.nextLine();
      if (!action.equals("a") && !action.equals("b") && !action.equals("c")) {
        out("Invalid action.");
      }
    }
    switch(action) {
      case "a":
        player.attack(opponents[selectionTitle.indexOf(target)]);
        break;
      case "b":
        player.specialAttack(opponents[selectionTitle.indexOf(target)]);
        break;
      case "c":
        player.setHP(0);
    }
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
        out(players[i].getStats());
      }
    }
    out("Opponents:");
    for (int i = 0; i < opponents.length; ++i) {
      if (opponents[i].getHP() > 0) {
        out(opponents[i].getStats());
      }
    }
  }
  
  public static boolean isGroupAlive(Adventurer[] group) {
    boolean[] status = new boolean[3];
    for (int i = 0; i < group.length; ++i) {
      status[i] = group[i].getHP() > 0;
    }
    return status[0] || status[1] || status[2];
  }  
  
  public static void main(String[] args) {
    Random rand = new Random();
    Scanner input = new Scanner(System.in);
    
    out("Welcome to Stuyablo, create a party of 3.");
    
    Adventurer[] playerParty = new Adventurer[3];
    for (int i = 0; i < playerParty.length; ++i) {
      playerParty[i] = userSelectClass();
    }
    
    out("Your opponents have appeared.");
    Adventurer[] opponentParty = new Adventurer[3];
    for (int i = 0; i < opponentParty.length; ++i) {
      opponentParty[i] = randomSelectOpponentClass();
    }
    
    // Determines whether the player or the opponent attacks first.
    boolean turn = rand.nextBoolean();
    if (turn) {
      out("Your opponents attacked you first.");
      for (int i = 0; i < opponentParty.length; ++i) {
        if (opponentParty[i].getHP() > 0) {
          opponentCombat(opponentParty[i], playerParty);
        }
      }
    } else {
      out("You shall attack your opponents first.");
    }
    
    // Determines the action that the user will undertake.
    while (isGroupAlive(playerParty) && isGroupAlive(opponentParty)) {
      // Handle player combat.
      for (int i = 0; i < playerParty.length; ++i) {
        if (playerParty[i].getHP() > 0) {
          playerCombat(playerParty[i], opponentParty);
          outputLivingCombatants(playerParty, opponentParty);
        }
      }
      
      out("Your opponents' turn!");
      
      // Handle opponent retaliation.
      for (int i = 0; i < opponentParty.length; ++i) {
        if (opponentParty[i].getHP() > 0) {
          opponentCombat(opponentParty[i], playerParty);
          outputLivingCombatants(playerParty, opponentParty);
        }
      }
      
      out("Your turn!");
    }
  
    // Outputs the state of the battle.
    if (!isGroupAlive(playerParty)) {
      out("You lose the battle.");
    } else {
      out("You win the battle.");
    }
    
    input.close();
  }
}
