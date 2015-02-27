/**
 * @author Elias Saric (esaric)
 * This class encapsulates all the methods to render the menu
 * correctly.
 */

public class Menu {
  
  private final int STATE_GAME = 0;
  private final int STATE_MENU = 1;
  private final int STATE_SETTINGS = 2;
  private final int STATE_PAUSE = 3;
  private final int STATE_LOST = 4;
  
  private Player player_;
  private Environment environment_;
  
  public Menu(Player player, Environment environment) {
    this.player_ = player;
    this.environment_ = environment;
  }
  
  // Checks to see whether the p key has been pressed or not
  // If the p key has been pressed then it swaps thstatee gamemode from menu to 3d render and vice versa
  public int updateOnKeyPressed(char eventKey, int current_state) {
    switch (current_state) {
      case STATE_GAME:
        switch (eventKey) {
          case 'p':
            return STATE_PAUSED;
          default:
            return current_state;
        }
      case STATE_MENU:
        switch (eventKey) {
          case 'p':
            this.player_.reset();
            this.environment_.reset();
            return STATE_GAME;
          case 's':
            return STATE_SETTINGS;
          default:
            return current_state;
        }
      case STATE_SETTINGS:
        switch (eventKey) {
          case 'i':
            this.player_.invertMouse();
            return STATE_SETTINGS;
          case 'p':
            return STATE_GAME;
          case 'm':
            this.player_.reset();
            this.environment_.reset();
            return STATE_MENU;
        }
      case STATE_PAUSE:
        switch (eventKey) {
          case 'p':
            return STATE_GAME;
          case 'm':
            this.player_.reset();
            this.environment_.reset();
            return STATE_MENU;
          case 's':
            return STATE_SETTINGS;
          default:
            return current_state;
        }
      case STATE_LOST:
        switch (eventKey) {
          case 'm':
            this.player_.reset();
            this.environment_.reset();
            return STATE_MENU;
          default:
            return current_state;
        }
    }
    return current_state;
  }
  
  public void drawMenuScreen() {
    cursor();
    background(loadImage("menuscreen.jpg"));
    textSize(42);
    fill(#FF0000);
    text("Press 'p' to play", 50, 100);
    text("Press 's' for settings", 50, 150);
    text("Press 'Esc' to quit", 800, 100);
    text("To play, use 'wasd' to move, click to shoot,", 50, 650);
    text("and 'p' to pause the game", 50, 700);
  }
  
  public void drawSettingsScreen() {
    cursor();
    background(loadImage("settingsscreen.jpg"));
    textSize(42);
    fill(#FF0000);
    text("Settings", 500, 50);
    textSize(32);
    if (this.player_.getInvertMouse()) {
      text("Mouse invert: true", 50, 100);
    } else {
      text("Mouse invert: false", 50, 100);
    }
    text("Press 'i' to invert mouse", 50, 200);
    text("Press 'p' to resume play", 750, 100);
    text("Press 'm' to quit to the main menu", 750, 200); 
  }
  
  public void drawPauseScreen() {
    cursor();
    background(loadImage("pausescreen.jpg"));
    textSize(42);
    fill(#FF0000);
    text("Press 'p' to unpause", 100, 200);
    text("Press 'm' to quit to the main menu", 100, 600);
    text("Press 's' to change settings", 100, 700);
  }
  
  public void drawLostScreen() {
    cursor();
    background(loadImage("lostscreen.jpg"));
    textSize(48);
    fill(#FF0000);
    text("You were killed by dem bloody mines!", 50, 100);
    text("Mines destroyed: " + this.player_.getScore(), 50, 150);
    text("Press 'm' to return to the main menu", 50, 250);
  }
}
