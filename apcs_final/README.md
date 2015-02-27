APCS FINAL PROJECT
=======
3D FPS Game by Alvin Lin and Elias Saric

<b>Mechanics:</b>
- Physics
  - Gravity (done)
  - Ballistics (done)
  - Collision detection without complex 3D models (done)
  - Map (done)
- Actions
  - move (done)
  - shoot (done)
  - sprint (done)
  - switch weapon (removed)
- Game
  - Health (done)
  - Stamina (removed)
  - HUD (done)
  - User interface and start screen (done)
  - Gun type and/or bullet type (removed)
  - Enemies (done)
  - Environment (done)

<br />
<b>Changelog:</b>
- Winter break:
  - Implemented base classes and template for 3D looking
  - Implemented physics classes and handling
- 1/6/2015:
  - Fixed player looking with vector component formulas
  - Jumping is bugged, space can be held down to achieve infinite jump
- 1/7/2015:
  - Jumping still bugged
  - COLLISION DETECTION IMPLEMENTED:
    All physical objects extend the PhysicalObject class and are given a
    position and size. Their bounding boxes are defined by their size and
    expand radially from their position, that is, the position represents
    the center of the object's bounding box. All physical objects have a cubic
    or prismic axis-aligned bounding box, which allows us to use the
    axis-aligned bounding box (AABB) method to check for collisions between
    two objects. The AABB method checks if the all three axes of the two
    objects intersect in order to determine if the two objects have collided.
  - Basis of HUD created (stamina bar)
- 1/8/2015:
  - Jumping still bugged
  - Collision detection works, but movement limitation based on collision
    needs fixing
- 1/9/2015:
  - Basic collision detection works on the xz-plane, sliding off walls
    is a little whack, will improve later
  - Jumping still bugged
  - Map is ready to be built
- 1/12/2015:
  - Added bullets and shooting
  - Built basis of map and improved collision
  - Jumping still bugged
- 1/13/2015:
  - Perfected collision in x-y-z direction
  - Bullets behave correctly when colliding
  - Jumping still bugged
- 1/14/2015:
  - Added tracker mines
  - Y-collision upward is bugged. Jumping on a physical object bugged
  - Jumping still bugged
- 1/15/2015:
  - Due to the way the physics engine works, if the bullet velocity is too
    high, then the bullet will skip past the mine in a single frame
  - Fixed collisions with tracker mines
  - Fixed bug that allows infinite jump
  - LONG EXPLANATION OF CHANGES TO MOVING:
    In order to make moving smooth, an ArrayList will track which buttons
    are being held down so that we can strafe diagonally and resume normal
    movement when not moving diagonally. The built-in keyPressed() method
    can only track single key presses, so it works very badly for player
    movement. As a tradeoff, sprinting was removed.
  - SHOOTING SCALING BUG:
    Shooting has a weird bug in which the direction that the bullet accelerates
    towards is somehow different from the player's looking direction even though
    it is derived from the player's look angle. This is a scaling issue that
    causes the bullet to not fire towards the middle of the crosshair. This can
    be evident if one fires while facing up and facing down. The bullets will
    fire above the crosshair if the player is facing the sky and it will fire
    below the crosshair if the player is facing the ground.
- 1/17/2015:
  - Fixed bullet velocity bug by slowing down bullet velocity
- 1/18/2015:
  - Added explosions and explosion particles
  - Cleaned up code
  - Added sound to game from Minim library
- 1/19/2015:
  - Fixed 2D on 3D overlay bug with menus and text
  - Finished up menu functionality and visuals
  - Cleaned up some small bugs
- 1/20/2014:
  - BULLET SPEED AND COLLISION FIX:
    Every update, the bullet moves forward by its velocity, so it will skip
    through space every frame. As a result, collisions with the
    tracker mines did not work properly when the bullet velocity was greater
    than the diameter of the tracker mine. This was solved by checking the
    intermediate points along the bullet's trajectory for collision.
  - Implemented explosions blasting away the player
  - Improved map and spawning
  - Cleaned up code
  - BUG REPORT:
    The only bug we are currently aware is the cursor scaling issue that causes
    bullets to not fire towards the crosshair.
  - Added healthpacks and an extra sound effect.
  - Sound is now a static class so that it can be sounds can be played without
    a Sound object instantiated.
