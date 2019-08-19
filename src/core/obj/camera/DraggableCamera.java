package core.obj.camera;

import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import core.IRenderer;
import core.graphic.ICamera;

/**
 * A camera that can be dragged.
 * 
 * @see ICamera
 * @see MovableCamera
 */
public class DraggableCamera <R extends IRenderer<R>> extends CameraObject<R> {

    private boolean rightClick = false;

    public DraggableCamera() {
        super();
    }

    public DraggableCamera(boolean rightClick) {
        super();
        this.rightClick = rightClick;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!rightClick || (rightClick && SwingUtilities.isRightMouseButton(e)))
            translate(getMouse().getDelta().negate());
    }
    
}