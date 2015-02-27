/**
 * @author Alvin Lin (alvin.lin@stuypulse.com)
 * This class encapsulates all the methods to play sounds.
 * Credit to James Chin (jamesbchin@gmail.com) for his Texas-accent-sounding
 * voice (removed).
 */

public static class Sound {
  
  private static Minim minim_;
  private static String[] soundNames_ = new String[] {
      "bop.mp3", "explosion.mp3", "footstep.mp3", "grunt.mp3", "shot.mp3",
      "theme.mp3"
  };
  private static HashMap sounds_;
  
  /**
   * Initializes all the sound files for playback.
   * @param minim The Minim object instantiated in FPS.pde
   */
  public static void loadSounds(Minim minim) {
    Sound.minim_ = minim;
    Sound.sounds_ = new HashMap();
    for (int i = 0; i < soundNames_.length; ++i) {
      AudioPlayer sound = Sound.minim_.loadFile(soundNames_[i]);
      Sound.sounds_.put(soundNames_[i], sound);
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
  
  public static void rewind(String soundName) {
    AudioPlayer sound = Sound.verifySound(soundName);
    sound.rewind();
  }
}
