package core.swing;

import core.graphic.BoxGraphic;
import core.math.Box;
import core.obj.GameObject;

/**
 * Convenience {@link GameObject} that uses a {@link SwingRenderer}.
 * 
 * <p>These generic types are turning out to be more of a hassle than I would've thought. Well, I had a feeling it was going to turn out this way. But hey, it works!
 */
public class SwingObject extends GameObject<SwingRenderer> {

    /**
     * Create a swing object.
     * 
     * @param box the size of the object
     */
    public SwingObject(Box box) {
        super(box);
        setGraphic(new BoxGraphic(box));
    }

    @Override
    public SwingGraphic getGraphic() {
        return (SwingGraphic) super.getGraphic();
    }
    
}