import java.util.*;

public class Game {

  // This method pauses the terminal for given number of milliseconds.
  public static void pause(int ms) {
    try {
      Thread.sleep(ms);
    } catch (Exception e) {
    }
  }

  // This method outputs 60 newlines to 'clear' the terminal display.
  public static void flushDisplay() {
    for (int i = 0; i < 60; ++i) {
      System.out.print("\n");
    }
  }

  // This method outputs a given string in a scrolling format by outputting
  // each character in the string one at a time with a 5 millisecond delay
  // in between each character.
  public static void out(String str) {
    for (int i = 0; i < str.length(); ++i) {
      System.out.print(str.charAt(i));
      pause(5);
    }
    System.out.print("\n");
  }

  // This method does the same thing as out, but overloads it so that it
  // can be used for Adventurers.
  public static void out(Adventurer adventurer) {
    out(adventurer.toString());
  }

  // This method returns an array containing stats the user has been
  // prompted to provide.
  public static int[] userSelectStats() {
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);

    int[] stats = new int[3];
    out("Yo homie, how much STR should this dude have? 30 stat points available.");
    stats[0] = input.nextInt();
    while (stats[0] < 0 || stats[0] > 30) {
      out("Dat ain't a valid STR, bro");
      stats[0] = input.nextInt();
    }

    if (stats[0] == 30) {
      out("Very well, that means this fine fellow will receive no DEX or INT.");
      stats[1] = 0;
      stats[2] = 0;
      pause(2000);
      return stats;
    }

    out("Jolly good, now pray tell the exacting amount of DEX that this fine "
        + "Adventurer should possess. " + (30 - stats[0])
        + " points remaining.");
    stats[1] = input.nextInt();
    while (stats[1] < 0 || stats[1] > 30 - stats[0]) {
      out("Pardon me sir, but that is not a valid amount of DEX. "
          + (30 - stats[0]) + " points remaining");
      stats[1] = input.nextInt();
    }

    stats[2] = 30 - (stats[0] + stats[1]);
    out("Aight bro, then the dude's gonna get " + stats[2] + " INT.");
    pause(2000);

    return stats;
  }

  // Given a number, this method will return an array of Adventurers of the
  // given length with stats that the user has selected.
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
        classSelect = classSelect.toLowerCase();
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

  // Given a number, this method will return an array of Adventurers of that
  // length with appropriately randomized stats.
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
          opponents[i].setStats(opponentSecondaryPowerStat, 0,
              opponentPowerStat);
          out("You will combat the Wizard " + opponents[i] + ".");
          break;
        case 1:
          opponents[i] = new Warrior("Darius");
          opponents[i].setStats(opponentPowerStat, opponentSecondaryPowerStat,
              0);
          out("You will combat the Warrior " + opponents[i] + ".");
          break;
        case 2:
          opponents[i] = new Rogue("Akali");
          opponents[i].setStats(opponentSecondaryPowerStat, opponentPowerStat,
              0);
          out("You will combat the Rogue " + opponents[i] + ".");
          break;
        case 3:
          opponents[i] = new MartialArtist("Lee Sin");
          opponents[i].setStats(opponentPowerStat, opponentSecondaryPowerStat,
              0);
          out("You will combat the Martial Artist " + opponents[i] + ".");
      }
    }

    return opponents;
  }

  // Given an attacking player and an array of opponents for him to attack,
  // this method will prompt the player for input to determine which opponent
  // to attack and what action to undertake when attacking.
  public static void playerCombat(Adventurer player, Adventurer[] opponents) {
    if (!isGroupAlive(opponents)) {
      return;
    }

    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);

    String target = "";
    out("Who will " + player + " attack?");
    String selectionTitle = "abcde";
    for (int i = 0; i < opponents.length; ++i) {
      out(selectionTitle.charAt(i) + ". " + opponents[i].getStats());
    }

    while (target.equals("") || selectionTitle.indexOf(target) == -1) {
      target = input.nextLine();
      target = target.toLowerCase();
      if (target.equals("") || selectionTitle.indexOf(target) == -1
          || selectionTitle.indexOf(target) > opponents.length - 1) {
        out("Invalid target, select another target.");
        target = "";
      } else if (opponents[selectionTitle.indexOf(target)].getHP() <= 0) {
        out(opponents[selectionTitle.indexOf(target)].toString()
            + " is already dead.");
        out("Select another target.");
        target = "";
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
      action = action.toLowerCase();
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
        out(player + " committed suicide.");
        pause(2000);
        flushDisplay();
        return;
    }

    pause(4000);
    flushDisplay();
  }

  // Given the opponent who will attack and the array of players for him to
  // attack, this method will select a random player for the opponent to attack
  // and an action for the opponent to undertake when attacking. This method
  // will output the state of the skirmish to the screen.
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

  // Outputs only the living players and opponents to the screen.
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

  // Returns true if one or more of the Adventurers in a given array are alive.
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

    // Allows the user to customize a party.
    if (customize.equalsIgnoreCase("y")) {
      out("How large is yer party? Maximum of 5.");
      int partySize = input.nextInt();
      while (partySize <= 0 || partySize > 5) {
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
    pause(3000);

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
      pause(1000);
    } else {
      out("You shall attack your opponents first.");
      pause(1000);
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
        pause(1000);

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
        pause(1000);
      }

      // Outputs the state of the battle and allows for replay.
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
            playerParty[i].setExpendableStat(75);
          }
          pause(2000);
        } else {
          out("Denks for pleying.");
          return;
        }
      }
    }

    input.close();
  }
}
