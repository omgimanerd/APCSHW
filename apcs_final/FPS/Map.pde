/**
 * @author Elias Saric (esaric)
 * This class encapsulates all the methods and numbers
 * to generate the map inside the game environment.
 * This class also handles the generation of the
 * tracker mines.
 */
 
public class Map {
  
  private static final int FLOOR_COLOR = #808080;
  private static final int BUILDING_WALLCOLOR = #CCFFCC;
  private static final int NWBUILDING_WALLCOLOR = #FF8100;
  private static final int SWABUILDING_WALLCOLOR = #0266C8;
  private static final int SWBBUILDING_WALLCOLOR = #FF40A7;
  private static final int SWSTAIRCASE_COLOR = #423E3E;
  private static final int SEBUILDING_WALLCOLOR = #5C0D21;
  private static final int PARK_WALLCOLOR = #CCCCCC;
  private static final int TREE_TRUNKCOLOR = #804A2D;
  private static final int TREE_BUSHCOLOR = #48B427;
  
  private static final int DEFAULT_STROKECOLOR = #000000;
  
  private static final float MINIMUM_SPAWN_DISTANCE = 300;
  private final float[][] TRACKER_MINE_SPAWN_POINTS = new float[][] {
    {1800.0, -150.0, -100.0},
    {400, -150.0, -2400.0},
    {-400.0, -150.0, -2400.0},
    {-1800.0, -150.0, -100.0},
    {-1800.0, -150.0, 100.0},
    {-400.0, -150.0, 2400.0},
    {400.0, -150.0, 2400.0},
  };
  
  private Environment environment_;
  
  public Map(Environment environment) {
    this.environment_ = environment;
  }

