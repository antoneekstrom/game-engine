package core.ui.layout;

import java.util.ArrayList;

import core.IRenderer;
import core.math.Vector2D;
import core.ui.Container;
import core.ui.IComponent;

/**
 * The default layout. Components will be centered by default and stack downwards.
 * You can set if the components will be resized to fill the container.
 * 
 * <p> TODO: currently ignores alignment
 */
public class DefaultLayout<R extends IRenderer<R>> extends AbstractLayout<R> {

    /**
     * if the layout should be able to change the size of the containers children
     */
    private boolean resizeChildren = true;

    /**
     * The percent of the width of the container that children will be resized to.
     */
    private double widthScale = 1;

    /**
     * The spacing between the children in height.
     */
    protected double spacing = 0;

    /**
     * The margin of the first child from the top of the container.
     */
    protected double topMargin = 0;


    /**
     * The current number of items that have been aligned.
     */
    private int currentIndex = 0;

    /**
     * The largest y of the last component, used for aligning them in y axis.
     */
    protected double lastHeight = 0;

    /**
     * Resizes children by default.
     */
    public DefaultLayout() {
        this(true);
    }

    /**
     * @param resizeChildren if the container should resize it's children
     */
    public DefaultLayout(boolean resizeChildren) {
        this.resizeChildren = resizeChildren;
    }

    @Override
    protected void setup() {
    }

    @Override
    public void clear() {
        currentIndex = 0;
        lastHeight = 0;
    }

    @Override
    public void align(IComponent<R> comp, Alignment align) {

        ArrayList<IComponent<R>> children = getContainer().getChildren();

        // check that the component is a child
        if (!children.contains(comp)) return;

        Container<R> c = getContainer();
        Vector2D pos = comp.getPosition();

        // resize the component if it should be
        if (shouldResizeChildren()) {
            comp.getBox().getSize().setX(c.getBox().getWidth() * widthScale);
        }

        // begin with aligning it in the center
        pos.set(c.getSize().copy().sub(comp.getBox().getSize()).div(2));

        // set the y position of the component, which is determined by it's index
        pos.setY(lastHeight).addY(currentIndex == 0 ? topMargin : spacing);

        // save state
        lastHeight = comp.getBox().getMaxY();
        currentIndex++;
    }

    /**
     * @param resizeChildren if the layout should be able to change the size of the containers children
     */
    public void shouldResizeChildren(boolean resizeChildren) {
        this.resizeChildren = resizeChildren;
    }

    /**
     * @return if the layout should be able to change the size of the containers children
     */
    public boolean shouldResizeChildren() {
        return resizeChildren;
    }

    /**
     * @param widthScale The percent of the width of the container that children will be resized to
     */
    public void setWidthScale(double widthScale) {
        this.widthScale = widthScale;
    }

    /**
     * @param spacing the spacing between the children in height
     */
    public void setSpacing(double spacing) {
        this.spacing = spacing;
    }

    /**
     * @param topMargin the margin of the first child from the top of the container
     */
    public void setTopMargin(double topMargin) {
        this.topMargin = topMargin;
    }

}