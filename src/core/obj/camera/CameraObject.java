package core.obj.camera;

import core.IRenderer;
import core.graphic.Camera;
import core.graphic.ICamera;
import core.math.Vector2D;
import core.obj.GameObject;

/**
 * An {@link ICamera} as an object.
 */
public class CameraObject <R extends IRenderer<R>> extends GameObject<R> implements ICamera {

    /**
     * The camera.
     */
    private Camera camera;

    public CameraObject(Vector2D pos) {
        super(pos.getX(), pos.getY(), 0, 0);
        camera = new Camera();
    }

    public CameraObject() {
        this(new Vector2D());
    }

    /**
     * @return the camera
     */
    public Camera getCamera() {
        return camera;
    }

    @Override
    public Vector2D getPosition() {
        return super.getPosition();
    }
}