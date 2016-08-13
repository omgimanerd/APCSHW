/**
 * @author Alvin Lin (alvin.lin@stuypulse.com)
 * This class encapsulates the player and contains all
 * the methods to update the player. It takes care of movement,
 * looking, and actions from the player as well as the physics
 * and other miscellaneous data.
 */

import java.awt.Robot;
import java.util.Arrays;

public class Player extends PhysicalObject {
  
  // Constants that define player movement behavior
  private static final float PLAYER_WIDTH = 140.0;
  private static final float PLAYER_HEIGHT = 160.0;
  private static final float PLAYER_LENGTH = 140.0;
  private static final float MAX_HEALTH = 50.0;
  private static final float BASE_HORIZONTAL_SPEED = 15.0;
  private static final float JUMP_SPEED = 10.0;
  private final String[] TRACKABLE_KEYS_ARRAY = new String[] {
      "w", "s", "a", "d", " "};
  private final ArrayList<String> TRACKABLE_KEYS = new ArrayList<String>(Arrays.asList(TRACKABLE_KEYS_ARRAY));
  
  private float[] lookPosition_;
  private float horizontalLookAngle_;
  private float verticalLookAngle_;
  private float virtualMouseX_, virtualMouseY_;
  private ArrayList<String> keysPressed_;
  private Robot robot_;
  
  // Stats
  private float health_ = Player.MAX_HEALTH;
  private boolean isJumping_ = false;
  // The score is the number of mines destroyed.
  private int score_ = 0;
  
  // Settings
  private float sensitivity_ = 500.0;
  private boolean invertMouse_ = false;
  
  public Player() {
    super(0.0, -Player.PLAYER_HEIGHT / 2, 0.0,
          Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT, Player.PLAYER_LENGTH,
          0.0, 0.0, 0.0,
          0.0, PhysicalObject.DEFAULT_AY, 0.0);
          
    this.lookPosition_ = new float[] {0.0, 0.0, 0.0};
    this.horizontalLookAngle_ = 0.0;
    this.verticalLookAngle_ = 0.0;
    this.virtualMouseX_ = 0.0;
    this.virtualMouseY_ = height;
    this.keysPressed_ = new ArrayList<String>();
    try {
      this.robot_ = new Robot();
    } catch (Exception e) {}
  }
  
  // Update the position and velocities of the player according to
  // acceleration and collisions,
  // Updates the bounds as the object moves.
  public void updatePhysics(Environment environment) {
    this.velocity_.add(this.acceleration_);
    ArrayList<PhysicalObject> collidedObjects = this.getCollidedObjects(environment);
    if (collidedObjects.size() > 0) {
      for (int i = 0; i < collidedObjects.size(); ++i) {
        PhysicalObject collidedObject = collidedObjects.get(i);
        if (!collidedObject.isSolid()) {
          continue;
        }
        
        // Prevents the player from moving inside other objects horizonally.
        if (Maf.almostEqual(abs(collidedObject.getX() - this.getX()), (collidedObject.getWidth() / 2) + (this.getWidth() / 2))) {
          if (collidedObject.getX() > this.getX()) {
            this.velocity_.x = min(0, this.velocity_.x);
          } else {
            this.velocity_.x = max(0, this.velocity_.x);
          }
        }
        if (Maf.almostEqual(abs(collidedObject.getZ() - this.getZ()), (collidedObject.getLength() / 2) + (this.getLength() / 2))) {
          if (collidedObject.getZ() > this.getZ()) {
            this.velocity_.z = min(0, this.velocity_.z);
          } else {
            this.velocity_.z = max(0, this.velocity_.z);
          }
        }
        
        // Prevents the player from falling into another object. Minimizes at the floor level just in case.
        if (this.position_[1] >= Environment.FLOOR_LEVEL - this.getHeight() / 2) {
          if (this.velocity_.y > 14) {
            this.damage(this.velocity_.y / 10);
          }
          this.velocity_.y = min(0, this.velocity_.y);
          this.position_[1] = Environment.FLOOR_LEVEL - this.getHeight() / 2;
          this.isJumping_ = false;
        } else if (Maf.almostEqual(abs(collidedObject.getY() - this.getY()), (collidedObject.getHeight() / 2) + (this.getHeight() / 2))) {
          if (collidedObject.getY() > this.getY()) {
            this.velocity_.y = min(0, this.velocity_.y);
            this.position_[1] = collidedObject.getY() - (collidedObject.getHeight() / 2 + this.getHeight() / 2);
            this.isJumping_ = false;
          } else {
            this.velocity_.y = max(0, this.velocity_.y);
            this.position_[1] = collidedObject.getY() + (collidedObject.getHeight() / 2 + this.getHeight() / 2);
          }
        }
      }
    }
    
    this.position_[0] += this.velocity_.x;
    this.position_[1] += this.velocity_.y;
    this.position_[2] += this.velocity_.z;
  }
  
