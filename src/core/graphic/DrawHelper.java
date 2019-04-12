package core.graphic;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import core.io.resources.ImageResource;
import core.math.Box;
import core.math.Vector2D;

/**
 * DrawHelper
 */
public class DrawHelper {

    public static void drawRectangle(Rectangle r, Graphics2D g) {
        g.fillRect(r.x, r.y, r.width, r.height);
    }

    public static void drawBox(Box b, Graphics2D g) {
        g.fillRect((int) b.getX(), (int) b.getY(), (int) b.getWidth(), (int) b.getHeight());
    }

    public static void drawText(String text, Vector2D pos, Graphics2D g) {
        g.drawString(text, (int) pos.getX(), (int) pos.getY());
    }

    public static void drawText(String[] text, Vector2D pos, Graphics2D g) {

        double height = g.getFontMetrics().getHeight();
        pos.addY(-height);

        for (String line : text) {
            drawText(line, pos.addY(height), g);
        }
    }

    public static void drawShape(Shape shape, Graphics2D g) {
        g.fill(shape);
    }

    public static void drawImage(ImageResource imgResource, Box b, Graphics2D g) {

        if (imgResource == null) return;

        BufferedImage img = imgResource.getBufferedImage();

        if (img != null)
        g.drawImage(img, (int) b.getX(), (int) b.getY(), (int) b.getWidth(), (int) b.getHeight(), null);
    }
    
}