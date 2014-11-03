import java.util.*;

public class Game {

  public static void pause (int ms) {
    try {
      Thread.sleep(ms);
    } catch (Exception e) {}
  }
  
  public static void flushDisplay () {
    for (int i = 0; i < 60; ++i) {
      System.out.print("\n");
    }
  }
  
  public static void out(String str) {
    for (int i = 0; i < str.length(); ++i) {
      System.out.print(str.charAt(i));
      pause(5);
    }
    System.out.print("\n");
  }

  public static void out(Adventurer adventurer) {
    out(adventurer.toString());
  }
  
  public static int[] userSelectStats() {
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);

    int[] stats = new int[3];
    out("Yo homie, how much STR should this dude have? 30 stat points available.");
    stats[0] = input.nextInt();
    while (stats[0] < 0 || stats[0] > 30) {
      out("Bro, dat ain't a valid STR.");
      stats[0] = input.nextInt();
    }
    if (stats[0] == 30) {
      out("Very well, that means this fine fellow will receive no DEX or INT.");
      out("\n");
      stats[1] = 0;
      stats[2] = 0;
      return stats;
    }
    
    out("Jolly good, now pray tell the exacting amount of DEX that this fine "
        + "Adventurer should possess. " + (30 - stats[0])
        + " points remaining.");
    stats[1] = input.nextInt();
    while (stats[1] < 0 || stats[1] > 30 - stats[0]) {
      out("Pardon me sir, but that is not a valid amount of DEX. "
          + (30 - stats[0]) + "points remaining");
      stats[1] = input.nextInt();
    }
    
    stats[2] = 30 - (stats[0] + stats[1]);
    out("Aight bro, then the dude's gonna get " + stats[2] + " INT.");
    pause(2000);

