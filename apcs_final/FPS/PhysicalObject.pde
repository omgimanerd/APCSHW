/**
 * @author Alvin Lin (alvin.lin@stuypulse.com)
 * This class encapsulates all the properties and methods
 * of a physical object in the game environment. All other physical
 * objects such as bullets and boxes extend this class.
 * This class has base methods to help detect collision.
 */

public abstract class PhysicalObject {
  
  // In the 3D rendered world, Y increases downward.
  // Acceleration in the Y-direction is therefore positive.
  public static final float DEFAULT_AX = 0;
  public static final float DEFAULT_AY = 0.75;
  public static final float DEFAULT_AZ = 0;
  
  protected float[] position_;
  // All objects will be cubic or rectangular prismic.
  protected float[] size_;
  protected PVector velocity_;
  protected PVector acceleration_;
  protected boolean isSolid_ = true;
  
  // Each object should have its own method to update its position
  // accordingly depending on its behavior.
  public void updatePhysics(Environment environment) {}
  
  // Each object should have its own method to draw itself.
  public void redrawOnScreen() {}
  
  // Used by damageable objects.
  public void damage(float health) {}
  public void heal(float health) {}
  
  public PhysicalObject(float x, float y, float z,
                        float sideLength,
                        float vx, float vy, float vz,
                        float ax, float ay, float az) {
    this(x, y, z,
         sideLength, sideLength, sideLength,
         vx, vy, vz,
         ax, ay, az);
  }
  
  public PhysicalObject(float x, float y, float z,
                        float width_, float height_, float length_,
                        float vx, float vy, float vz,
                        float ax, float ay, float az) {
    this.position_ = new float[] {x, y, z};
    this.size_ = new float[] {width_, height_, length_};
    this.velocity_ = new PVector(vx, vy, vz);
    this.acceleration_ = new PVector(ax, ay, az);
  }
  
  // Returns an ArrayList of the objects inside an environment if and only if this
  // object is in that environment.
  // Does this cause overhead?
  private ArrayList<PhysicalObject> getEnvironmentObjects(Environment environment) {
    ArrayList<PhysicalObject> objects = environment.getObjects();
    if (objects.indexOf(this) == -1) {
      throw new Error("This object is not inside the specified environment");
    }
    return objects;
  }
  
  // Returns an ArrayList of the PhysicalObjects within a radius.
  public ArrayList<PhysicalObject> getNearbyObjects(Environment environment, float radius) {
    ArrayList<PhysicalObject> objects = this.getEnvironmentObjects(environment);
    ArrayList<PhysicalObject> nearbyObjects = new ArrayList<PhysicalObject>();
    for (int i = 0; i < objects.size(); ++i) {
      if (Maf.distanceL2(this.getPosition(), objects.get(i).getPosition()) < radius &&
          this != objects.get(i)) {
        nearbyObjects.add(objects.get(i));
      }
    }
    return nearbyObjects;
  }
  
  // http://gamedev.stackexchange.com/questions/60505/how-to-check-for-cube-collisions
  // hasCollided() assumes all parity checks have been passed.
  public boolean hasCollidedWith(PhysicalObject object) {
    return abs(this.getX() - object.getX()) < (this.getWidth() / 2) + (object.getWidth() / 2) &&
           abs(this.getY() - object.getY()) < (this.getHeight() / 2) + (object.getHeight() / 2) &&
           abs(this.getZ() - object.getZ()) < (this.getLength() / 2)+ (object.getLength() / 2);
  }
  
  public boolean hasPointCollidedWith(float[] point) {
    return abs(this.getX() - point[0]) < this.getWidth() / 2 &&
           abs(this.getY() - point[1]) < this.getHeight() / 2 &&
           abs(this.getZ() - point[2]) < this.getLength() / 2;
  }
  
  // Returns an ArrayList of objects this object has collided with.
  public ArrayList<PhysicalObject> getCollidedObjects(Environment environment) {
    ArrayList<PhysicalObject> objects = this.getEnvironmentObjects(environment);
    ArrayList<PhysicalObject> collidedObjects = new ArrayList<PhysicalObject>();
    for (int i = 0; i < objects.size(); ++i) {
      PhysicalObject object = objects.get(i);
      if (this == object) {
        continue;
      }
      if (this.hasCollidedWith(object)) {
        collidedObjects.add(object);
      }
    }
    return collidedObjects;
  }
  
  public float[] getPosition() {
    return this.position_;
  }
  
  public PhysicalObject setPosition(float[] position) {
    this.position_ = position;
    return this;
  }
  
  public float getX() {
    return this.position_[0];
  }
  
  public PhysicalObject setX(float x) {
    this.position_[0] = x;
    return this;
  }
  
  public float getY() {
    return this.position_[1];
  }
  
  public PhysicalObject setY(float y) {
    this.position_[1] = y;
    return this;
  }
  
  public float getZ() {
    return this.position_[2];
  }
  
  public PhysicalObject setZ(float z) {
    this.position_[2] = z;
    return this;
  }
  
  public float[] getSize() {
    return this.size_;
  }
  
  public PhysicalObject setSize(float[] size) {
    this.size_ = size;
    return this;
  }
  
  public PhysicalObject setSize(float width_, float height_, float length_) {
    this.setWidth(width_);
    this.setHeight(height_);
    this.setLength(length_);
    return this;
  }
  
  public float getWidth() {
    return this.size_[0];
  }
  
  public PhysicalObject setWidth(float width_) {
    this.size_[0] = width_;
    return this;
  }
  
  public float getHeight() {
    return this.size_[1];
  }
  
  public PhysicalObject setHeight(float height_) {
    this.size_[1] = height_;
    return this;
  }
  
  public float getLength() {
    return this.size_[2];
  }
  
  public PhysicalObject setLength(float length_) {
    this.size_[2] = length_;
    return this;
  }
  
  public PVector getVelocity() {
    return this.velocity_;
  }
  
  public PhysicalObject setVelocity(PVector velocity) {
    this.velocity_.set(velocity);
    return this;
  }
  
  public PhysicalObject addVelocity(PVector velocity) {
    this.velocity_.add(velocity);
    return this;
  }
  
  public PVector getAcceleration() {
    return this.acceleration_;
  }
  
  public PhysicalObject setAcceleration(PVector acceleration) {
    this.acceleration_.set(acceleration);
    return this;
  }
  
  public boolean isSolid() {
    return this.isSolid_;
  }
  
  public PhysicalObject setSolid() {
    this.isSolid_ = true;
    return this;
  }
  
  public PhysicalObject setUnsolid() {
    this.isSolid_ = false;
    return this;
  }
}
