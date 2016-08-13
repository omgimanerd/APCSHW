import damkjer.ocd.*;
import java.lang.*;

/**
 * Class encapsulating the player.
 */
public class Player extends PhysicalObject {
  
  private static final int DEFAULT_PLAYER_SIZE = 10;
  private static final int MAX_HEALTH = 20;
  private static final float THRUST_RATE = 0.025;
  private static final float MAX_VELOCITY = 3.5;
  private static final float MINIMUM_TAKEOFF_HEIGHT = -2.5;
  private static final float YAW_HANDLING = 0.075;
  private static final float PITCH_HANDLING = 0.00005;
  private static final float ROLL_HANDLING = 0.00001;
  
  private static final float MISSILE_START_OFFSET = 3;
  private static final float MISSILE_LOCK_THRESHOLD = 50;
  
  // MISSILE_LOCK_TIME is a tick counter, and does not
  // correlate with milliseconds.
  public static final int MISSILE_LOCK_TIME = 75;
  private static final long SHOT_COOLDOWN = 100;
  private static final long MISSILE_COOLDOWN = 4000;
  
  private static final float IMMINENT_COLLISION_DISTANCE = 150;
  
  private Camera camera_;
  private float thrustMagnitude_;
  private PVector lookPosition_;
  private int missileLockTimer_;
  private long lastShotTime_;
  private long lastMissileShotTime_;
  private PhysicalObject potentialLockTarget_;
  private PhysicalObject lockTarget_;
  private boolean imminentCollision_ = false;
  private Particle explosionGenerator_;
 
  public Player(Camera camera) {
    super(0, 0, 2000,
          0, PI / 2, 0,
          0, 0, 0,
          0, PhysicalObject.DEFAULT_AY, 0,
          DEFAULT_PLAYER_SIZE);
    
    this.health_ = MAX_HEALTH;
          
    this.camera_ = camera;
    this.lookPosition_ = new PVector();
    this.missileLockTimer_ = MISSILE_LOCK_TIME;
    this.lastShotTime_ = 0;
    this.lastMissileShotTime_ = 0;
    this.potentialLockTarget_ = null;
    this.lockTarget_ = null;
    this.imminentCollision_ = false;
    this.explosionGenerator_ = new Particle(0, 0, 0);
  }
 
