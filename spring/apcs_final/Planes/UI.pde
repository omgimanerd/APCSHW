/**
 * Class that handles the drawing of the HUD and UI.
 */
public class UI {
  
  private static final int CROSSHAIR_LENGTH = 15;
  private static final int CROSSHAIR_COLOR = #C0392B;
  
  private static final int LOCK_INDICATOR_STROKE_WEIGHT = 5;
  private static final int LOCK_INDICATOR_MIN_SIZE = 50;
  private static final int LOCK_INDICATOR_MAX_SIZE = 200;
  private static final int LOCK_INDICATOR_LOCKED_COLOR = #2ECC71;
  private static final int LOCK_INDICATOR_UNLOCKED_COLOR = #C0392B;
  
  private static final int IMMINENT_COLLISION_OFFSET = 100;
  private static final int IMMINENT_COLLISION_BOX_HEIGHT = 50;
  private static final int IMMINENT_COLLISION_BOX_WIDTH = 250;
  private static final int IMMINENT_COLLISION_TEXTSIZE = 20;
  private static final int IMMINENT_COLLISION_COLOR = #821C30;
  
  private static final int ATTITUDE_INDICATOR_STROKE_WEIGHT = 10;
  private static final int ATTITUDE_INDICATOR_RADIUS = 225;
  private static final float ATTITUDE_INDICATOR_ARC_SIZE = PI / 8;
  private static final int ATTITUDE_INDICATOR_COLOR = #F3ED45;
  
  private static final int ALTIMETER_STROKE_WEIGHT = 1;
  private static final int ALTIMETER_OFFSET = 75;
  private static final int ALTIMETER_TICK_LENGTH = 10;
  private static final int ALTIMETER_TICK_INCREMENT = 500;
  private static final int ALTIMETER_TICK_TEXTSIZE = 12;
  private static final int ALTIMETER_MIN = -2000;
  private static final int ALTIMETER_MAX = 2000;
  private static final int ALTIMETER_COLOR = #F4442E;
  
  private static final int AIRSPEED_METER_X = 1000;
  private static final int AIRSPEED_METER_Y = 750;
  private static final int AIRSPEED_METER_RADIUS = 250;
  private static final int AIRSPEED_METER_INDICATOR_LENGTH = 100;
  private static final int AIRSPEED_METER_LABEL_SIZE = 10;
  private static final int AIRSPEED_METER_LABEL_COLOR = #980000;
  private static final int AIRSPEED_METER_LABEL_RADIUS = 100;
  private static final int AIRSPEED_METER_LABEL_OFFSET = 15;
  private static final int AIRSPEED_METER_MAX = 400;
  private static final int AIRSPEED_METER_COLOR = #686868;
  private static final int AIRSPEED_METER_BORDER_WIDTH = 5;
  private static final int AIRSPEED_METER_BORDER_COLOR = #FF0000;
  
  private static final int HEALTH_TEXTSIZE = 15;
  private static final int HEALTHBAR_COLOR = #CC0000;
  
  private float cx_;
  private float cy_;
  
  public UI() {
    this.cx_ = width / 2;
    this.cy_ = height / 2;
  }
  
