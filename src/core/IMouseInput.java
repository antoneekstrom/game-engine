package core;

import java.awt.event.MouseEvent;
import java.util.function.Consumer;

import core.math.Vector2D;

/**
 * IMouseInput
 */
public interface IMouseInput {

    /**
     * 
     * @param listener
     * @return
     */
    public long connect(Consumer<MouseEvent> listener);

    /**
     * 
     * @param id
     */
    public void disconnect(long id);

    public void mouseReleased(MouseEvent e);

    public void mousePressed(MouseEvent e);

    public void mouseDragged(MouseEvent e);

    public void mouseMoved(MouseEvent e);

    public Vector2D getDelta();

    public Vector2D getPosition();
    
}