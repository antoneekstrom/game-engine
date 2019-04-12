package core.graphic;

import core.obj.IGameObject;
import core.swing.SwingGraphic;
import core.swing.SwingRenderer;

/**
 * A {@link SwingGraphic} that houses an object which can be used to affect the graphic.
 */
public abstract class ObjectGraphic <O extends IGameObject<SwingRenderer>> extends BoxGraphic {

    /**
     * The object.
     */
    O object;

    /**
     * @param object the object to use
     */
    public ObjectGraphic(O object) {
        super(object.getBox());
        this.object = object;
    }

    /**
     * @return the object which this graphic uses
     */
    public O getObject() {
        return object;
    }
    
}