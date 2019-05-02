package core.obj;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import core.IRenderer;
import core.graphic.GraphicObject;
import core.math.Box;
import core.math.Vector2D;

/**
 * ObjectStorage
 * TODO
 */
public abstract class ObjectStorage <O extends IGameObject<R>, R extends IRenderer<R>> extends GraphicObject<R> {

    /**
     * 
     */
    private ArrayList<O> objects;

    /**
     * 
     */
    public ObjectStorage() {
        super(new Box());
        objects = new ArrayList<>();
    }

    /**
     * If the storage object should forward a method call to a child object.
     * @param obj the child object
     * @return if the child should be invoked
     */
    protected abstract boolean shouldPropagate(O obj);

    @Override
    public void render(R renderer, Vector2D screenPos) {
        if (isVisible())
        for (O obj : getObjects()) {
            if (shouldPropagate(obj))
            obj.getGraphic().render(renderer, screenPos.copy().add(obj.getPosition()));
        }
    }

    @Override
    public void update() {
        for (O obj : getObjects()) {
            if (shouldPropagate(obj))
            obj.update();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (O obj : getObjects()) {
            if (shouldPropagate(obj))
            obj.mouseMoved(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (O obj : getObjects()) {
            if (shouldPropagate(obj))
            obj.mouseDragged(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (O obj : getObjects()) {
            if (shouldPropagate(obj))
            obj.mouseReleased(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (O obj : getObjects()) {
            if (shouldPropagate(obj))
            obj.mousePressed(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (O obj : getObjects()) {
            if (shouldPropagate(obj))
            obj.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (O obj : getObjects()) {
            if (shouldPropagate(obj))
            obj.keyReleased(e);
        }
    }

    /**
     * @return the objects
     */
    public ArrayList<O> getObjects() {
        return objects;
    }
    
}