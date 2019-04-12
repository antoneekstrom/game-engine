package core.swing;

import java.awt.Graphics2D;

import core.graphic.BoxGraphic;
import core.graphic.DrawHelper;
import core.io.resources.ImageResource;
import core.math.Box;
import core.math.Vector2D;

/**
 * A {@link SwingGraphic} that displays an image.
 * 
 * @see SwingRenderer
 * @see BoxGraphic
 */
public class Sprite extends BoxGraphic {

    /**
     * The image the sprite is using.
     */
    private ImageResource image;

    /**
     * Create a sprite.
     * 
     * @param image the image to use
     */
    public Sprite(ImageResource image) {
        super(new Box());
        setImage(image);
        resize();
    }

    @Override
    public void render(Graphics2D g, SwingRenderer renderer, Vector2D screenPos) {
        DrawHelper.drawImage(getImage(), getBox(screenPos), g);
    }

    /**
     * Set the size of the box to be the same as the resolution of the image.
     */
    public void resize() {
        getBox().setSize(image.getSize());
    }

    /**
     * @return the image
     */
    public ImageResource getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(ImageResource image) {
        this.image = image;
    }
    
}