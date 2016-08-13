/**
 * Class encapsulating a bullet.
 * @author Alvin Lin (alvin.lin@stuypulse.com)
 */
public class Bullet extends PhysicalObject {


  private static final float BULLET_SIZE = .5;
  private static final int DEFAULT_TRAVEL_VELOCITY = 12;
  private static final int MAX_TRAVEL_DISTANCE = 1000;
  private static final int BULLET_COLOR = #000000;
  
  private PhysicalObject shooter_;
  private PVector lastPosition_;
  private float distanceTraveled_;
  
  public Bullet(PhysicalObject shooter,
                PVector origin, PVector velocity) {
    this(shooter,
         origin.x, origin.y, origin.z,
         velocity.x, velocity.y, velocity.z);
    this.velocity_.setMag(DEFAULT_TRAVEL_VELOCITY);
  }
  
  public Bullet(PhysicalObject shooter,
                float x, float y, float z,
                float vx, float vy, float vz) {
    super(x, y, z,
          0, 0, 0,
          vx, vy, vz,
          0, 0, 0,
          BULLET_SIZE);
    this.shooter_ = shooter;
    this.lastPosition_ = new PVector();
    this.distanceTraveled_ = 0;
  }
  
  public boolean hasCollidedWith(PhysicalObject object) {
    return object != this.shooter_ && object.isSolid() &&
        Util.lineSphereIntersection(this.lastPosition_,
                                    this.position_,
                                    object.getPosition(),
                                    object.getCollisionRadius()) &&
        PVector.dist(this.getPosition(), object.getPosition()) <
        DEFAULT_TRAVEL_VELOCITY * 2;
  }
  
  public void update(Environment environment) {
    this.lastPosition_.set(this.position_);
    this.position_.add(this.velocity_);
    this.distanceTraveled_ += this.velocity_.mag();
    if (this.distanceTraveled_ > MAX_TRAVEL_DISTANCE || this.position_.y >= 0) {
      this.shouldExist_ = false;
    }
   
    PhysicalObject object = this.getCollidedObject(environment);
    if (object != null) {
      if (object instanceof Player) {
        Sound.play("bullet_ricochet.mp3"); 
      }
      object.damage(1);
      this.shouldExist_ = false;
    }  
  }
  
  public void render() {
    pushMatrix();
    translate(this.getX(), this.getY(), this.getZ());
    stroke(BULLET_COLOR);
    fill(BULLET_COLOR);
    sphere(this.getWidth());
    popMatrix();
  }
}

