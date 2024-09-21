package menus;

import java.awt.*;
import entity.Player;

public class DebugMenu {

    private Player player;
    private static int currentFPS;

    public DebugMenu(Player player) {
        this.player = player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void updateFPS(int fps) {
        currentFPS = fps;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(20, 20, 100, 100);
        g2.setColor(Color.white);
        g2.drawString("Debug Menu", 30, 30);

        // FPS
        g2.drawString("FPS: " + currentFPS, 30, 50);

        // World x and world y
        g2.drawString("(" + this.player.worldX + ", " + this.player.worldY + ")", 30, 70);

        // Tile x and y
        g2.drawString("(" + this.player.tileX + ", " + this.player.tileY + ")", 30, 90);
    }
}
