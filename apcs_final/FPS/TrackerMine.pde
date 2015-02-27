/**
 * @author Alvin Lin (alvin.lin@stuypulse.com)
 * This class encapsulates a tracker mine. It contains all the methods
 * needed to handle the physics and rendering of the mine as well as its
 * 'tracking', which is simply to move towards to player at all times.
 */

public class TrackerMine extends PhysicalObject {
  
  private static final float MINE_RADIUS = 10;
  private static final float MAX_HEALTH = 30;
  private static final float MOVE_VELOCITY = 12;
  private static final int MINE_COLOR = #FF0000;
  private static final int MINE_STROKE_COLOR = #000000;
  
  private float health_;
  
  public TrackerMine(float position[]) {
    this(position[0], position[1], position[2]);
  }
  
  public TrackerMine(float x, float y, float z) {
    super(x, y, z,
          2 * TrackerMine.MINE_RADIUS,
          0.0, 0.0, 0.0,
          0.0, 0.0, 0.0);
    this.health_ = TrackerMine.MAX_HEALTH;
  }
  
  public void updatePhysics(Environment environment) {
    ArrayList<PhysicalObject> collidedObjects = this.getCollidedObjects(environment);
    if (collidedObjects.size() > 0) {
      for (int i = 0; i < collidedObjects.size(); ++i) {
        PhysicalObject collidedObject = collidedObjects.get(i);
        
        if (!collidedObject.isSolid()) {
          continue;
        }
        
        if (collidedObject instanceof Player) {
          this.explode(environment);
          collidedObject.damage(10);
          // Sets an appropriate velocity to add to the player when blasted away.
          this.velocity_.y = -15.0;
          collidedObject.addVelocity(this.getVelocity());
          environment.removePhysicalObject((PhysicalObject) this);
          return;
        }
        
        // Prevents the player from moving inside other objects horizonally.
        if (Maf.almostEqual(abs(collidedObject.getX() - this.getX()), (collidedObject.getWidth() / 2) + (this.getWidth() / 2))) {
          if (collidedObject.getX() > this.getX()) {
            this.velocity_.x = min(0, this.velocity_.x);
          } else {
            this.velocity_.x = max(0, this.velocity_.x);
          }
        }
        if (Maf.almostEqual(abs(collidedObject.getZ() - this.getZ()), (collidedObject.getLength() / 2) + (this.getLength() / 2))) {
          if (collidedObject.getZ() > this.getZ()) {
            this.velocity_.z = min(0, this.velocity_.z);
          } else {
            this.velocity_.z = max(0, this.velocity_.z);
          }
        }
        
        // Prevents the player from falling into another object. Minimizes at the floor level just in case.
        if (Maf.almostEqual(abs(collidedObject.getY() - this.getY()), (collidedObject.getHeight() / 2) + (this.getHeight() / 2))) {
          if (collidedObject.getY() > this.getY()) {
            this.velocity_.y = min(0, this.velocity_.y);
            this.position_[1] = collidedObject.getY() - (collidedObject.getHeight() / 2 + this.getHeight() / 2);
          }
        } else if (this.position_[1] >= Environment.FLOOR_LEVEL - this.getHeight() / 2) {
          this.position_[1] = Environment.FLOOR_LEVEL - this.getHeight() / 2;
          this.velocity_.y = min(0, this.velocity_.y);
        }
      }
    }
    
    if (this.health_ <= 0) {
      this.explode(environment);
      // When a TrackerMine dies, it has a chance of spawning a HealthPack.
      if (random(100) < 20) {
        environment.addPhysicalObject(new HealthPack(this.getPosition()));
      }
      environment.removePhysicalObject((PhysicalObject) this);
      return;
    }
    
    this.position_[0] += this.velocity_.x;
    this.position_[1] += this.velocity_.y;
    this.position_[2] += this.velocity_.z;
    this.updateTracking(environment);
  }
  
  public void updateTracking(Environment environment) {
    this.velocity_.x = environment.getPlayer().getX() - this.getX();
    this.velocity_.y = environment.getPlayer().getY() - this.getY();
    this.velocity_.z = environment.getPlayer().getZ() - this.getZ();
    this.velocity_.setMag(TrackerMine.MOVE_VELOCITY);
  }
  
  public void explode(Environment environment) {
    environment.getPlayer().incrementScore();
    environment.addExplosion(new Explosion(this.position_));
  }
  
  public void redrawOnScreen() {
    pushMatrix();
    translate(this.position_[0], this.position_[1], this.position_[2]);
    fill(TrackerMine.MINE_COLOR);
    stroke(TrackerMine.MINE_STROKE_COLOR);
    sphere(TrackerMine.MINE_RADIUS);
    popMatrix();
  }
  
  public float getHealth() {
    return this.health_;
  }
  
  public void damage(float health) {
    this.health_ = max(this.health_ - health, 0);
  }
}