  // Called once to build the whole Map.
  public void buildMap() {
    // floor
    this.addBox(0, 0.5, 0, 10000, 1, 10000, Map.FLOOR_COLOR, Map.FLOOR_COLOR);
    
    this.environment_.setRadialMode(false);
    // outer walls
    this.addBox(-2000, 0, -2600, 4000, 1000, 100, Map.BUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-2000, 0, -2500, 100, 1000, 5000, Map.BUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-2000, 0, 2500, 4000, 1000, 100, Map.BUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(1900, 0, -2500, 100, 1000, 5000, Map.BUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    
    // SE building
    this.addBox(500, 0, -2500, 100, 400, 900, Map.SEBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(500, 0, -1400, 100, 400, 900, Map.SEBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(600, 0, -600, 600, 400, 100, Map.SEBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(1400, 0, -600, 500, 400, 100, Map.SEBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(1000, 0, -1700, 500, 400, 500, Map.SEBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(500, -400, -2500, 1400, 50, 2000, Map.SEBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    
    // SW building A
    this.addBox(-600, 0, -2500, 100, 300, 300, Map.SWABUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-600, 0, -2000, 100, 300, 300, Map.SWABUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-1400, 0, -1700, 900, 300, 100, Map.SWABUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-1900, 0, -1700, 300, 400, 1200, Map.SWABUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-1900, -300, -2500, 1400, 50, 900, Map.SWABUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    
    // SW building B
    this.addBox(-1400, 0, -1400, 800, 200, 100, Map.SWBBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-1400, 0, -100, 100, 200, 500, Map.SWBBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-1400, 0, -600, 900, 200, 100, Map.SWBBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-600, 0, -1400, 100, 200, 600, Map.SWBBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-1400, -200, -1400, 900, 50, 900, Map.SWBBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    
    // SW Staircase
    this.addBox(-1600, 0, -1700, 200, 50, 100, Map.SWSTAIRCASE_COLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-1600, 0, -1600, 200, 100, 200, Map.SWSTAIRCASE_COLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-1600, 0, -1400, 200, 50, 100, Map.SWSTAIRCASE_COLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-1400, 0, -1600, 100, 50, 200, Map.SWSTAIRCASE_COLOR, Map.DEFAULT_STROKECOLOR);
    
    // NW building
    this.addBox(-1900, 0, 500, 800, 500, 100, Map.NWBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR); // exterior walls
    this.addBox(-900, 0, 500, 400, 500, 100, Map.NWBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-600, 0, 600, 100, 500, 1100, Map.NWBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-600, 0, 1900, 100, 500, 600, Map.NWBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    
    this.addBox(-1200, 0, 600, 100, 500, 300, Map.NWBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR); // interior walls
    this.addBox(-900, 0, 600, 100, 500, 300, Map.NWBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-1700, 0, 900, 100, 500, 1600, Map.NWBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-900, 0, 1600, 100, 500, 200, Map.NWBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-1700, 0, 800, 500, 500, 100, Map.NWBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-800, 0, 1600, 200, 500, 100, Map.NWBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(-1400, 0, 1800, 600, 500, 500, Map.NWBUILDING_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    
    // NE park
    this.addBox(500, 0, 500, 500, 130, 100, Map.PARK_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(1200, 0, 500, 700, 130, 100, Map.PARK_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(500, 0, 600, 100, 130, 300, Map.PARK_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    this.addBox(500, 0, 1100, 100, 130, 1400, Map.PARK_WALLCOLOR, Map.DEFAULT_STROKECOLOR);
    
    // Trees!!!
    this.addTree(200, 0, 200, 100, 400, 300);
  }
  
  // Spawns a Tracker Mine. This method will try not to place it too close to the player.
  // If that is not possible after 15 tries, then screw it.
  public void spawnTrackerMine() {
    float position[] = this.TRACKER_MINE_SPAWN_POINTS[(int) random(this.TRACKER_MINE_SPAWN_POINTS.length)];
    int tries = 0;
    while (Maf.distanceL1(position, environment.getPlayer().getPosition()) < Map.MINIMUM_SPAWN_DISTANCE &&
           tries < 15) {
      position = this.TRACKER_MINE_SPAWN_POINTS[(int) random(this.TRACKER_MINE_SPAWN_POINTS.length)];
      tries++;
    }
    this.environment_.addPhysicalObject(new TrackerMine(position));
  }
  
  // Helper methods to facilitate map building.
  // The helper methods are sorted in order of decreasing complexity and reference up the object tree.
  
  public void addTree(float x, float y, float z, float trunkWidth, float trunkHeight, float bushSize) {
    // build the trunk
    this.addBox(x, y, z, trunkWidth, trunkHeight, trunkWidth, Map.TREE_TRUNKCOLOR, Map.DEFAULT_STROKECOLOR);
    // build the cube bush on top of the trunk
    if (this.environment_.getRadialMode()) {
      this.addBox(x + trunkWidth / 2, y - trunkHeight - bushSize / 2, z + trunkWidth / 2,
                  bushSize, Map.TREE_BUSHCOLOR, Map.DEFAULT_STROKECOLOR);
    } else {
      this.environment_.setRadialMode(true);
      this.addBox(x + trunkWidth / 2, y - trunkHeight - bushSize / 2, z + trunkWidth / 2,
                  bushSize, Map.TREE_BUSHCOLOR, Map.DEFAULT_STROKECOLOR);
      this.environment_.setRadialMode(false);
    }
  }
  
  public void addBox(float x, float y, float z,
                     float width_, float height_, float length_,
                     int sideColor, int strokeColor) {
    if (this.environment_.getRadialMode()) {
      this.environment_.addPhysicalObject(new Box(x, y, z,
                                          width_, height_, length_,
                                          sideColor, strokeColor));
    } else {
      this.environment_.addPhysicalObject(new Box(x + width_ / 2, y - height_ / 2, z + length_ / 2,
                                          width_, height_, length_,
                                          sideColor, strokeColor));
    }
  }
  
  public void addBox(float x, float y, float z,
                     float sideLength,
                     int sideColor, int strokeColor) {
    if (this.environment_.getRadialMode()) {
      this.environment_.addPhysicalObject(new Box(x, y, z, sideLength,
                                          sideColor, strokeColor));
    } else {
      float offset = sideLength / 2;
      this.environment_.addPhysicalObject(new Box(x + offset, y - offset, z + offset,
                                          sideLength,
                                          sideColor, strokeColor));
    }
  }
}
