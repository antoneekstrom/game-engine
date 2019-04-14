package core.ui;

import core.IRenderer;
import core.math.Vector2D;
import core.obj.IGameObject;

/**
 * An {@link IComponent} that is rendered relative to an {@link IGameObject} in the world.
 */
public abstract class HudComponent<R extends IRenderer<R>> extends GraphicComponent<R> {

    /**
     * 
     */
    IGameObject<R> obj;

    /**
     * @param obj the object to follow
     */
    public HudComponent(IGameObject<R> obj) {
        setObject(obj);
    }

    @Override
    public void render(R renderer, Vector2D pos) {

        Vector2D newPos = getObject().getPosition().copy();
        newPos.add(pos);
        newPos.set(renderer.getCamera().getDisplayCoordinates(newPos));

        pos.set(newPos);
    }

    /**
     * @param obj the obj to set
     */
    public void setObject(IGameObject<R> obj) {
        this.obj = obj;
    }

    /**
     * @return the obj
     */
    public IGameObject<R> getObject() {
        return obj;
    }
    
}