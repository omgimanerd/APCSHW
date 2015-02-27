/**
 * @author Elias Saric (esaric)
 * This class encapsulates the game environment.
 * It holds all the objects and explosions and handles
 * their update, rendering, and drawing.
 * This class plays the sounds of the game as well.
 */
 
public class Environment {
  
  public static final float FLOOR_LEVEL = 0.0;
  public static final int MAX_TRACKER_MINES = 4;
  
  private ArrayList<PhysicalObject> objects_;
  private ArrayList<Explosion> explosions_;
  private Map map_;
  private boolean radialMode_;

  public Environment() {
    this.objects_ = new ArrayList<PhysicalObject>();
    this.explosions_ = new ArrayList<Explosion>();
    this.map_ = new Map(this);
    this.radialMode_ = true;
  }

  public void updateObjects() {
    for (int i = 0; i < this.objects_.size(); ++i) {
      this.objects_.get(i).redrawOnScreen();
      this.objects_.get(i).updatePhysics(this);
    }
    if (this.getNumTrackerMines() < Environment.MAX_TRACKER_MINES) {
      this.map_.spawnTrackerMine();
    }
  }
  
  public void updateExplosions() {
    for (int i = 0; i < this.explosions_.size(); ++i) {
      this.explosions_.get(i).update(this);
    }
  }
  
  // Called once to add all the boxes creating the map into the ArrayList of objects.
  public void buildMap() {
    this.map_.buildMap();
  }
  
  // Getters, setters, and modifiers.
  public Player getPlayer() {
    for (int i = 0; i < this.objects_.size(); ++i) {
      if (this.objects_.get(i) instanceof Player) {
        return (Player) this.objects_.get(i);
      }
    }
    throw new Error("No player found");
  }
  
  public int getNumTrackerMines() {
    int numTrackerMines = 0;
    for (int i = 0; i < this.objects_.size(); ++i) {
      if (this.objects_.get(i) instanceof TrackerMine) {
        numTrackerMines++;
      }
    }
    return numTrackerMines;
  }
  
  public void reset() {
    for (int i = 0; i < this.objects_.size(); ++i) {
      if (this.objects_.get(i) instanceof TrackerMine) {
        this.removePhysicalObject(this.objects_.get(i));
      }
    }
  }
  
  public void addPhysicalObject(PhysicalObject object) {
    this.objects_.add(object);
  }

  public void removePhysicalObject(PhysicalObject object) {
    this.objects_.remove(object);
    object = null;
  }
  
  public void addExplosion(Explosion explosion) {
    this.explosions_.add(explosion);
    Sound.play("explosion.mp3");
  }
  
  public void removeExplosion(Explosion explosion) {
    this.explosions_.remove(explosion);
    explosion = null;
  }

  public ArrayList<PhysicalObject> getObjects() {
    return this.objects_;
  }
  
  public ArrayList<Explosion> getExplosions() {
    return this.explosions_;
  }
  
  public boolean getRadialMode() {
    return this.radialMode_;
  }
  
  public Environment setRadialMode(boolean radialMode) {
    this.radialMode_ = radialMode;
    return this;
  }
}

