/**
 * DROP THE BASS
 */

public static class Sound {
  
  private static Minim MINIM;
  private static String SOUND_DIRECTORY = "sound/";
  private static String[] SOUND_NAMES = new String[] {
    "bullet.mp3",
    "bullet_ricochet.mp3",
    "engine_flying.mp3",
    "explosion.mp3",
    "missile_launch.mp3",
    "missile_locked.mp3",
    "missile_locking.mp3",
    "warning.mp3"
  };
   
  private static HashMap sounds_;
  
  /**
    * Initializes all the sound files for playback.
    * @param minim The Minim object instantiated in Planes.pde 
    */
  public static void loadSounds(Minim minim) {
    Sound.MINIM = minim;
    Sound.sounds_ = new HashMap();
    for(int i = 0; i < SOUND_NAMES.length; ++i){
      AudioPlayer sound = Sound.MINIM.loadFile(SOUND_DIRECTORY +
                                                SOUND_NAMES[i]);
      Sound.sounds_.put(SOUND_NAMES[i], sound);
    }
  }
  
  public static AudioPlayer verifySound(String soundName) {
    if (!Sound.sounds_.containsKey(soundName)) {
      throw new Error("Unknown sound");
    } else {
      return (AudioPlayer) Sound.sounds_.get(soundName);
    }
  }
  
  public static void pause(String soundName) {
    AudioPlayer sound = Sound.verifySound(soundName);
    sound.pause();
  }
  
  public static void play(String soundName) {
    AudioPlayer sound = Sound.verifySound(soundName);
    sound.rewind();
    sound.play();
  }
  
  public static void playNonOverlap(String soundName) {
    AudioPlayer sound = Sound.verifySound(soundName);
    if (!sound.isPlaying()) {
      sound.rewind();
      sound.play();
    }
  }
  
  public static void stop(String soundName) {
    AudioPlayer sound = Sound.verifySound(soundName);
    sound.pause();
    sound.rewind();
  }
  
  public static void rewind(String soundName) {
    AudioPlayer sound = Sound.verifySound(soundName);
    sound.rewind();
  }
}

