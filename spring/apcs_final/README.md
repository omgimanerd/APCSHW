APCS FINAL PROJECT
Team Flying Rhizomes
=======
3D Plane Flight Game by William Yang and Alvin Lin

<b>Mechanics:</b>
- Physics:
  - Plane control in terms of yaw, pitch, and roll.
  - Plane should emulate real-world physics and be able to land.
  - Physical objects interact in terms of crashing and exploding.
- Actions:
  - Player controls the plane's pitch and roll using the mouse.
  - Player can fire bullets and missiles by clicking.
- Game:
  - Player can fire bullets and missiles at enemy plane and structures.
  - Enemy AI will attempt to dodge missiles and retaliate smartly.

<b>How to Play:</b>
- Use W to increase your airspeed, and S to decrease your airspeed.
- Your mouse controls your plane's direction. Bring the mouse lower or
  higher to control the pitch of the plane, bring the mouse left and right
  to control the roll and yaw of the plane.
- Click to fire bullets, and press X to fire missiles. If you can train your
  sights on a plane for long enough, your sights will turn green, and the next
  missile you fire will lock on. Lock on missiles will try and hit the plane,
  but can still miss.

<b>Dependencies:</b>
- OCD: Obsessive Camera Direction

<b>Demo Versions:</b>
- <a href='https://github.com/omgimanerd/apcs_final/releases/tag/alpha1.0'>
  Alpha 1.0
  </a>
- <a href='https://github.com/omgimanerd/apcs_final/releases/tag/alpha1.1'>
  Alpha 1.1
  </a>
- <a href='https://github.com/omgimanerd/apcs_final/releases/tag/beta1.0'>
  Beta 1.0
  </a>
- <a href='https://github.com/omgimanerd/apcs_final/releases/tag/beta2.0'>
  Beta 2.0
  </a>

<br />
<b>Changelog:</b>
  - 5/21/2015:
    - Added first-term final project for code recycling.
  - 5/26/2015:
    - Finished basic player looking. The player's mouse controlls pitch
    and yaw as of now, which control the direction the plane will face.
    Implementing movement will require some conceptualization of the
    physics. Look bounding must be done as well.
  - 5/27/2015:
    - Created CollisionSphere class - implementation basic as of now; creates a
    sphere whose radius = the largest side/2 and whose center is at the center
    as the PhysicalObject's. There will be a lot of extra space, which we'll
    fix later by shrinking the collison spheres and placing them within the
    PhysicalObject.
  - 5/28/2015:
    - Implemented camera roll with OCD. The roll() function changes the camera's
    roll by the given amount, but cannot set the roll to a specific position,
    making the implementation super annoying. In order to implement it, you must
    find the amount to roll by to reach the desired position in order to pass
    it into the roll() function.
  - 5/29/2015:
    - Implemented some basic velocity physics by moving with the yaw. Turns out
    camera clip is modifiable, and it is kind of like a render distance. Now
    objects don't disappear if they're too far away. Also fixed a bug where the
    box would disappear sporadically.
  - 5/30/2015:
    - Celebrated birthday.
    - Created Bullet classes and improved physics. Collision almost done.
    - Messed around with some UI stuff, but 2D drawing on top of 3D canvas is
    incompatible with damkjer's OCD camera library.
  - 6/4/2015:
    - Implemented more realistic flying and landing mechanics. Plane will only
    when certain orientation parameters are met.
  - 6/5/2015:
    - Messed around some more with UI stuff, we will not be implementing it
    since it is incompatible with damkjer's OCD camera library.
  - 6/7/2015:
    - Removed the CollisionSphere class and simplified collision so that each
    object merely has a single spherical hitbox. We average the object's width,
    height, and length to calculate the sphere radius. We will work under the
    assumption that all objects in our world will be relatively square/cuboid.
    The default collision detection is the simple distance and radial sum
    comparison.
  - 6/9/2015:
    - Implemented better collision in the Bullet class using a line-sphere
    intersection algorithm. Planning to make destroyable stuff now.
  - 6/10/2015:
    - Made destroyable boxes in the environment. Improved player plane control
    a bit and created plane class with rendering. Need to create plane AI now.
  - 6/11/2015:
    - Combined OCD camera class (full functionality) with 2D overlay :D
    - Added enemy plane velocity movement and fixed some drawing issues that
    arose with that. Added pitching part of plane AI tracking.
  - 6/12/2015:
    - Created smarter plane AI and made it quasi-realistic and awesome.
    - Created missiles with sexiness.
    - Beginning of UI code, fixed the bug where 3D objects clipped away the HUD
    by using hint(DISABLE_DEPTH_TEST);
  - 6/13/2015:
    - Finished missiles and tracking yay. It looks awesome.
  - 6/14/2015:
    - Made the UI, improved some ballistics.
    - Added all the sound files that we collected over the past week. Recycled Sound.pde
    from FPS.
