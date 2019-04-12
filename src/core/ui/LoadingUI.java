package core.ui;

import core.IRenderer;
import core.IRenderer.Type;

/**
 * Shows a loading bar.
 */
public class LoadingUI <R extends IRenderer<R>> extends UserInterface<R> {

    public static final String ID = "loading_ui";

    public LoadingUI() {
        super(ID);
    }

    @Override
    public void setup() {
    }

    @Override
    public boolean isCompatible(IRenderer<?> renderer) {
        return renderer.getType() == Type.SWING;
    }

    
}