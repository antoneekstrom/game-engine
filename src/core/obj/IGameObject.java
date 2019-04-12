package core.obj;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import core.Game;
import core.IGraphic;
import core.IRenderer;
import core.Logic;
import core.math.Box;
import core.math.Vector2D;

/**
 * The {@link IGameObject} is the building block of a {@link Game}. A game object represents something that exists in the game world. Though, it can also be something that is not visible in the world and is only there to listen to user input or update some kind of logic.
 * 
 * <p>The {@code IGameObject} is meant to be added to a {@link Logic}. There it will automatically by updated by the logic and receive user input and other kinds of events.
 */
public interface IGameObject <R extends IRenderer<R>> {

    /**
     * The update method is called frequently by {@link Logic}.
     */
    public void update();

    /**
     * Get the graphic that this object is supposed to display when being rendered.
     * 
     * @return the graphic object
     */
    public IGraphic<R> getGraphic();

    /**
     * Convenience method for checking if the graphic exists.
     * @return if it exists
     */
    public default boolean hasGraphic() {
        return getGraphic() != null;
    }

    // Mouse functions, self explanatory? I can't be bothered to doc

    public default void mouseMoved(MouseEvent e) {}

    public default void mouseDragged(MouseEvent e) {}

    public default void mouseReleased(MouseEvent e) {}

    public default void mousePressed(MouseEvent e) {}

    public default void keyPressed(KeyEvent e) {}

    public default void keyReleased(KeyEvent e) {}


    /**
     * Get the dimensions and position of this object.
     * 
     * @return the box
     */
    public Box getBox();

    /**
     * Get the position. This is a convenience method and gets the same position vector that is contained within {@link #getBox()}.
     * 
     * @return the position
     */
    public default Vector2D getPosition() {
        return getBox().getPosition();
    }
    
}