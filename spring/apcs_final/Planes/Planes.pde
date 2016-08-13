import damkjer.ocd.*;
import ddf.minim.*;

Minim minim;
Sound sound;
Camera camera;
Player player;
UI ui;
Environment environment;

void setup() {
  size(1200, 800, OPENGL);
  frameRate(200);
  perspective(PI / 3.0, float(width) / float(height),
              1, 10000);
  // ************** SETS DEBUG MODE ON AND OFF ******************
  Util.DEBUG_MODE = false;
   
  //load sound files
  minim = new Minim(this);
  sound.loadSounds(minim); 
  camera = new Camera(this, 0.05, 10000);
  player = (Player) new Player(camera).setDestroyable();
  ui = new UI();
  environment = new Environment();
  environment.addPlayer(player);
  
  environment.addObject(new Plane(20, -50, 100, PI / 9, 11 * PI / 8, 0));
  environment.addObject(new Plane(0, -500, 100, PI / 9, 11 * PI / 8, 0));
  environment.addObject(new Plane(1000, -50, 400, PI / 9, 11 * PI / 8, 0));
  environment.addObject(new Plane(200, -500, -1900, PI / 9, 11 * PI / 8, 0));
}

void draw() {
  if (!player.isDead()) {
    environment.update();
    
    pushMatrix();
    player.render();
    environment.render();
    popMatrix();
    
    // 2D UI draw code goes here
    hint(DISABLE_DEPTH_TEST);
    ui.renderUI(player);
    hint(ENABLE_DEPTH_TEST);
  } else {
    background(0);
    textSize(30);
    textAlign(CENTER, CENTER);
    text("You died. Press space to respawn.", width / 2, height / 2);
    Sound.stop("engine_flying.mp3");
    if (Input.SPACE) {
      player = (Player) new Player(camera).setDestroyable();
      environment.addPlayer(player);
    }
  }
}

void keyPressed() {
  Input.onKeyPressed(keyCode);
}

void keyReleased() {
  Input.onKeyReleased(keyCode);
}

