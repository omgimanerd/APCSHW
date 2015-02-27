/**
 * @author Alvin Lin (alvin.lin@stuypulse.com)
 * This class encapsulates a bullet in the physical world.
 * The physics, rendering, and collision are taken care of in
 * this class.
 */
public class Bullet extends PhysicalObject {

  private static final float BULLET_SPEED = 300.0;
  private static final float BULLET_SIZE = 1.0;
  private static final float MAX_RANGE = 5000;
  private static final int BULLET_COLOR = #FF0000;

  private PhysicalObject firedBy_;
  private float distanceTraveled_;

  public Bullet(float x, float y, float z, 
                float vx, float vy, float vz,
                PhysicalObject firedBy) {
    super(x, y, z, 
          Bullet.BULLET_SIZE * 2,
          vx, vy, vz, 
          0.0, 0.0, 0.0);
    this.distanceTraveled_ = 0.0;
    this.setUnsolid();
    this.firedBy_ = firedBy;
  }

  public void updatePhysics(Environment environment) {
    this.velocity_.add(this.acceleration_);
    
    PVector checkDistance = this.velocity_.get();
    checkDistance.setMag(TrackerMine.MINE_RADIUS);
    int numChecks = (int) (this.velocity_.mag() / TrackerMine.MINE_RADIUS);
    
    for (int i = 0; i < numChecks + 1; ++i) {
      this.position_[0] += checkDistance.x;
      this.position_[1] += checkDistance.y;
      this.position_[2] += checkDistance.z;
      this.distanceTraveled_ += checkDistance.mag();
      
      // Once the bullet reaches its max range, it will disappear.
      if (this.distanceTraveled_ >= Bullet.MAX_RANGE) {
        environment.removePhysicalObject((PhysicalObject) this);
        return;
      }
        
      ArrayList<PhysicalObject> collidedObjects = this.getCollidedObjects(environment);
      // Upon firing, the bullet will always collide with the object that fired it, so
      // we need to check many other conditions.
      for (int j = 0; j < collidedObjects.size(); ++j) {
        PhysicalObject collidedObject = collidedObjects.get(j);
        
        // The bullet will disappear upon collision.
        if (collidedObject instanceof Box) {
          environment.removePhysicalObject((PhysicalObject) this);
          return;
        }
        
        // If the bullet was not fired by the object it hit, then it will do damage.
        if (this.firedBy_ != collidedObject) {
          environment.removePhysicalObject((PhysicalObject) this);
          collidedObject.damage(10);
          return;
        }
      }
    }
  }

  public void redrawOnScreen() {
    pushMatrix();
    translate(this.getX(), this.getY(), this.getZ());
    fill(Bullet.BULLET_COLOR);
    stroke(Bullet.BULLET_COLOR);
    sphere(Bullet.BULLET_SIZE);
    popMatrix();
  }
  
  public PhysicalObject getShooter() {
    return this.firedBy_;
  }
}

