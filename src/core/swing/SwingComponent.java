package core.swing;

import core.ui.GraphicComponent;

/**
 * SwingComponent TODO
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