  public void update(Environment environment) {
    // Updates player movement.
    if (Util.DEBUG_MODE) {
      if (Input.W) {
        this.position_.add(this.lookPosition_);
      } else if (Input.S) {
        this.position_.sub(this.lookPosition_);
      } else if (Input.D) {
        this.position_.x += sin(PI - this.getYaw());
        this.position_.z += cos(PI - this.getYaw());
      } else if (Input.A) {
        this.position_.x += sin(0 - this.getYaw());
        this.position_.z += cos(0 - this.getYaw());
      }
      if (Input.SPACE) {
        this.position_.y--;
      } else if (Input.C) {
        this.position_.y++;
      }
    } else {
      if (Input.W && !Input.S) {
        this.thrustMagnitude_ = min(this.thrustMagnitude_ + THRUST_RATE, MAX_VELOCITY);
      } else if (Input.S && !Input.W) {
        this.thrustMagnitude_ = max(this.thrustMagnitude_ - THRUST_RATE, 0);
      }
      
      this.lookPosition_.setMag(this.thrustMagnitude_);
      this.lookPosition_.add(this.acceleration_);
      this.velocity_.set(this.lookPosition_);
      this.position_.add(this.velocity_);
    }
    
    // Handles plane on the ground.
    if (this.position_.y >= 0) {
      this.position_.y = 0;
      this.setRoll(0);
    }
    
    if (this.isDead()) {
      this.shouldExist_ = false;
    }
    
    // Handles crashing
    PhysicalObject collidedObject = this.getCollidedObject(environment);
    if (collidedObject instanceof Box || collidedObject instanceof Plane) {
      this.damage(100);
      environment.generateExplosion(this.position_);
    }
    
    // Handles shooting normal bullets
    if (mousePressed && System.currentTimeMillis() >
                        this.lastShotTime_ + SHOT_COOLDOWN) {
      environment.addObject(new Bullet(this, this.position_, this.lookPosition_));
      this.lastShotTime_ = System.currentTimeMillis();
      Sound.play("bullet.mp3");
    }
    
    // Tracks an object for missiles and collision detector.
    this.imminentCollision_ = false;
    ArrayList<PhysicalObject> environmentObjects = environment.getObjects();
    for (PhysicalObject object : environmentObjects) {
      if (object instanceof Plane &&
          PVector.dist(this.getPosition(), object.getPosition()) < IMMINENT_COLLISION_DISTANCE) {
        this.imminentCollision_ = true;
      }
      if (object instanceof Plane &&
          Util.getLinePointPerpendicularDistance(
              this.getPosition(),
              PVector.add(this.getPosition(), this.lookPosition_),
              object.getPosition()) < MISSILE_LOCK_THRESHOLD) {
         this.potentialLockTarget_ = object;
         if (this.missileLockTimer_ == 0) {
           this.lockTarget_ = object;
           Sound.playNonOverlap("missile_locked.mp3");
         } else {
           this.missileLockTimer_ = max(this.missileLockTimer_ - 1, 0);
           Sound.playNonOverlap("missile_locking.mp3");
         }
         break;
      }
    }
    if (this.imminentCollision_) {
      Sound.playNonOverlap("warning.mp3");
    } else {
      Sound.stop("warning.mp3");
    }
    if (this.potentialLockTarget_ == null) {
      this.missileLockTimer_ = MISSILE_LOCK_TIME;
      this.lockTarget_ = null;
    }
    this.potentialLockTarget_ = null;
    
    if (Input.X && System.currentTimeMillis() >
                   this.lastMissileShotTime_ + MISSILE_COOLDOWN) {
      PVector tmpOrientation = new PVector();
      tmpOrientation.set(this.orientation_);
      tmpOrientation.y = (PI - tmpOrientation.y);
      tmpOrientation.x = -(PI - tmpOrientation.x);
      PVector tmpPosition = new PVector();
      tmpPosition.set(this.position_);
      tmpPosition.y += MISSILE_START_OFFSET;
      environment.addObject(new Missile(this, tmpPosition, tmpOrientation, this.lockTarget_));
      this.lastMissileShotTime_ = System.currentTimeMillis();
      Sound.playNonOverlap("missile_launch.mp3");
    }
    
    // Mouse looking update logic
    float mouseXDeviation = -(mouseX - width / 2);
    float mouseYDeviation = -(mouseY - height / 2);
    
    if (this.position_.y < MINIMUM_TAKEOFF_HEIGHT) {
      this.setRoll(Util.normalizeRadians(this.getRoll() + (mouseXDeviation * ROLL_HANDLING)));
      
      float tmpRoll = -this.getRoll();
      if (this.getRoll() > PI) {
        tmpRoll = -(this.getRoll() - TWO_PI);
      }
      this.setYaw(Util.normalizeRadians(this.getYaw() + (tmpRoll * YAW_HANDLING)));
    }
    this.setPitch(Util.normalizeRadians(this.getPitch() + (mouseYDeviation * PITCH_HANDLING)));
    
    if (this.thrustMagnitude_ != 0) {
      Sound.playNonOverlap("engine_flying.mp3");
    } else {
      Sound.stop("engine_flying.mp3");
    }
    
    this.lookPosition_.x = sin(this.getPitch()) * cos(this.getYaw());
    this.lookPosition_.y = cos(this.getPitch());
    this.lookPosition_.z = sin(this.getPitch()) * sin(this.getYaw());
  }
 
  // *** BIG NOTE ***
  // THE RENDER CLASS MOVES THE CAMERA AND GOES INSIDE THE Planes.pde MATRIX STACK
  public void render() {
    // Camera looking
    this.camera_.jump(this.getX(), this.getY(), this.getZ());
    this.camera_.aim(this.getX() + this.lookPosition_.x,
                     this.getY() + this.lookPosition_.y,
                     this.getZ() + this.lookPosition_.z);
    
    // The roll function rolls the camera by a given amount and cannot set it to a
    // roll position, so we must save the roll position and roll by the difference.
    float currentRoll = Util.normalizeRadians(-this.camera_.attitude()[2]);
    float rollAmount = (currentRoll - this.getRoll());
    this.camera_.roll(rollAmount);
    this.camera_.feed();
  }
  
  public boolean getImminentCollision() {
    return this.imminentCollision_;
  }
  
  public PhysicalObject getLockedTarget() {
    return this.lockTarget_;
  }
  
  public int getLockTimer() {
    return this.missileLockTimer_;
  }
}
