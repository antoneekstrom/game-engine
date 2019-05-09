package core.ui;

import core.IGraphic;
import core.IRenderer;
import core.math.Vector2D;

/**
 * A very long name for a very unneccessary component.
 * <p>
 * This is a {@link GraphicComponent} that renders an {@link IGraphic}.
 */
public class ConcreteGraphicComponent<R extends IRenderer<R>> extends GraphicComponent<R> {

    IGraphic<R> graphic;

    /**
     * @param g the graphic to render
     */
    public ConcreteGraphicComponent(IGraphic<R> g) {
        super();
        graphic = g;
    }

    @Override
    public void render(R renderer, Vector2D pos) {
        graphic.render(renderer, pos);
    }
    
}