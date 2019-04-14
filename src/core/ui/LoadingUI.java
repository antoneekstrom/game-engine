package core.ui;

import core.IRenderer;

/**
 * Shows a loading bar.
 */
public class LoadingUI <R extends IRenderer<R>> extends UserInterface<R> {

    public static final String ID = "loading_ui";

    MeterComponent loadingBar;

    public LoadingUI() {
        super(ID);
    }

    @Override
    public void setup() {
        loadingBar = new MeterComponent(() -> 3d, 100);
    }
    
}