    return stats;
  }

  public static Adventurer[] userSelectPlayers(int amount) {
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);

    Adventurer[] players = new Adventurer[amount];

    for (int i = 0; i < amount; ++i) {
      out("Select a class:");
      out("a. Wizard.");
      out("b. Warrior.");
      out("c. Rogue.");
      out("d. Martial Artist.");

      // Allows the user to select a class.
      String classSelect = "";
      while (!classSelect.equalsIgnoreCase("a")
          && !classSelect.equalsIgnoreCase("b")
          && !classSelect.equalsIgnoreCase("c")
          && !classSelect.equalsIgnoreCase("d")) {
        classSelect = input.nextLine();
        if (!classSelect.equalsIgnoreCase("a")
            && !classSelect.equalsIgnoreCase("b")
            && !classSelect.equalsIgnoreCase("c")
            && !classSelect.equalsIgnoreCase("d")) {
          out("Invalid selection.");
        }
      }

      // Allows the user to name themselves.
      out("Name thyself.");
      String playerName = input.nextLine();
      switch (classSelect) {
        case "a":
          players[i] = new Wizard(playerName);
          out("Wizard " + playerName + " has joined your party.");
          break;
        case "b":
          players[i] = new Warrior(playerName);
          out("Warrior " + playerName + " has joined your party.");
          break;
        case "c":
          players[i] = new Rogue(playerName);
          out("Rogue " + playerName + " has joined your party.");
          break;
        case "d":
          players[i] = new MartialArtist(playerName);
          out("Martial Artist " + playerName + " has joined your party.");
      }
      
      // Takes in stats.
      int[] stats = userSelectStats();
      players[i].setSTR(stats[0]);
      players[i].setDEX(stats[1]);
      players[i].setINT(stats[2]);
    }

    return players;
  }

  public static Adventurer[] randomSelectOpponents(int amount) {
    Random rand = new Random();

    Adventurer[] opponents = new Adventurer[amount];

    for (int i = 0; i < amount; ++i) {
      // Randomly selects an opponent class.
      int opponentSelect = rand.nextInt(4);
      int opponentPowerStat = 20 + rand.nextInt(11);
      int opponentSecondaryPowerStat = 30 - opponentPowerStat;
      switch (opponentSelect) {
        case 0:
          opponents[i] = new Wizard("Xerath");
          opponents[i].setStats(opponentSecondaryPowerStat, 0, opponentPowerStat);
          out("You will combat the Wizard " + opponents[i] + ".");
          break;
        case 1:
          opponents[i] = new Warrior("Darius");
          opponents[i].setStats(opponentPowerStat, opponentSecondaryPowerStat, 0);
          out("You will combat the Warrior " + opponents[i] + ".");
          break;
        case 2:
          opponents[i] = new Rogue("Akali");
          opponents[i].setStats(opponentSecondaryPowerStat, opponentPowerStat, 0);
          out("You will combat the Rogue " + opponents[i] + ".");
          break;
        case 3:
          opponents[i] = new MartialArtist("Lee Sin");
          opponents[i].setStats(opponentPowerStat, opponentSecondaryPowerStat, 0);
          out("You will combat the Martial Artist " + opponents[i] + ".");
      }
    }

    return opponents;
  }

  public static void playerCombat(Adventurer player, Adventurer[] opponents) {
    if (!isGroupAlive(opponents)) {
      return;
    }

    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);

    String target = "";
    out("Who will " + player + " attack?");
    String selectionTitle = "abc";
    for (int i = 0; i < opponents.length; ++i) {
      out(selectionTitle.charAt(i) + ". "
          + opponents[i].getStats());
    }

    while (!target.equalsIgnoreCase("a") && !target.equalsIgnoreCase("b")
        && !target.equalsIgnoreCase("c")) {
      target = input.nextLine();
      if (selectionTitle.indexOf(target) != -1) {
        if (opponents[selectionTitle.indexOf(target)].getHP() <= 0) {
          out(opponents[selectionTitle.indexOf(target)].toString()
              + " is already dead.");
          out("Select another target.");
          target = "";
        }
      }
      if (!target.equalsIgnoreCase("a") && !target.equalsIgnoreCase("b")
          && !target.equalsIgnoreCase("c")) {
        out("Invalid target, select another target.");
      }
    }

    out("What shall you do?");
    out("a. Attack");
    out("b. Special Attack");
    out("c. Commit suicide");

    String action = "";
    while (!action.equalsIgnoreCase("a") && !action.equalsIgnoreCase("b")
        && !action.equalsIgnoreCase("c")) {
      action = input.nextLine();
      if (!action.equalsIgnoreCase("a") && !action.equalsIgnoreCase("b")
          && !action.equalsIgnoreCase("c")) {
        out("Invalid action.");
      }
    }
    switch (action) {
      case "a":
        out(player.attack(opponents[selectionTitle.indexOf(target)]));
        break;
      case "b":
        out(player.specialAttack(opponents[selectionTitle.indexOf(target)]));
        break;
      case "c":
        player.setHP(0);
    }
    
    pause(4000);
    flushDisplay();
  }

  public static void opponentCombat(Adventurer opponent, Adventurer[] players) {
    if (!isGroupAlive(players)) {
      return;
    }

    Random rand = new Random();
    int querySelection = rand.nextInt(players.length);

    while (players[querySelection].getHP() <= 0) {
      querySelection = rand.nextInt(players.length);
    }

    boolean action = rand.nextBoolean();
    if (action) {
      out(opponent.specialAttack(players[querySelection]));
    } else {
      out(opponent.attack(players[querySelection]));
    }
    
    pause(4000);
    flushDisplay();
  }

  public static void outputLivingCombatants(Adventurer[] players,
      Adventurer[] opponents) {
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
    out("");
  }

  public static boolean isGroupAlive(Adventurer[] group) {
    for (int i = 0; i < group.length; ++i) {
      if (group[i].getHP() > 0) {
        return true;
      }
    }
    return false;
  }

  @SuppressWarnings("resource")
  public static void main(String[] args) {
    Random rand = new Random();
    Scanner input = new Scanner(System.in);
    Adventurer[] playerParty = null;
    Adventurer[] opponentParty = null;

    flushDisplay();
    out("Welcome to Stuyablo, do you want to customize a party? [y/n]");
    String customize = input.nextLine();
    while (!customize.equalsIgnoreCase("y") && !customize.equalsIgnoreCase("n")) {
      out("Invalid selection.");
      customize = input.nextLine();
    }
    flushDisplay();
    
    if (customize.equalsIgnoreCase("y")) {
      out("How large is yer party? Maximum of 5.");
      int partySize = input.nextInt();
      while (partySize < 0 || partySize > 5) {
        out("Invalid party size. Maximum of 5.");
        partySize = input.nextInt();
      }
      playerParty = userSelectPlayers(partySize);
      flushDisplay();
      opponentParty = randomSelectOpponents(partySize);
    } else {
      out("Default party created.");
      playerParty = new Adventurer[3];
      playerParty[0] = new Wizard();
      playerParty[1] = new Warrior();
      playerParty[2] = new Rogue();
      opponentParty = randomSelectOpponents(3);
    }
    outputLivingCombatants(playerParty, opponentParty);
    pause(4000);
    
    // Determines whether the player or the opponent attacks first.
    boolean turn = rand.nextBoolean();
    if (turn) {
      out("Your opponents will attack first.");
      pause(2000);
      flushDisplay();
      for (int i = 0; i < opponentParty.length; ++i) {
        if (opponentParty[i].getHP() > 0) {
          outputLivingCombatants(playerParty, opponentParty);
          opponentCombat(opponentParty[i], playerParty);
        }
      }
      out("Your turn!");
      pause(2000);
    } else {
      out("You shall attack your opponents first.");
      pause(2000);
      flushDisplay();
    }
    
    boolean fighting = true;
    while (fighting) {
      // Determines the action that the user will undertake.
      while (isGroupAlive(playerParty) && isGroupAlive(opponentParty)) {
        // Handle player combat.
        for (int i = 0; i < playerParty.length; ++i) {
          if (playerParty[i].getHP() > 0) {
            outputLivingCombatants(playerParty, opponentParty);
            playerCombat(playerParty[i], opponentParty);
          }
        }

        if (!isGroupAlive(playerParty) || !isGroupAlive(opponentParty)) {
          break;
        }
        out("Your opponents' turn!");
        pause(2000);

        // Handle opponent retaliation.
        for (int i = 0; i < opponentParty.length; ++i) {
          if (opponentParty[i].getHP() > 0) {
            outputLivingCombatants(playerParty, opponentParty);
            opponentCombat(opponentParty[i], playerParty);
          }
        }
        if (!isGroupAlive(playerParty) || !isGroupAlive(opponentParty)) {
          break;
        }

        out("Your turn!");
        pause(2000);
      }

      // Outputs the state of the battle.
      if (!isGroupAlive(playerParty)) {
        out("Your party has died, you lose the battle.");
        fighting = false;
      } else {
        out("You win the battle. Ye wanna fite another buncha dudes? [y/n]");
        String fight = input.nextLine();
        while (!fight.equalsIgnoreCase("y") && !fight.equalsIgnoreCase("n")) {
          out("Invalid selection.");
          fight = input.nextLine();
        }
        
        if (fight.equalsIgnoreCase("y")) {
          out("Yer men have been heiled.");
          opponentParty = randomSelectOpponents(playerParty.length);
          for (int i = 0; i < playerParty.length; ++i) {
            playerParty[i].setHP(75);
            playerParty[i].setExpendableStat(40);
          }
        } else {
          out("Denks for pleying.");
          return;
        }
      }
    }
    
    input.close();
  }
}
