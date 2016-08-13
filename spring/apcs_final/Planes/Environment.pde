import java.util.*;

/**
 * Class the manages, updates, and renders everything in the world.
 */
public class Environment {
  
  private static final int SKY_COLOR = #7EC0EE;
  private static final int MAP_MIN = -5000;
  private static final int MAP_MAX = 5000;
  private final int[][] POSSIBLE_SPAWNS = new int[][] {
    {2000, -50, 2000}, {2000, -75, -2000}, {-2000, -100, 2000}, {-2000, -40, -2000}
  }; 
  
  private Map map_;
  private ArrayList<PhysicalObject> objects_;
  private ArrayList<Particle> particles_;
  private Particle explosionGenerator_;
  
  public Environment() {
    this.map_ = new Map();
    this.objects_ = new ArrayList<PhysicalObject>();
    this.objects_.addAll(this.map_.getMap());
    this.particles_ = new ArrayList<Particle>();
    this.explosionGenerator_ = new Particle(0, 0, 0);
  }
  
  public void update() {
    for (int i = 0; i < this.objects_.size(); ++i) {
      PhysicalObject object = this.objects_.get(i);
      object.update(this);
      if (!object.shouldExist()) {
        if (object instanceof Plane) {
          int[] spawn = POSSIBLE_SPAWNS[floor(random(POSSIBLE_SPAWNS.length))];
          this.objects_.set(i, (PhysicalObject) new Plane(spawn[0], spawn[1], spawn[2],
                               0, 3 * HALF_PI, 0));
        } else {
          this.objects_.remove(object);
          i--;
        }
      }
    }
    for (int i = 0; i < this.particles_.size(); ++i) {
      Particle particle = this.particles_.get(i);
      particle.update(this);
      if (!particle.shouldExist()) {
        this.particles_.remove(particle);
        i--;
      }
    }
  }
  
  public void render() {
    background(SKY_COLOR);
    
    pushMatrix();
    for (int i = -5000; i < 5000; i+=100) {
      stroke(#000000);
      line(i, 0, -5000, i, 0, 5000);
      line(-5000, 0, i, 5000, 0, i);
    }
    popMatrix();

    for (PhysicalObject object : this.objects_) {
      // Skip rendering the player because that MUST be called
      // first for the camera to work properly.
      if (object instanceof Player) {
        continue;
      }
      object.render();
    }
    for (Particle particle : this.particles_) {
      particle.render();
    }
  }
  
  public Environment addPlayer(Player player) {
    this.objects_.add(player);
    return this;
  }
  
  public Player getPlayer() {
    for (PhysicalObject object : this.objects_) {
      if (object instanceof Player) {
        return (Player) object;
      }
    }
    return null;
  }
  
  public Environment addObject(PhysicalObject object) {
    this.objects_.add(object);
    return this;
  }
  
  public ArrayList<PhysicalObject> getObjects() {
    return this.objects_;
  }
  
  public Environment addParticle(Particle particle) {
    this.particles_.add(particle);
    return this;
  }
  
  public Environment generateExplosion(PVector location) {
    this.particles_.addAll(this.explosionGenerator_.generateExplosion(location));
    Sound.play("explosion.mp3");
    return this;
  }
  
  public ArrayList<Particle> getParticles() {
    return this.particles_;
  }
}
  
  
