/**
 * @author Alvin Lin (alvin.lin@stuypulse.com)
 * This class encapsulates an explosion.
 * It contains a subclass called Particle, which
 * encapsulates each particle in the explosion.
 * The rendering of the explosion is handled in this class.
 */

public class Explosion {

  private class Particle extends PhysicalObject {
    
    private static final float PARTICLE_SIZE = 1.0;
    private static final float EXPLOSION_RADIUS = 200;
    
    private int[] particle_colors = {
        #FF2400, #FF3300, #FF5721, #FF8C00,
        #FFE600, #FF3030, #FC1501, #FF3D0D
    };
    private int particleColor_ = 0;

    public Particle(float[] position, PVector velocity, Explosion parentExplosion) {
      super(position[0], position[1], position[2], 
            Particle.PARTICLE_SIZE,
            velocity.x, velocity.y, velocity.z, 
            0.0, 0.0, 0.0);
      this.setUnsolid();
    }

    public void update(Explosion explosion) {
      this.position_[0] += this.velocity_.x;
      this.position_[1] += this.velocity_.y;
      this.position_[2] += this.velocity_.z;
      this.setColor(this.getRandomParticleColor());
      if (Maf.distanceL1(explosion.getPosition(), this.position_) > Particle.EXPLOSION_RADIUS) {
        explosion.removeParticle(this);
      }
    }
    
    public void redrawOnScreen() {
      pushMatrix();
      translate(this.position_[0], this.position_[1], this.position_[2]);
      stroke(this.particleColor_);
      fill(this.particleColor_);
      sphere(Particle.PARTICLE_SIZE);
      popMatrix();
    }
    
    public int getColor() {
      return this.particleColor_;
    }
    
    public void setColor(int particleColor) {
      this.particleColor_ = particleColor;
    }
    
    public int getRandomParticleColor() {
      return this.particle_colors[(int) random(this.particle_colors.length)];
    }
  }

  private static final int NUM_PARTICLES = 25;

  private float[] position_;
  private ArrayList<Particle> particles_ = new ArrayList<Particle>();
  
  public Explosion(float[] position) {
    this.position_ = position;
    for (int i = 0; i < Explosion.NUM_PARTICLES; ++i) {
      this.particles_.add(new Particle(this.position_, this.getRandomParticleVelocity(),
                                       this));
    }                              
  }
  
  public void update(Environment enviroment) {
    for (int i = 0; i < this.particles_.size(); ++i) {
      this.particles_.get(i).redrawOnScreen();
      this.particles_.get(i).update(this);
    }
    if (this.particles_.size() == 0) {
      enviroment.removeExplosion(this);
    }
  }
  
  public float[] getPosition() {
    return this.position_;
  }
  
  public void addParticle(Particle particle) {
    this.particles_.add(particle);
  }
  
  public void removeParticle(Particle particle) {
    this.particles_.remove(particle);
    particle = null;
  }
  
  public PVector getRandomParticleVelocity() {
    return new PVector(random(-10, 11), random(-10, 11), random(-10, 11));
  }
}
    

