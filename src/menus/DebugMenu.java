package menus;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

import entity.Player;

public class DebugMenu {

    private Player player;
    private static int currentFPS;
    private static final Color backgroundColor = Color.BLACK;
    private static final Color textColor = Color.white;

    public DebugMenu(Player player) {
        this.player = player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void updateFPS(int fps) {
        currentFPS = fps;
    }

    /*
    Returns an int of the height of the debug rectangle.
     */
    public int drawDebugText(Graphics2D g2, String attributeName, int x, int y, int... attributes)
    {
        String debugText = attributeName + ": ";
        if (attributes.length > 1) {
            debugText += "(";
            for (int i = 0; i < attributes.length - 1; i++) {
                debugText += attributes[i] + ", ";
            }
            debugText += attributes[attributes.length - 1] + ")";
        } else {
            debugText += attributes[0];
        }

        return drawDebugText(g2, debugText, x, y);
    }

    /*
    Draws debug text.
     */
    public int drawDebugText(Graphics2D g2, String debugText, int x, int y)
    {
        Rectangle2D textBounds = g2.getFontMetrics().getStringBounds(debugText, g2);
        g2.setColor(backgroundColor);
        g2.fillRect(
                x,
                y - (int)(textBounds.getHeight()),
                (int) textBounds.getWidth(),
                (int) (textBounds.getHeight()*1.5)
        );
        g2.setColor(textColor);
        g2.drawString(debugText, x, y);
        return (int)(textBounds.getHeight()*1.5);
    }

    public void drawColoredTile(Graphics2D g2, int tileX, int tileY, Color color)
    {
        Color currentColor = g2.getColor();
        g2.setColor(color);
        g2.fillRect((tileX/48)*48, (tileY/48)*48, 48, 48);
        g2.setColor(currentColor);
    }

    public void draw(Graphics2D g2)
    {
        int currentDrawY = 50;

        g2.setColor(backgroundColor);
        g2.fillRect(20, 20, 100, 10);
        g2.setColor(textColor);
        g2.setFont(g2.getFont().deriveFont(12F));
        g2.drawString("Debug Menu", 30, 30);

        currentDrawY += drawDebugText(g2, "FPS", 30, currentDrawY, currentFPS);
        currentDrawY += drawDebugText(g2, "World Position", 30, currentDrawY, this.player.worldX, this.player.worldY);
        currentDrawY += drawDebugText(g2, "Tile Position", 30, currentDrawY, this.player.tileX, this.player.tileY);


    }
}