  public void resetDrawOptions() {
    fill(#000000);
    stroke(#000000);
    strokeWeight(1);
  }
  
  public void renderUI(Player player) {
    this.renderCrosshair();
    this.renderLockOn(player);
    this.renderImminentCollision(player);
    this.renderMeters(player);
    this.renderHealthBar(player);
  }
  
  public void renderCrosshair() {
    this.resetDrawOptions();
    noFill();  
    stroke(CROSSHAIR_COLOR);
    ellipse(this.cx_, this.cy_, CROSSHAIR_LENGTH, CROSSHAIR_LENGTH);
  }
  
  public void renderLockOn(Player player) {
    this.resetDrawOptions();
    float lockIndicatorSize = map(player.getLockTimer(),
                                  0, Player.MISSILE_LOCK_TIME,
                                  LOCK_INDICATOR_MIN_SIZE,
                                  LOCK_INDICATOR_MAX_SIZE);             
    strokeWeight(LOCK_INDICATOR_STROKE_WEIGHT);
    if (player.getLockTimer() == 0) {
      noFill();
      stroke(LOCK_INDICATOR_LOCKED_COLOR);
    } else {
      noFill();
      stroke(LOCK_INDICATOR_UNLOCKED_COLOR);
    }
    ellipse(this.cx_, this.cy_, lockIndicatorSize, lockIndicatorSize);
  }
  
  public void renderImminentCollision(Player player) {
    if (player.getImminentCollision()) {
      textSize(IMMINENT_COLLISION_TEXTSIZE);
      stroke(IMMINENT_COLLISION_COLOR);
      noFill();
      rect(this.cx_ - IMMINENT_COLLISION_BOX_WIDTH / 2,
           height - IMMINENT_COLLISION_OFFSET - IMMINENT_COLLISION_BOX_HEIGHT / 2,
           IMMINENT_COLLISION_BOX_WIDTH,
           IMMINENT_COLLISION_BOX_HEIGHT);
      fill(IMMINENT_COLLISION_COLOR);
      textAlign(CENTER, CENTER);
      text("IMMINENT COLLISION", this.cx_, height - IMMINENT_COLLISION_OFFSET);
    }
  } 
      
  
  public void renderMeters(Player player) {
    this.resetDrawOptions();
    PVector ypr = player.getOrientation();
    
    stroke(ATTITUDE_INDICATOR_COLOR);
    strokeWeight(ATTITUDE_INDICATOR_STROKE_WEIGHT);
    noFill();
    arc(this.cx_, this.cy_, ATTITUDE_INDICATOR_RADIUS, ATTITUDE_INDICATOR_RADIUS,
        player.getRoll() + PI / 2 - ATTITUDE_INDICATOR_ARC_SIZE,
        player.getRoll() + PI / 2 + ATTITUDE_INDICATOR_ARC_SIZE);
    
    stroke(ALTIMETER_COLOR);
    fill(ALTIMETER_COLOR);
    strokeWeight(ALTIMETER_STROKE_WEIGHT);
    textAlign(CENTER, CENTER);
    textSize(ALTIMETER_TICK_TEXTSIZE);
    line(ALTIMETER_OFFSET, ALTIMETER_OFFSET, ALTIMETER_OFFSET, height - ALTIMETER_OFFSET);
    
    float altimeterHeight = height - 2 * ALTIMETER_OFFSET;
    int altCounter = ALTIMETER_MIN; 
    for (float y = height - ALTIMETER_OFFSET; y >= ALTIMETER_OFFSET; y -= altimeterHeight / 8) {
      line(ALTIMETER_OFFSET - ALTIMETER_TICK_LENGTH, y,
           ALTIMETER_OFFSET + ALTIMETER_TICK_LENGTH, y);
      text(altCounter + " m", ALTIMETER_OFFSET - 4 * ALTIMETER_TICK_LENGTH, y);
      altCounter += ALTIMETER_TICK_INCREMENT;
    }
    float convertedAlt = map(constrain(-player.getY(), ALTIMETER_MIN, ALTIMETER_MAX),
        -2000, 2000, altimeterHeight, 0) + ALTIMETER_OFFSET;
    triangle(ALTIMETER_OFFSET + 2 * ALTIMETER_TICK_LENGTH, convertedAlt,
             ALTIMETER_OFFSET + 3 * ALTIMETER_TICK_LENGTH, convertedAlt - ALTIMETER_TICK_LENGTH,
             ALTIMETER_OFFSET + 3 * ALTIMETER_TICK_LENGTH, convertedAlt + ALTIMETER_TICK_LENGTH);
  
    fill(AIRSPEED_METER_COLOR);
    stroke(AIRSPEED_METER_BORDER_COLOR);
    strokeWeight(AIRSPEED_METER_BORDER_WIDTH);
    textAlign(CENTER, CENTER);
    textSize(AIRSPEED_METER_LABEL_SIZE);
    arc(AIRSPEED_METER_X, AIRSPEED_METER_Y, AIRSPEED_METER_RADIUS, AIRSPEED_METER_RADIUS,
        -PI, 0);
    fill(AIRSPEED_METER_LABEL_COLOR);
    textAlign(CENTER, TOP);
    for (int i = 0; i <= 400; i += 100) {
      text(i,
           AIRSPEED_METER_X + AIRSPEED_METER_LABEL_RADIUS * cos(map(i, 0, AIRSPEED_METER_MAX, -PI, 0)),
           AIRSPEED_METER_Y + AIRSPEED_METER_LABEL_RADIUS * sin(map(i, 0, AIRSPEED_METER_MAX, -PI, 0)) -
               AIRSPEED_METER_LABEL_OFFSET);
    }
    stroke(AIRSPEED_METER_BORDER_COLOR);
    float meterAngle = map(player.getVelocity().mag() * 100, 0, AIRSPEED_METER_MAX, -PI, 0);
    line(AIRSPEED_METER_X, AIRSPEED_METER_Y,
         AIRSPEED_METER_X + AIRSPEED_METER_INDICATOR_LENGTH * cos(meterAngle),
         AIRSPEED_METER_Y + AIRSPEED_METER_INDICATOR_LENGTH * sin(meterAngle));
  }
  
  // This method draws the player's health on the top left corner of the screen.
  public void renderHealthBar(Player player) {
    this.resetDrawOptions();
    stroke(HEALTHBAR_COLOR);
    fill(HEALTHBAR_COLOR);
    textAlign(LEFT, CENTER);
    textSize(HEALTH_TEXTSIZE);
    text("Health", 150, 80);
    rect(150, 100, player.getHealth() * 10, 10);
  }
}

