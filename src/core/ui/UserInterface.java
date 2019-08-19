package core.ui;

import java.util.ArrayList;

import core.Game;
import core.IGraphic;
import core.IRenderer;
import core.math.Vector2D;

/**
 * An interface for the user to interact with.
 */
public abstract class UserInterface <R extends IRenderer<R>> extends Container<R> {

    /**
     * The layer to render this graphic, larger number means more on top.
     */
    private int layerIndex = 0;

    /**
     * Create a user interface.
     * 
     * @param id the (hopefully) unique identifier/name of the interface
     */
    public UserInterface() {
        super(false);

        setLayerIndex(420);
        setVisible(false);

        getBox().setSize(Game.getWindowInstance().getSizeVector());

        connectMouse();

        setup();
        refresh();
    }

    /**
     * A setup method where the components of the interface can be added.
     */
    public abstract void setup();

    /**
     * Rebuild the UI components.
     */
    public void reload() {
        getComponents().clear();
        setup();
        refresh();
    }

    /**
     * Refresh the state of the UserInterface.
     * 
     * <p>This method can be called to tell the UI to update it's component.
     * 
     * <p>It is unnecessary to constantly update the UI with {@link #update()}, because the UI does usually not change that much.
     * Instead you should do the updating in this method which is called less often.
     */
    @Override
    public void refresh() {
        super.refresh();
    }

    /**
     * Called when the UserInterface is shown. Method is called before the new {@link #isVisible()} state is set.
     * 
     * @see #setVisible
     */
    protected void onShow() {
        refresh();
    }

    /**
     * Called when the UserInterface is hidden. Method is called before the new {@link #isVisible()} state is set.
     * 
     * @see #setVisible
     */
    protected void onHide() {
        refresh();
    }

    /**
     * Invoked when the window is resized.
     * 
     * @param size the new size of the window
     */
    public void onResize(Vector2D size) {
        getSize().set(size);
        refresh();
    }

    @Override
    protected boolean shouldPropagate(IComponent<R> obj) {
        return obj.isVisible();
    }

    @Override
    public void render(R renderer, Vector2D screenPos) {
        if (isVisible()) {
            for (IComponent<R> c : getComponents()) {
                c.getGraphic().render(renderer, getPosition().copy().add(c.getPosition()));
            }
        }
    }

    @Override
    public IGraphic<R> getGraphic() {
        return this;
    }

    @Override
    public void setVisible(boolean visible) {
        if (isVisible() && !visible) onHide();
        else if (!isVisible() && visible) onShow();
        super.setVisible(visible);
    }

    @Override
    public void setLayerIndex(int layerIndex) {
        this.layerIndex = layerIndex;
    }

    @Override
    public int getLayerIndex() {
        return layerIndex;
    }

    /**
     * Add a component to the UI.
     * 
     * @param comp the component to add
     */
    public void add(IComponent<R> comp) {
        getComponents().add(comp);
        comp.setUI(this);
    }

    /**
     * Get the components inside this UI
     * 
     * @return the components
     */
    public ArrayList<IComponent<R>> getComponents() {
        return getObjects();
    }

}