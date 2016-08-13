/**
 * Class encapsulating a drawn box in the environment.
 */
public class Box extends PhysicalObject {
    
  private int sideColor_;
  private int strokeColor_;
  
  public Box(float x, float y, float z,
             float yaw, float pitch, float roll,
             float w, float h, float l,
             int sideColor, int strokeColor) {
    super(x, y, z, 
          yaw, pitch, roll,
          0, 0, 0,
          0, 0, 0,
          w, h, l);
    this.sideColor_ = sideColor;
    this.strokeColor_ = strokeColor;
    this.health_ = 1;
  }
  
  public void update(Environment environment) {
    if (this.isDead()) {
      this.shouldExist_ = false;
    }
  }
  
  public void render() {
    pushMatrix();
    fill(this.sideColor_);
    stroke(this.strokeColor_);
    translate(this.getX(), this.getY(), this.getZ());
    rotateX(this.getRoll());
    rotateY(this.getYaw());
    rotateZ(this.getPitch());
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
 
