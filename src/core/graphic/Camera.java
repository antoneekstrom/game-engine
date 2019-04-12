package core.graphic;


import core.math.Vector2D;

/**
 * A concrete implementation of an {@link ICamera}.
 */
public class Camera implements ICamera {

    /**
     * The point from which to draw other objects on the screen.
     */
    private Vector2D position;

    public Camera() {
        this(0, 0);
    }

    public Camera(double x, double y) {
        position = new Vector2D(x, y);
    }

    public Camera(Vector2D position) {
        this(position.getX(), position.getY());
    }

    /**
     * @return the position
     */
    public Vector2D getPosition() {
        return position;
    }

    public void translate(Vector2D d) {
        getPosition().translate(d);
    }
    
}