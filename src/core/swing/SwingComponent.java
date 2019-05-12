package core.swing;

import core.ui.GraphicComponent;
import core.ui.IComponent;

/**
 * A concrete {@link IComponent} that uses the {@link SwingRenderer}.
 */
public abstract class SwingComponent extends GraphicComponent<SwingRenderer> {

    @Override
    protected void updateMouseHover() {
        super.updateMouseHover(getPosition());
    }

    /**
     * Get the {@link SwingRenderer} instance.
     * 
     * @return the renderer
     */
    public SwingRenderer getRenderer() {
        return getLogic().getRenderer();
    }
    
}