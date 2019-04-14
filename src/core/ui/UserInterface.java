package core.ui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
     * 
     */
    private boolean visible = true;

    /**
     * Name/id of the interface. Used to find it in UIManagers.
     */
    private final String id;

    /**
     * Create a user interface.
     * 
     * @param id the (hopefully) unique identifier/name of the interface
     */
    public UserInterface(String id) {
        super(false);
        this.id = id;

        setLayerIndex(420);

        getBox().setSize(Game.getWindowInstance().getSizeVector());

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
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        if (isVisible() && !visible) onHide();
        else if (!isVisible() && visible) onShow();
        this.visible = visible;
    }

    @Override
    public void update() {
        for (IComponent<R> c : getComponents()) {
            c.update();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (IComponent<R> c : getComponents()) {
            c.mouseMoved(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (IComponent<R> c : getComponents()) {
            c.mouseDragged(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (IComponent<R> c : getComponents()) {
            c.mouseReleased(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (IComponent<R> c : getComponents()) {
            c.mousePressed(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (IComponent<R> c : getComponents()) {
            c.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (IComponent<R> c : getComponents()) {
            c.keyReleased(e);
        }
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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Add a component to the UI.
     * 
     * @param comp the component to add
     */
    public void add(IComponent<R> comp) {
        getComponents().add(comp);
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