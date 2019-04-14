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

    @Override
    public void render(R renderer, Vector2D screenPos) {
        if (isVisible())
        for (O obj : getObjects()) {
            obj.getGraphic().render(renderer, screenPos.copy().add(obj.getPosition()));
        }
    }

    @Override
    public void update() {
        for (O obj : getObjects()) {
            obj.update();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (O obj : getObjects()) {
            obj.mouseMoved(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (O obj : getObjects()) {
            obj.mouseDragged(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (O obj : getObjects()) {
            obj.mouseReleased(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (O obj : getObjects()) {
            obj.mousePressed(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (O obj : getObjects()) {
            obj.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (O obj : getObjects()) {
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