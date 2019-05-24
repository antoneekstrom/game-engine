package core.obj;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import core.Game;
import core.IGraphic;
import core.IRenderer;
import core.AbstractLogic;
import core.math.Box;
import core.math.Vector2D;

/**
 * The {@link IGameObject} is the building block of a {@link Game}. A game object represents something that exists in the game world. Though, it can also be something that is not visible in the world and is only there to listen to user input or update some kind of logic.
 * 
 * <p>The {@code IGameObject} is meant to be added to a {@link AbstractLogic}. There it will automatically by updated by the logic and receive user input and other kinds of events.
 */
public interface IGameObject <R extends IRenderer<R>> {

    /**
     * The update method is called frequently by {@link AbstractLogic}.
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
     * Remove an object. This method is supposed to check if this is the object that is being requested to be removed.
     * If this is a collection of objects (eg. {@link ObjectStorage}) this method should check if the object to be removed is a child of this one.
     * If the object is a child, it should be removed.
     * 
     * @param objects the collection which the object is to be removed from
     * @param obj the object to be removed
     * @param index the index of the object
     * @return if the object was removed
     */
    public boolean remove(ArrayList<IGameObject<R>> objects, IGameObject<R> obj, int index);

    /**
     * Remove this object from its parent.
     */
    public void remove();

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