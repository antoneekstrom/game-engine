package core.graphic;

import java.awt.Dimension;

import core.Game;
import core.math.Vector2D;
import core.obj.IGameObject;
import core.swing.SwingRenderer;

/**
 * A camera that can show things using a {@link SwingRenderer}.
 * The camera renders other game objects relative to itself. This allows
 * for moving the camera and making it appear as the game window has moved position.
 */
public interface ICamera {

    /**
     * The inverse of {@link #getDisplayCoordinates(Vector2D)}.
     * 
     * @param pos the position
     * @return the world coordinates
     */
    public default Vector2D getWorldCoordinates(Vector2D pos) {

        Dimension windowSize = Game.getWindowInstance().getSize();
        Vector2D windowCenter = new Vector2D(windowSize.getWidth() / 2, windowSize.getHeight() / 2);

        return new Vector2D(pos).add(getPosition()).sub(windowCenter);
    }

    /**
     * Receive a coordinate and return where those coordinates should be drawn on the window
     * relative to this camera.
     * @param pos the position
     * @return the relative position
     */
    public default Vector2D getDisplayCoordinates(Vector2D pos) {
        
        Dimension windowSize = Game.getWindowInstance().getSize();
        Vector2D windowCenter = new Vector2D(windowSize.getWidth() / 2, windowSize.getHeight() / 2);

        return new Vector2D(pos).sub(getPosition()).add(windowCenter);
    }

    /**
     * The same as {@link #getDisplayCoordinates(Vector2D)} but it uses the position of an {@link IGameObject}.
     * @see #getDisplayCoordinates(Vector2D)
     * @param obj the object
     * @return it's coordinates relative to the camera
     */
    public default Vector2D getDisplayCoordinates(IGameObject<?> obj) {
        return getDisplayCoordinates(obj.getPosition());
    }

    /**
     * The position of this camera object in the world.
     * @return the position
     */
    public Vector2D getPosition();

}