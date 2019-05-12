package core.swing;

import core.IGraphic;
import core.math.Vector2D;
import core.obj.IGameObject;
import core.ui.HudComponent;

/**
 * A {@link HudComponent} that uses the {@link SwingRenderer}.
 * 
 * <p>This component renders a given {@link IGraphic} relative to an {@link IGameObject}.
 */
public class SwingHudComponent extends HudComponent<SwingRenderer> {

    /**
     * Create a HudComponent.
     * 
     * @param obj the object to follow
     * @param g the graphic to render
     * @param offset the offset from the object
     */
    public SwingHudComponent(IGameObject<SwingRenderer> obj, IGraphic<SwingRenderer> g, Vector2D offset) {
        super(obj);
        setGraphic(g);
        setPosition(offset);
    }
    
    @Override
    public void render(SwingRenderer renderer, Vector2D pos) {
        super.render(renderer, pos);
        getInnerGraphic().render(renderer, pos);
    }
    
}