  // Updates where the player is looking depending on where the mouse is panned.
  public void updateLook(int screenX, int screenY) {
    // Tracks the virtual position of the mouse since the mouse
    // will be locked to the center of the screen.
    this.virtualMouseX_ += mouseX - width / 2;
    this.virtualMouseY_ += mouseY - height / 2;
    
    // Relocates the mouse to the center of the screen.
    // This function needs absolute coordinates relative to the
    // monitor top left corner. As a result, the x and y coords
    // of the window must be passed in.
    this.robot_.mouseMove(frame.getX() + screenX + round(width / 2),  
                          frame.getY() + screenY + round(height / 2));                      
                    
    // Keeps the virtual position within the range of the window.
    if (this.virtualMouseX_ < 0) {
      this.virtualMouseX_ += width;
    }
    this.virtualMouseX_ %= width;
    this.virtualMouseY_ = min(max(this.virtualMouseY_, 0), height);
    
    // Maps virtual 2D mouse position to two angles that can describe a point
    // on a 3D sphere that we can set the mouse to look at.
    // If desired, the mouse settings can be inverted.
    if (this.invertMouse_) {
      this.horizontalLookAngle_ = map(this.virtualMouseX_, width, 0, 0, 2 * PI);
      this.verticalLookAngle_ = map(this.virtualMouseY_, height, 0, PI - PI / 18, 0 + PI / 18);
    } else {
      this.horizontalLookAngle_ = map(this.virtualMouseX_, width, 0, 2 * PI, 0);
      this.verticalLookAngle_ = map(this.virtualMouseY_, height, 0, 0 + PI / 18, PI - PI / 18);
    }
    
    // Updates the player's lookPosition_ by mapping the look angle to the imaginary
    // sphere. The radius of the sphere is represented by the "mouse sensitivity"
    // since it is inversely proportional to the sensitivity of the mouse.
    // http://mathinsight.org/spherical_coordinates
    this.lookPosition_[0] = this.getX() +
        this.sensitivity_ * sin(this.verticalLookAngle_) * cos(this.horizontalLookAngle_);
    this.lookPosition_[1] = this.getY() +
        this.sensitivity_ * cos(this.verticalLookAngle_); 
    this.lookPosition_[2] = this.getZ() +
        this.sensitivity_ * sin(this.verticalLookAngle_) * sin(this.horizontalLookAngle_);
  
    // Sets the calculated positions and lookPositions to the camera.
    // The player's actual position will be on the ground where it is supposed to be for
    // the purpose of calculating gravity and collisions, but the camera eye will be
    // placed at the player's height above the position.
    camera(this.getX(), this.getY() - Player.PLAYER_HEIGHT / 2, this.getZ(),
           this.lookPosition_[0], this.lookPosition_[1], this.lookPosition_[2],
           0.0, 1.0, 0.0);
  }
  
  // Tracks the player's currently held down keys.
  public void updateOnKeyPressed(char eventKeyChar) {
    String eventKey = Character.toString(eventKeyChar).toLowerCase();
    
    // Tracks what keys are currently being held down since keyPressed() only tracks
    // them one at a time.
    if (this.keysPressed_.indexOf(eventKey) == -1 && this.TRACKABLE_KEYS.indexOf(eventKey) != -1) {
      this.keysPressed_.add(eventKey);
    }
  }
  
  // Removes tracked key presses once they are released.
  public void updateOnKeyReleased(char eventKeyChar) {
    String eventKey = Character.toString(eventKeyChar).toLowerCase();
    
    // Once the key is released, it will be removed from the tracking array.
    if (this.keysPressed_.indexOf(eventKey) != -1) {
      this.keysPressed_.remove(eventKey);
    }
  }
  
