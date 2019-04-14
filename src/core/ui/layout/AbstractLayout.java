package core.ui.layout;

import core.IRenderer;
import core.ui.Container;
import core.ui.IComponent;

/**
 * A layout is something that can be applied to {@link Container} objects to align their children in certain ways.
 * 
 * <p>First, a layout is applied to a container. After that, children of the container can be aligned using {@link #align(IComponent, Alignment)}.
 */
public abstract class AbstractLayout<R extends IRenderer<R>> {

    /**
     * The container.
     */
    private Container<R> container;

    /**
     * @param container the container to create this layout for
     */
    public AbstractLayout() {}

    /**
     * Apply this layout to a container.
     * 
     * @param container the container
     */
    public void apply(Container<R> container) {

        if (getContainer() != null) clear();

        setContainer(container);
        setup();
    }

    /**
     * Setup the layout.
     */
    protected abstract void setup();

    /**
     * Clear the state of the layout.
     */
    public abstract void clear();

    /**
     * Align an {@link IComponent} according to the rules of this layout.
     * 
     * <p>Components that are aligned have to be children of the container.
     * If a component is not a child of the container the method should not do anything.
     * 
     * <p>The positions of the aligned items is determined by the order in which they are aligned.
     * If the layout of the container is switched while it has children, the components will be aligned in the order
     * in which they where added.
     * 
     * @param comp the component to align
     * @param align the alignment to use
     * 
     */
    public abstract void align(IComponent<R> comp, Alignment align);

    /**
     * Get the container that this layout is applied to.
     * 
     * @return the container
     */
    public Container<R> getContainer() {
        return container;
    }

    /**
     * Set the container.
     * 
     * @param container the container to set
     */
    private void setContainer(Container<R> container) {

        if (container != null) clear();

        this.container = container;
    }
    
}