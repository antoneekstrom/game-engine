package core.obj.camera;

import java.awt.event.KeyEvent;

import core.IRenderer;
import core.graphic.ICamera;
import core.math.Vector2D;
import core.obj.camera.CameraObject;

/**
 * A camera that can be moved with the WASD keys.
 * 
 * @see ICamera
 * @see DraggableCamera
 */
public class MovableCamera <R extends IRenderer<R>> extends CameraObject<R> {

    private String name;

    private double movementSpeed = 4;

    private boolean leftPressed = false, rightPressed = false, downPressed = false, upPressed = false;

    public MovableCamera(String name) {
        super();
        this.name = name;

        getBox().setSize(100, 100);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        if (e.getKeyCode() == KeyEvent.VK_A) { leftPressed = true; }
        if (e.getKeyCode() == KeyEvent.VK_D) { rightPressed = true; }
        if (e.getKeyCode() == KeyEvent.VK_W) { upPressed = true; }
        if (e.getKeyCode() == KeyEvent.VK_S) { downPressed = true; }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        if (e.getKeyCode() == KeyEvent.VK_A) { leftPressed = false; }
        if (e.getKeyCode() == KeyEvent.VK_D) { rightPressed = false; }
        if (e.getKeyCode() == KeyEvent.VK_W) { upPressed = false; }
        if (e.getKeyCode() == KeyEvent.VK_S) { downPressed = false; }
    }

    @Override
    public void update() {
        super.update();
        movement();
    }

    private void movement() {
        Vector2D delta = new Vector2D(0, 0);

        if (leftPressed) delta.addX(-1);
        if (rightPressed) delta.addX(1);

        if (upPressed) delta.addY(-1);
        if (downPressed) delta.addY(1);

        delta.mul(movementSpeed);

        translate(delta);

    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}