package core.swing;

import core.IGraphic;
import core.IRenderer;
import core.IRenderer.Type;
import core.graphic.BoxGraphic;
import core.graphic.GraphicObject;
import core.math.Box;
import core.math.Vector2D;
import core.obj.GameObject;

/**
 * Convenience {@link GameObject} that uses a {@link SwingRenderer}.
 * 
 * <p>The SwingObject is a subtype of {@link GraphicObject}, meaning, it is an {@link IGraphic} itself.
 * Even though it returns itself as a graphic, it does still render it's inner graphic like a normal {@link GameObject} would.
 * This is so that the user can override the {@link #render(SwingRenderer, Vector2D)} method if necessary.
 * 
 * <p>These generic types are turning out to be more of a hassle than I would've
 * thought. Well, I had a feeling it was going to turn out this way. But hey, it
 * works!
 */
public class SwingObject extends GraphicObject<SwingRenderer> {

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
    public void render(SwingRenderer renderer, Vector2D pos) {
        getInnerGraphic().render(renderer, pos);
    }
    
}