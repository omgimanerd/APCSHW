/**
 * @author Elias Saric (esaric)
 * This class is somewhat of a utility class and handles
 * the drawing of the HUD and user interface on top of the
 * game.
 */
 
public class UI {
  
  public static final int CROSSHAIR_HALFLENGTH = 50;
  public static final int CROSSHAIR_OFFSET = 80;
  public static final int HEALTHBAR_COLOR = #CC0000;
  
  // This method calls all the internal methods and is called in FPS.pde
  // to update the UI.
  public void updateUI(Player player) {
    updateCrosshair();
    updateHealthBar(player);
  }
  
  // This method draws the crosshairs in the center of the screen.
  public void updateCrosshair() {
    int halfWidth = width / 2;
    int halfHeight = height / 2 - CROSSHAIR_OFFSET;
    for (int i = halfWidth - UI.CROSSHAIR_HALFLENGTH; i < halfWidth + UI.CROSSHAIR_HALFLENGTH; i++) {
      set(i, halfHeight, #CCCCCC);
    }
    for (int i = halfHeight - UI.CROSSHAIR_HALFLENGTH; i < halfHeight + UI.CROSSHAIR_HALFLENGTH; i++) {
      set(halfWidth, i, #CCCCCC);
    }
  }
  
  // This method draws the player's health on the top left corner of the screen.
  public void updateHealthBar(Player player) {
    // Every 4 pixels represents 1 point of health.
    for (int j = 0; j < player.getHealth() * 4; j++) {
      for (int i = height / 32; i < height / 32 + 17; i++) {
        set(width / 32 + j, i, UI.HEALTHBAR_COLOR);
      }
    }
  }
}

