/**
 * Class encapsulating a fired missile.
 */
public class Missile extends PhysicalObject {
  
  private static final float BODY_LENGTH = 1;
  private static final float BODY_WIDTH = 4;
  private static final float BODY_HEIGHT = 1;
  private static final int BODY_COLOR = #CD533B;
  private static final int MISSILE_DAMAGE = 5;
  
  private static final float MAX_TRAVEL_DISTANCE = 800;
  private static final float MISSILE_VELOCITY = 4;
  private static final float MIN_ROLL = -PI;
  private static final float MAX_ROLL = PI;
  private static final float MIN_PITCH = 9 * PI / 8;
  private static final float MAX_PITCH = 15 * PI / 8;
  private static final float YAW_RATE = 0.05;
  private static final float PITCH_RATE = 0.05;
  
  private PhysicalObject shooter_;
  private PhysicalObject target_;
  private PVector lastPosition_;
  private float distanceTraveled_;
  private Particle explosionGenerator_;
  
  public Missile(PhysicalObject shooter,
                 PVector origin, PVector orientation, PhysicalObject target) {
    this(shooter,
         origin.x, origin.y, origin.z,
         orientation.x, orientation.y, orientation.z,
         target);
  }
  
  public Missile(PhysicalObject shooter,
                 float x, float y, float z,
                 float yaw, float pitch, float roll,
                 PhysicalObject target) {
    super(x, y, z,
          yaw, pitch, roll,
          0, 0, 0,
          0, 0, 0,
          BODY_LENGTH, BODY_WIDTH, BODY_HEIGHT);
    this.shooter_ = shooter;
    this.target_ = target;
    this.lastPosition_ = new PVector();
    this.distanceTraveled_ = 0;
    // This is necessary because Processing doesn't allow static methods.
    this.explosionGenerator_ = new Particle(0.0, 0.0, 0.0);
  }
  
  public boolean hasCollidedWith(PhysicalObject object) {
    return (object != this.shooter_ && object.isSolid() &&
        Util.getEuclideanDistance2(this.getX(), this.getY(), this.getZ(),
            object.getX(), object.getY(), object.getZ()) <
            sq(this.getCollisionRadius() + object.getCollisionRadius()));
  }
  
  public void update(Environment environment) {
    if (this.target_ != null) {
      // Missiles follow practically the same AI as a plane, except without the collision
      // avoidance.
      float targetPitch = Util.normalizeRadians2(HALF_PI -
          Util.getVerticalAngleBetween(this.target_.getPosition(), this.getPosition()));
      int sign = Util.getSign(targetPitch - this.getPitch());
      this.setPitch(this.getPitch() + (sign * PITCH_RATE));
      
      float targetYaw = Util.normalizeRadians(PI +
          Util.getLateralAngleBetween(this.getPosition(), this.target_.getPosition()));
      sign = Util.getSign(targetYaw - this.getYaw());
      this.setYaw(Util.normalizeRadians(this.getYaw() + (sign * YAW_RATE)));
    }
    
    this.velocity_.x = sin(this.getPitch()) * cos(this.getYaw());
    this.velocity_.y = cos(this.getPitch());
    this.velocity_.z = sin(this.getPitch()) * sin(this.getYaw());
    this.velocity_.setMag(-MISSILE_VELOCITY);
    
    this.lastPosition_.set(this.position_);
    this.position_.add(this.velocity_);
    this.distanceTraveled_ += this.velocity_.mag();
    
    if (this.distanceTraveled_ > MAX_TRAVEL_DISTANCE || this.position_.y >= 0) {
      this.shouldExist_ = false;
      environment.generateExplosion(this.position_);
    }
    
    PhysicalObject object = getCollidedObject(environment);
    if (object != null) {
      object.damage(MISSILE_DAMAGE);
      this.shouldExist_ = false;
      environment.generateExplosion(this.position_);
    }
  }
  
  public void render() {
    pushMatrix();
    translate(this.getX(), this.getY(), this.getZ());
    rotateX(this.getRoll());
    rotateY(-this.getYaw());
    rotateZ(-this.getPitch());
    fill(BODY_COLOR);
    box(BODY_LENGTH, BODY_WIDTH, BODY_HEIGHT);
    popMatrix();
  }    
}
