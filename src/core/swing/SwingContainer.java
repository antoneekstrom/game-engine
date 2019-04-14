package core.swing;

import core.IRenderer;
import core.IRenderer.Type;
import core.ui.Container;

/**
 * SwingContainer TODO
 */
public class SwingContainer extends Container<SwingRenderer> {

    @Override
    protected void updateMouseHover() {
        super.updateMouseHover(getPosition());
    }
    
}