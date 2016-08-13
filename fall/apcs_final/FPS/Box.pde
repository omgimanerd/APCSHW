/**
 * @author Alvin Lin (alvin.lin@stuypulse.com)
 * This class encapsulates a physical box in the 3D world.
 * The physics and rendering of the box are handled in this class.
 */
 
public class Box extends PhysicalObject {
  
  private int sideColor_;
  private int strokeColor_;
  
  public Box(float x, float y, float z,
             float sideLength,
             int sideColor, int strokeColor) {
    this(x, y, z,
         sideLength, sideLength, sideLength,
         sideColor, strokeColor);
  }
  
  public Box(float x, float y, float z,
             float width_, float height_, float length_,
             int sideColor, int strokeColor) {
    super(x, y, z,
          width_, height_, length_,
          0.0, 0.0, 0.0,
          0.0, 0.0, 0.0);
    this.sideColor_ = sideColor;
    this.strokeColor_ = strokeColor;
  }
  
  public void updatePhysics(Environment environment) {
    this.velocity_.add(this.acceleration_);
    this.position_[0] += this.velocity_.x;
    this.position_[1] += this.velocity_.y;
    this.position_[2] += this.velocity_.z;
  }
  
  public void redrawOnScreen() {
    pushMatrix();
    fill(this.sideColor_);
    stroke(this.strokeColor_);
    translate(this.getX(), this.getY(), this.getZ());
    box(this.getWidth(), this.getHeight(), this.getLength());
    popMatrix();
  }
  
  public int getSideColor() {
    return this.sideColor_;
  }
  
  public Box setSideColor(int sideColor) {
    this.sideColor_ = sideColor;
    return this;
  }
  
  public int getStrokeColor() {
    return this.strokeColor_;
  }
  
  public Box setStrokeColor(int strokeColor) {
    this.strokeColor_ = strokeColor;
    return this;
  }
}