  public void updateMovement() {
    // Based on what keys are being pressed, the player will move at some angle
    // relative to THEIR CURRENT LOOKING ANGLE.
    // The current facing angle is inverted from the actual look angle due to
    // the look calculation mathematics.
    float moveAngleRelativeToLookAngle = 0.0;
    float horizontalSpeed = 0.0;
    float verticalSpeed = 0.0;
    
    if (this.keysPressed_.indexOf("w") != -1 ||
        this.keysPressed_.indexOf("a") != -1 ||
        this.keysPressed_.indexOf("s") != -1 ||
        this.keysPressed_.indexOf("d") != -1) {
      horizontalSpeed = Player.BASE_HORIZONTAL_SPEED;
      Sound.playNonOverlap("footstep.mp3");
    }
    
    if (this.keysPressed_.indexOf("d") != -1 && this.keysPressed_.indexOf("w") != -1) {
      // NE relative to facing angle.
      moveAngleRelativeToLookAngle = PI / 4;
    } else if (this.keysPressed_.indexOf("w") != -1 && this.keysPressed_.indexOf("a") != -1) {
      // NW relative to facing angle.
      moveAngleRelativeToLookAngle = 3 * PI / 4;
    } else if (this.keysPressed_.indexOf("a") != -1 && this.keysPressed_.indexOf("s") != -1) {
      // SW relative to facing angle.
      moveAngleRelativeToLookAngle = 5 * PI / 4;
    } else if (this.keysPressed_.indexOf("s") != -1 && this.keysPressed_.indexOf("d") != -1) {
      // SE relative to facing angle.
      moveAngleRelativeToLookAngle = 7 * PI / 4;
    } else if (this.keysPressed_.indexOf("w") != -1) {
      // N relative to facing angle.
      moveAngleRelativeToLookAngle = HALF_PI;
    } else if (this.keysPressed_.indexOf("a") != -1) {
      // W relative to facing angle.
      moveAngleRelativeToLookAngle = PI;
    } else if (this.keysPressed_.indexOf("s") != -1) {
      // S relative to facing angle.
      moveAngleRelativeToLookAngle = 3 * HALF_PI;
    } else if (this.keysPressed_.indexOf("d") != -1) {
      // E relative to facing angle.
      moveAngleRelativeToLookAngle = 0.0;
    }
    this.velocity_.x = horizontalSpeed * sin(moveAngleRelativeToLookAngle - this.horizontalLookAngle_);
    this.velocity_.z = horizontalSpeed * cos(moveAngleRelativeToLookAngle - this.horizontalLookAngle_);
    
    if (this.keysPressed_.indexOf(" ") != -1) {
      if (!this.isJumping_) {
        this.velocity_.y = -Player.JUMP_SPEED;
        this.isJumping_ = true;
      }
    }
  }
  
  // Fires a bullet when the mouse is clicked.
  public void updateOnMousePressed(Environment environment) {
    PVector bulletVelocity = new PVector(sin(this.verticalLookAngle_) * cos(this.horizontalLookAngle_),
                                         cos(this.verticalLookAngle_),
                                         sin(this.verticalLookAngle_) * sin(this.horizontalLookAngle_));
    bulletVelocity.mult(Bullet.BULLET_SPEED);
    
    environment.addPhysicalObject(new Bullet(this.getX(), this.getY() - Player.PLAYER_HEIGHT / 2, this.getZ(),
                                             bulletVelocity.x, bulletVelocity.y, bulletVelocity.z, this));
    Sound.play("shot.mp3");
  }
  
  public boolean isDead() {
    return this.health_ <= 0;
  }
  
  public float getHealth() {
    return this.health_;
  }
  
  public void setHealth(float health) {
    this.health_ = health;
  }
  
  public void damage(float health) {
    this.health_ = max(this.health_ - health, 0);
    Sound.play("grunt.mp3");
  }
  
  public void heal(float health) {
    this.health_ = min(this.health_ + health, Player.MAX_HEALTH);
  }
  
  public int getScore() {
    return this.score_;
  }
  
  public void setScore(int score) {
    this.score_ = score;
  }
  
  public void incrementScore() {
    this.score_++;
  }
  
  public boolean getInvertMouse() {
    return this.invertMouse_;
  }
  
  public void invertMouse() {
    this.invertMouse_ = !this.invertMouse_;
  }
  
  public void reset() {
    this.position_[0] = 0.0;
    this.position_[1] = -Player.PLAYER_HEIGHT / 2;
    this.position_[2] = 0.0;
    this.velocity_.x = 0.0;
    this.velocity_.y = 0.0;
    this.velocity_.z = 0.0;
    this.score_ = 0;
    this.heal(Player.MAX_HEALTH);
  }
}
