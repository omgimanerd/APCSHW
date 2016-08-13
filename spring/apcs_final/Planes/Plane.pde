import java.lang.*;

/**
 * Class encapsulating an enemy plane.
 */
public class Plane extends PhysicalObject {
 
  // The plane's internal float size will be different from the values used to render it.
  private static final float PLANE_COLLISION_SIZE = 10;
  private static final float BODY_LENGTH = 15;
  private static final float BODY_WIDTH = 3;
  private static final float BODY_HEIGHT = 3;
  private static final float WING_OFFSET = 2;
  private static final float WING_SPAN = 15;
  private static final float WING_WIDTH = 3;
  private static final float WING_HEIGHT = 1;
  private static final float TAIL_OFFSET = 5;
  private static final float TAIL_SPAN = 6;
  private static final float TAIL_WIDTH = 2;
  private static final float TAIL_HEIGHT = 1;
 
  private static final float PLANE_VELOCITY = 1.5;
  private static final float MIN_ROLL = -PI;
  private static final float MAX_ROLL = PI;
  private static final float MIN_PITCH = 10 * PI / 8;
  private static final float MAX_PITCH = 14 * PI / 8;
  private static final float YAW_RATE = 0.01;
  private static final float PITCH_RATE = 0.01;
 
  private static final float MINIMUM_TAKEOFF_HEIGHT = -2.5;
  private static final float DODGE_DISTANCE = 100;
  private static final int SHOT_COOLDOWN = 2000;
  private static final int GAME_START_SHOT_DELAY = 2500;
  private static final int MAX_HEALTH = 5;
 
  private Particle explosionGenerator_;
  private long lastShotTime_;
  
  public Plane(float x, float y, float z,
               float yaw, float pitch, float roll) {
    super(x, y, z,
          yaw, pitch, roll,
          0, 0, 0,
          0, 0, 0,
          PLANE_COLLISION_SIZE);
    this.health_ = MAX_HEALTH;
    this.setDestroyable();
    
    // Necessary because Processing doesn't allow static classes.
    this.explosionGenerator_ = new Particle(0, 0, 0);
    
    this.lastShotTime_ = System.currentTimeMillis() +
        GAME_START_SHOT_DELAY;
  }
  
  public void update(Environment environment) {
    if (this.isDead()) {
      this.shouldExist_ = false;
      environment.generateExplosion(this.position_);
    }
    
    PhysicalObject object = this.getCollidedObject(environment);
    if (this.position_.y >= -0.1 || object instanceof Box || object instanceof Plane) {
      this.damage(100);
    }
    
    // Explanation of AI: enemy planes will always try and face you to get a shot off,
    // if they are too close and a collision is imminent, then they will pitch up
    // and circle around again. Enemy planes are not affected by acceleration down since
    // we will assume the pilots are competent and won't crash their stuff.
    Player player = environment.getPlayer();
    float targetPitch = Util.getVerticalAngleBetween(this.getPosition(), player.getPosition());
    if (this.shouldDodge(player)) {
      this.setPitch(constrain(this.getPitch() - PITCH_RATE, MIN_PITCH, MAX_PITCH));
    } else {
      int sign = Util.getSign((targetPitch + 3 * HALF_PI) - this.getPitch());
      this.setPitch(constrain(this.getPitch() + (sign * PITCH_RATE), MIN_PITCH, MAX_PITCH));
    }
    
    float targetYaw = Util.normalizeRadians(
        Util.getLateralAngleBetween(this.getPosition(), player.getPosition()));
    if (!this.shouldDodge(player) || this.position_.y <= MINIMUM_TAKEOFF_HEIGHT) {
      int sign = Util.getSign(targetYaw - this.getYaw());
      this.setYaw(Util.normalizeRadians(this.getYaw() + (sign * YAW_RATE)));
    }
    
    if (this.lastShotTime_ + SHOT_COOLDOWN < System.currentTimeMillis()) {
      environment.addObject(new Bullet(this, this.position_, this.velocity_));
      this.lastShotTime_ = System.currentTimeMillis();
    }
    
    this.velocity_.x = sin(-this.getPitch()) * cos(this.getYaw());
    this.velocity_.y = cos(-this.getPitch());
    this.velocity_.z = sin(-this.getPitch()) * sin(this.getYaw());
    this.velocity_.setMag(PLANE_VELOCITY);
    
    this.position_.add(this.velocity_);
    this.position_.y = min(this.position_.y, 0);
  }
 
  public void render() {
    pushMatrix();
    translate(this.getX(), this.getY(), this.getZ());
    rotateX(this.getRoll());
    rotateY(-this.getYaw());
    rotateZ(this.getPitch());
    box(BODY_HEIGHT, BODY_LENGTH, BODY_WIDTH);
    translate(0, WING_OFFSET, 0);
    box(WING_HEIGHT, WING_WIDTH, WING_SPAN);
    translate(0, -(WING_OFFSET + TAIL_OFFSET), 0);
    box(TAIL_HEIGHT, TAIL_WIDTH, TAIL_SPAN);
    popMatrix();
  }
  
  public boolean shouldDodge(Player player) {
    return PVector.dist(this.getPosition(), player.getPosition()) < DODGE_DISTANCE;
  }
}
