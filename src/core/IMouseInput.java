package core;

import java.awt.event.MouseEvent;

import core.math.Vector2D;

/**
 * IMouseInput
 */
public interface IMouseInput {

    public void mouseReleased(MouseEvent e);

    public void mousePressed(MouseEvent e);

    public void mouseDragged(MouseEvent e);

    public void mouseMoved(MouseEvent e);

    public Vector2D getDelta();

    public Vector2D getPosition();
    
}