/**
 * Class encapsulates all properites and methods
 * of a physical object in the game environment. 
 * Includes size, position, and base collision
 * detection
 */

public abstract class PhysicalObject {
  // In the 3D rendered world, Y increases downward.
  // Acceleration in the Y-direction is therefore positive.
  // Constants
  public static final float DEFAULT_AX = 0;
  public static final float DEFAULT_AY = 0.15;
  public static final float DEFAULT_AZ = 0;

  protected int health_;
  
  protected PVector position_;
  protected PVector orientation_;
  protected PVector velocity_;
  protected PVector acceleration_;
  protected float[] size_;
  // We will build the world on the assumption that the world components
  // and stuff will be mostly square, hence every object will have a
  // spherical hitbox.
  protected float collisionRadius_;
  
  protected boolean destroyable_ = false;
  protected boolean shouldExist_ = true;  
  
  public PhysicalObject(float x, float y, float z,
                        float yaw, float pitch, float roll,
                        float vx, float vy, float vz,
                        float ax, float ay, float az,
                        float sideLength) {
    this(x, y, z,
         yaw, pitch, roll,
         vx, vy, vz,
         ax, ay, az,
         sideLength, sideLength, sideLength);
  }
  
  public PhysicalObject(float x, float y, float z,
                        float yaw, float pitch, float roll,
                        float vx, float vy, float vz,
                        float ax, float ay, float az,
                        float w, float h, float l) {
    this.position_ = new PVector(x, y, z);
    this.orientation_ = new PVector(yaw, pitch, roll);
    this.velocity_ = new PVector(vx, vy, vz);
    this.acceleration_ = new PVector(ax, ay, az);
    this.size_ = new float[] {w, h, l};
    this.setSolid();
  }

  // Each object should have its own update method that updates it
  // and checks position with all other objects.
  public abstract void update(Environment environment);

  // Each object should have its own method to draw itself.
  public abstract void render();
  
  public boolean hasCollidedWith(PhysicalObject object) {
    return object.isSolid() &&
        Util.getEuclideanDistance2(this.getX(), this.getY(), this.getZ(),
            object.getX(), object.getY(), object.getZ()) <
            sq(this.getCollisionRadius() + object.getCollisionRadius());
        
  }
  
  public boolean hasPointCollidedWith(float[] point) {
    return Util.getEuclideanDistance2(
        this.getX(), this.getY(), this.getZ(),
        point[0], point[1], point[2]) < sq(this.collisionRadius_);
  }

  // Checks through the environment and returns the first PhysicalObject this
  // object has collided with. null if no collision yet.
  public PhysicalObject getCollidedObject(Environment environment) {
    ArrayList<PhysicalObject> objects = environment.getObjects();
    for (PhysicalObject object : objects) {
      if (this == object) {
        continue;
      } else if (this.hasCollidedWith(object)) {
        return object;
      }
    }
    return null;
  }

  // Returns an ArrayList of objects this object has collided with.
  public ArrayList<PhysicalObject> getCollidedObjects(Environment environment) {
    ArrayList<PhysicalObject> objects = environment.getObjects();
    ArrayList<PhysicalObject> collidedObjects = new ArrayList<PhysicalObject>();
    for (PhysicalObject object : objects) {
      if (this == object) {
        continue;
      } else if (hasCollidedWith(object)) {
        collidedObjects.add(object);
      }
    }
    return collidedObjects;
  }
  
  public int getHealth() {
    return this.health_;
  }
  
  public PhysicalObject setHealth(int health) {
    this.health_ = health;
    return this;
  }
  
  public PhysicalObject damage(int amount) {
    this.health_ -= amount;
    return this;
  }
  
  public PhysicalObject heal(int amount) {
    this.health_ += amount;
    return this;
  }
  
  public boolean isDead() {
    return this.destroyable_ && this.health_ <= 0;
  }
  
  public PVector getPosition() {
    return position_;
  }
  
  public PhysicalObject setPosition(PVector position) {
    position_ = position;
    return this;
  }
  
  public float getX() {
    return position_.x;
  }
  
  public PhysicalObject setX(float x) {
    position_.x = x;
    return this;
  }
  
  public float getY() {
    return position_.y;
  }
  
  public PhysicalObject setY(float y) {
    position_.y = y;
    return this;
  }
  
  public float getZ() {
    return position_.z;
  }
  
  public PhysicalObject setZ(float z) {
    position_.z = z;
    return this;
  }
  
  public PVector getOrientation() {
    return orientation_;
  }
  
  public PhysicalObject setOrientation(PVector orientation) {
    orientation_ = orientation;
    return this;
  }
  
  public float getYaw() {
    return orientation_.x;
  }
  
  public PhysicalObject setYaw(float yaw) {
    orientation_.x = yaw;
    return this;
  }
  
  public float getPitch() {
    return orientation_.y;
  }
  
  public PhysicalObject setPitch(float pitch) {
    orientation_.y = pitch;
    return this;
  }
  
  public float getRoll() {
    return orientation_.z;
  }
  
  public PhysicalObject setRoll(float roll) {
    orientation_.z = roll;
    return this;
  }
  
  public PVector getVelocity() {
    return velocity_;
  }
  
  
  public PhysicalObject setVelocity(PVector velocity) {
    velocity_.set(velocity);
    return this;
  }
  
  public PhysicalObject addVelocity(PVector velocity) {
    velocity_.add(velocity);
    return this;
  }
  
  public PVector getAcceleration() {
    return acceleration_;
  }
  
  public PhysicalObject setAcceleration(PVector acceleration) {
    acceleration_.set(acceleration);
    return this;
  }
  
  public float[] getSize() {
    return size_;
  }
  
  public PhysicalObject setSize(float[] size) {
    size_ = size;
    return this;
  }
  
  public PhysicalObject setSize(float width_, float height_, float length_) {
    setWidth(width_);
    setHeight(height_);
    setLength(length_);
    return this;
  }
  
  public float getWidth() {
    return size_[0];
  }
  
  public PhysicalObject setWidth(float width_) {
    size_[0] = width_;
    return this;
  }
  
  public float getHeight() {
    return size_[1];
  }
  
  public PhysicalObject setHeight(float height_) {
    size_[1] = height_;
    return this;
  }
  
  public float getLength() {
    return size_[2];
  }
  
  public PhysicalObject setLength(float length_) {
    size_[2] = length_;
    return this;
  }
  
  public float getCollisionRadius() {
    return this.collisionRadius_;
  }
  
  public PhysicalObject setCollisionRadius(float collisionRadius) {
    this.collisionRadius_ = collisionRadius;
    return this;
  }
  
  public boolean isSolid() {
    return this.collisionRadius_ != 0;
  }
  
  public PhysicalObject setSolid() {
    this.setCollisionRadius(Util.average(this.getWidth() / 2,
                                         this.getHeight() / 2,
                                         this.getLength() / 2));
    return this;
  }
  
  public PhysicalObject setUnsolid() {
    this.setCollisionRadius(0);
    return this;
  }
  
  public boolean isDestroyable() {
    return this.destroyable_;
  }
  
  public PhysicalObject setDestroyable() {
    this.destroyable_ = true;
    return this;
  }
  
  public PhysicalObject setUndestroyable() {
    this.destroyable_ = false;
    return this;
  }
  
  public boolean shouldExist() {
    return this.shouldExist_;
  }
}
