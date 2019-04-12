package core.io.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.math.Vector2D;

/**
 * The {@code ImageResource} acts as a pointer to an image and allows for an image to be passed around and not having to be loaded until it has to.
 * TODO doc
 */
public class ImageResource {

    /**
     * 
     */
    private final File file;

    /**
     * 
     */
    private BufferedImage image;

    /**
     * 
     */
    private boolean loaded = false;

    /**
     * @param file
     */
    protected ImageResource(File file) {
        this.file = file;
    }

    /**
     * Create an image resource from the specified path. The path will be used to create a file object.
     * 
     * @param path the path
     * @return the image resource
     */
    public static ImageResource create(String path) {
        return new ImageResource(new File(path));
    }

    /**
     * Create an image resource from a file.
     * 
     * @param file the file
     * @return the image resource
     */
    public static ImageResource create(File file) {
        return new ImageResource(file);
    }

    /**
     * @return the loaded image
     */
    public BufferedImage loadImage() {
        if (isLoaded()) return image;

        try {
            image = ImageIO.read(getFile());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        loaded = image != null;

        return image;
    }

    /**
     * Loads the image if it hasn't been loaded yet
     * @return the bufferedImage
     */
    public BufferedImage getBufferedImage() {
        return isLoaded() ? image : loadImage();
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @return the loaded
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * @return
     */
    public int getWidth() {
        return getBufferedImage().getWidth();
    }

    /**
     * @return
     */
    public int getHeight() {
        return getBufferedImage().getHeight();
    }

    /**
     * @return
     */
    public Vector2D getSize() {
        return Vector2D.create(getWidth(), getHeight());
    }
    
}