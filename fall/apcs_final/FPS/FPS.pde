/**
 * @author Alvin Lin (alvin.lin@stuypulse.com)
 * @author Elias Saric (esaric)
 * This fps has standard WASD controls with click to shoot.
 * The enemies are floating mines that track you, if they touch you,
 * you will take damage. You can destroy them by shooting.
 */

import ddf.minim.*;

Minim minim;
Sound sound;
Player player;
Environment environment;
UI ui;
Menu menu;

final int STATE_GAME = 0;
final int STATE_MENU = 1;
final int STATE_SETTINGS = 2;
final int STATE_PAUSED = 3;
final int STATE_LOST = 4; 

int current_state = STATE_MENU;

void setup () {
  size (1200, 800, P3D);
  frameRate(200);
  
  // Load sound files
  minim = new Minim(this);
  sound.loadSounds(minim);
  
  // Instantiate necessary game objects.
  player = new Player();
  environment = new Environment();
  environment.addPhysicalObject(player);
  environment.buildMap();
  ui = new UI();
  menu = new Menu(player, environment);
}

void draw () {
  
  // In STATE_GAME mode all the actual fps related functions are run.
  // Otherwise, the appropriate menu is drawn.
  switch (current_state) {
    case STATE_GAME:
      Sound.pause("theme.mp3");
      noCursor();
      fill(101);
      background(#000000);
      
      environment.updateObjects();
      environment.updateExplosions();
      ui.updateUI(player);
      player.updateMovement();
      player.updateLook(this.getX(), this.getY());
      if (player.isDead()) {
        current_state = STATE_LOST;
      }
      break;
    case STATE_MENU:
      Sound.playNonOverlap("theme.mp3");
      hint(DISABLE_DEPTH_TEST);
      camera();
      noLights();
      menu.drawMenuScreen();
      hint(ENABLE_DEPTH_TEST);
      break;
    case STATE_SETTINGS:
      hint(DISABLE_DEPTH_TEST);
      camera();
      noLights();
      menu.drawSettingsScreen();
      hint(ENABLE_DEPTH_TEST);
      break;
    case STATE_PAUSED:
      hint(DISABLE_DEPTH_TEST);
      camera();
      noLights();
      menu.drawPauseScreen();
      hint(ENABLE_DEPTH_TEST);
      break;
    case STATE_LOST:
      Sound.playNonOverlap("theme.mp3");
      hint(DISABLE_DEPTH_TEST);
      camera();
      noLights();
      menu.drawLostScreen();
      hint(ENABLE_DEPTH_TEST);
      break;
  }
}

// Event Handlers
void keyPressed() {
  // Change game states as necessary.
  current_state = menu.updateOnKeyPressed(key, current_state);
  
  // STATE_GAME update separate from menu when game is running.
  if (current_state == STATE_GAME) {
    player.updateOnKeyPressed(key);
  }
}

void keyReleased() {
  // STATE_GAME update separate from menu when game is running.
  if (current_state == STATE_GAME) {
    player.updateOnKeyReleased(key);
  }
}

void mousePressed() {
  // STATE_GAME update separate from menu when game is running.
  if (current_state == STATE_GAME) {
    player.updateOnMousePressed(environment);
  }
}

