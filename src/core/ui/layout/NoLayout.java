package core.ui.layout;

import core.IRenderer;
import core.ui.IComponent;

/**
 * A layout that does nothing.
 */
public class NoLayout<R extends IRenderer<R>> extends AbstractLayout<R> {

    @Override
    protected void setup() {
    }

    @Override
    public void clear() {
    }

    @Override
    public void align(IComponent<R> comp, Alignment align) {
    }

    
}