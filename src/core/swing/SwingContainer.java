package core.swing;

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