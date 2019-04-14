package core.graphic;

import java.awt.Dimension;

import core.Game;
import core.math.Vector2D;
import core.obj.IGameObject;
import core.swing.SwingRenderer;

/**
 * A camera that can show things using a {@link SwingRenderer}.
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

    // TODO comment

    /**
     * 
     * @param pos
     * @return
     */
    public default Vector2D getDisplayCoordinates(Vector2D pos) {
        
        Dimension windowSize = Game.getWindowInstance().getSize();
        Vector2D windowCenter = new Vector2D(windowSize.getWidth() / 2, windowSize.getHeight() / 2);

        return new Vector2D(pos).sub(getPosition()).add(windowCenter);
    }

    /**
     * 
     * @param obj
     * @return
     */
    public default Vector2D getDisplayCoordinates(IGameObject<?> obj) {
        return getDisplayCoordinates(obj.getPosition());
    }

    /**
     * 
     * @return
     */
    public Vector2D getPosition();

}