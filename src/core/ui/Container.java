package core.ui;

import java.util.ArrayList;

import core.IRenderer;
import core.math.Vector2D;
import core.obj.ObjectStorage;
import core.ui.layout.AbstractLayout;
import core.ui.layout.Alignment;
import core.ui.layout.DefaultLayout;

/**
 * A basic {@link IComponent} container. This container groups multiple components.
 */
public class Container <R extends IRenderer<R>> extends ObjectStorage<IComponent<R>, R> implements IComponent<R> {

    /**
     * The layout.
     */
    private AbstractLayout<R> layout;

    /**
     * The default alignment for children that are added to the container.
     */
    private Alignment defaultAlignment = Alignment.CENTER;

    /**
     * Create a container.
     */
    public Container() {
        this(true);
    }

    /**
     * @param size the size of the container
     */
    public Container(Vector2D size) {
        this(true);
        getBox().setSize(size);
    }

    /**
     * @param resizeChildren if the container should resize it's children
     */
    public Container(boolean resizeChildren) {
        this(new DefaultLayout<>(resizeChildren));
    }

    /**
     * @param layout the layout to use
     */
    public Container(AbstractLayout<R> layout) {
        super();
        setLayout(layout);
    }

    @Override
    public void refresh() {
        getChildren().forEach(IComponent::refresh);
        align();
    }

    @Override
    public void render(R renderer, Vector2D screenPos) {
        getInnerGraphic().render(renderer, screenPos);
        super.render(renderer, screenPos);
    }

    /**
     * Realign the children according to the current layout.
     */
    public void align() {
        getLayout().clear();
        getChildren().forEach(c -> getLayout().align(c, getDefaultAlignment()));
    }

    /**
     * Add a component as children to this container.
     * 
     * @param component the component to add
     */
    public void add(IComponent<R> component) {
        getChildren().add(component);
        getLayout().align(component, getDefaultAlignment());
    }

    /**
     * Add a component as children to this container.
     * 
     * @param component the component to add
     * @param align the aligment for the item
     */
    public void add(IComponent<R> component, Alignment align) {
        getChildren().add(component);
        getLayout().align(component, align);
    }

    /**
     * Get the children of this container.
     * 
     * @return the children
     */
    public ArrayList<IComponent<R>> getChildren() {
        return getObjects();
    }

    /**
     * @param layout the layout to set
     */
    public void setLayout(AbstractLayout<R> layout) {

        if (layout == null) return;

        this.layout = layout;
        layout.apply(this);

        align();
    }

    /**
     * @return the layout
     */
    public AbstractLayout<R> getLayout() {
        return layout;
    }

    /**
     * @param defaultAlignment the defaultAlignment to set
     */
    public void setDefaultAlignment(Alignment defaultAlignment) {
        this.defaultAlignment = defaultAlignment;
    }

    /**
     * @return the default alignment of the children
     */
    public Alignment getDefaultAlignment() {
        return defaultAlignment;
    }

}