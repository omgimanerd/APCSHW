/**
 * @author Alvin Lin (alvin.lin@stuypulse.com)
 * This class encapsulates a health pack that the player
 * can pick up to heal themselves. All handling is done
 * inside this class.
 */

public class HealthPack extends PhysicalObject {
  
  public static final float MIN_HEAL_AMOUNT = 5;
  public static final float MAX_HEAL_AMOUNT = 15;
  public static final float HEALTH_PACK_SIZE = 50;
  public static final float MAX_HEALTH = 50;
  public static final int HEALTH_PACK_COLOR = #FFFFFF;
  
  private float healAmount_;
  private float health_;
  
  public HealthPack(float position[]) {
    this(position[0], position[1], position[2]);
  }
  
  public HealthPack(float x, float y, float z) {
    super(x, y, z,
          HealthPack.HEALTH_PACK_SIZE,
          0.0, 0.0, 0.0,
          0.0, PhysicalObject.DEFAULT_AY, 0.0);
    this.healAmount_ = random(HealthPack.MIN_HEAL_AMOUNT, MAX_HEAL_AMOUNT + 1);
    this.health_ = HealthPack.MAX_HEALTH;
  }

  public void updatePhysics(Environment environment) {
    this.velocity_.add(this.acceleration_);
    
    ArrayList<PhysicalObject> collidedObjects = this.getCollidedObjects(environment);
    if (collidedObjects.size() > 0) {
      for (int i = 0; i < collidedObjects.size(); ++i) {
        PhysicalObject collidedObject = collidedObjects.get(i);
        
        if (collidedObject instanceof Player) {
          collidedObject.heal(this.healAmount_);
          Sound.play("bop.mp3");
          environment.removePhysicalObject((PhysicalObject) this);
          return;
        }
        
        // Prevents the healthpack from falling into another object. Minimizes at the floor level just in case.
        if (this.position_[1] >= Environment.FLOOR_LEVEL - this.getHeight() / 2) {
          this.velocity_.y = min(0, this.velocity_.y);
          this.position_[1] = Environment.FLOOR_LEVEL - this.getHeight() / 2;
        } else if (Maf.almostEqual(abs(collidedObject.getY() - this.getY()), (collidedObject.getHeight() / 2) + (this.getHeight() / 2))) {
          if (collidedObject.getY() > this.getY()) {
            this.velocity_.y = min(0, this.velocity_.y);
            this.position_[1] = collidedObject.getY() - (collidedObject.getHeight() / 2 + this.getHeight() / 2);
          } else {
            this.velocity_.y = max(0, this.velocity_.y);
            this.position_[1] = collidedObject.getY() + (collidedObject.getHeight() / 2 + this.getHeight() / 2);
          }
        }
      }
    }
    
    // Health packs can be destroyed.
    if (this.health_ <= 0) {
      this.explode(environment);
      environment.removePhysicalObject((PhysicalObject) this);
      return;
    }
    
    // Health packs will only fall downward.
    this.position_[1] += this.velocity_.y;
  }

  public void redrawOnScreen() {
    pushMatrix();
    translate(this.position_[0], this.position_[1], this.position_[2]);
    fill(HealthPack.HEALTH_PACK_COLOR);
    box(HealthPack.HEALTH_PACK_SIZE);
    translate(0.0, - HealthPack.HEALTH_PACK_SIZE / 2, 0.0);
    fill(#FF0000);
    box(HealthPack.HEALTH_PACK_SIZE / 4, 1.0, HealthPack.HEALTH_PACK_SIZE);
    box(HealthPack.HEALTH_PACK_SIZE, 1.0, HealthPack.HEALTH_PACK_SIZE / 4);
    popMatrix();
  }
  
  public void explode(Environment environment) {
    environment.addExplosion(new Explosion(this.position_));
  }
  
  public float getHealth() {
    return this.health_;
  }
  
  public void damage(float health) {
    this.health_ = max(this.health_ - health, 0);
  }
}
