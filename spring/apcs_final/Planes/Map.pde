/**
 * Class that generates the game map.
 */
public class Map {
  
  private ArrayList<PhysicalObject> map_;
  
  public Map() {
    map_ = new ArrayList<PhysicalObject>();
    init();
  }
  
  public void init() {
    // The floor is set to unsolid because it is not a cuboid and it's collision
    // hitbox will intersect everything.
    // By default, Boxes are not destroyable and solid.
    
    this.map_.add(new Box(
        0, 10, 0,
        0, 0, 0,
        100000, 20, 10000,
        #3C2F12, #3C2F12).setUnsolid());
        
    //Sector 1.0
    this.map_.add(new Box(
        80, 0, 0,
        0, 0, 0,
        40, 100, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        120, 0, 0,
        0, 0, 0,
        40, 200, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        160, 0, 0,
        0, 0, 0,
        40, 100, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        200, 0, 0,
        0, 0, 0,
        40, 200, 40,
        #CCCCCC, #000000));
        
    //Sector 1.1
    this.map_.add(new Box(
        280, 0, 0,
        0, 0, 0,
        40, 200, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        320, 0, 0,
        0, 0, 0,
        40, 300, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        360, 0, 0,
        0, 0, 0,
        40, 250, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        400, 0, 0,
        0, 0, 0,
        40, 100, 40,
        #CCCCCC, #000000));
        
    //Sector 2.0 
    this.map_.add(new Box(
        80, 0, 800,
        0, 0, 0,
        40, 200, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        120, 0, 800,
        0, 0, 0,
        40, 150, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        160, 0, 80,
        0, 0, 0,
        40, 100, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        200, 0, 80,
        0, 0, 0,
        40, 300, 40,
        #CCCCCC, #000000));
        
    //Sector 2.1
    this.map_.add(new Box(
        280, 0, 80,
        0, 0, 0,
        40, 300, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        320, 0, 80,
        0, 0, 0,
        40, 200, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        360, 0, 80,
        0, 0, 0,
        40, 100, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        400, 0, 80,
        0, 0, 0,
        40, 150, 40,
        #CCCCCC, #000000));
    
    //Sector 3.0
    this.map_.add(new Box(
        80, 0, 160,
        0, 0, 0,
        40, 100, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        120, 0, 160,
        0, 0, 0,
        40, 200, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        160, 0, 160,
        0, 0, 0,
        40, 100, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        200, 0, 160,
        0, 0, 0,
        40, 200, 40,
        #CCCCCC, #000000));
        
    //Sector 1.1
    this.map_.add(new Box(
        280, 0, 160,
        0, 0, 0,
        40, 200, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        320, 0, 160,
        0, 0, 0,
        40, 300, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        360, 0, 160,
        0, 0, 0,
        40, 250, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        400, 0, 160,
        0, 0, 0,
        40, 100, 40,
        #CCCCCC, #000000));
        
    //Sector 2.0 
    this.map_.add(new Box(
        80, 0, 240,
        0, 0, 0,
        40, 200, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        120, 0, 240,
        0, 0, 0,
        40, 150, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        160, 0, 240,
        0, 0, 0,
        40, 100, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        200, 0, 240,
        0, 0, 0,
        40, 300, 40,
        #CCCCCC, #000000));
        
    //Sector 2.1
    this.map_.add(new Box(
        280, 0, 240,
        0, 0, 0,
        40, 300, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        320, 0, 240,
        0, 0, 0,
        40, 200, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        360, 0, 240,
        0, 0, 0,
        40, 100, 40,
        #CCCCCC, #000000));
    this.map_.add(new Box(
        400, 0, 240,
        0, 0, 0,
        40, 150, 40,
        #CCCCCC, #000000));
    
        
  }
  
  public ArrayList<PhysicalObject> getMap() {
    return this.map_;
  }
}
