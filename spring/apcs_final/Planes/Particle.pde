import java.lang.*;
import java.util.*;

/**
 * Class encapsulating non-solid particles that comprise explosions.
 */
public class Particle extends PhysicalObject {
  
  private final int[] PARTICLE_COLORS = new int[] {
      #FF0000, #FAFF81, #E03616, #F5F2B8, #E58C8A, #F67E7D
  };
    
  private static final float MIN_VELOCITY = -0.25;
  private static final float MAX_VELOCITY = 0.25;
  private static final float MIN_START_SIZE = 1;
  private static final float MAX_START_SIZE = 4;
  private static final int MIN_DURATION = 5000;
  private static final int MAX_DURATION = 5500;
  
  private static final int MIN_EXPLOSION_DENSITY = 0;
  private static final int MAX_EXPLOSION_DENSITY = 20;
  
  private float expansionRate_;
  private long expirationTime_;
  private int particleColor_;
  
  public Particle(float x, float y, float z,
                  float vx, float vy, float vz,
                  float size, long duration,
                  int particleColor) {
    super(x, y, z,
          0, 0, 0,
          vx, vy, vz,
          0, 0, 0,
          size);
    this.setUnsolid();
    this.expirationTime_ = System.currentTimeMillis() + duration;
    this.particleColor_ = particleColor;
  }

  public Particle(float x, float y, float z) {
    this(x, y, z, 0, 0, 0, 0, 0, 0);
    this.velocity_.x = random(MIN_VELOCITY, MAX_VELOCITY);
    this.velocity_.y = random(MIN_VELOCITY, MAX_VELOCITY);
    this.velocity_.z = random(MIN_VELOCITY, MAX_VELOCITY);
    this.setWidth(random(MIN_START_SIZE, MAX_START_SIZE));
    this.particleColor_ = PARTICLE_COLORS[floor(random(PARTICLE_COLORS.length))];
    this.expirationTime_ = System.currentTimeMillis() + (long) random(MIN_DURATION, MAX_DURATION);
  }

  // Omfg I can't make this static because Processing is stupid.
  public ArrayList<Particle> generateExplosion(PVector origin) {
    return generateExplosion(origin.x, origin.y, origin.z);
  }

  public ArrayList<Particle> generateExplosion(float x, float y, float z) {
    ArrayList<Particle> explosion = new ArrayList<Particle>();
    int explosionDensity = (int) (random(MIN_EXPLOSION_DENSITY, MAX_EXPLOSION_DENSITY));
    for (int i = 0; i < explosionDensity; ++i) {
      explosion.add(new Particle(x, y, z));
    }
    return explosion;
  }

  public void update(Environment environment) {
    if (System.currentTimeMillis() > this.expirationTime_) {
      this.shouldExist_ = false;
    }
    
    this.position_.add(this.velocity_);
  }
  
  public void render() {
    pushMatrix();
    translate(this.getX(), this.getY(), this.getZ());
    stroke(this.particleColor_);
    fill(this.particleColor_);
    sphere(this.getWidth());
    popMatrix();
  }
}
