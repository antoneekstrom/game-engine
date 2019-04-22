package core.ui.layout;

import core.IRenderer;
import core.math.Vector2D;
import core.ui.IComponent;

/**
 * CenteredLayout
 */
public class CenteredLayout<R extends IRenderer<R>> extends AbstractLayout<R> {

    private double spacing = 0;

    private double currentHeight = 0;

    private double marginTop = 0;

    private int index = 0;

    @Override
    protected void setup() {
    }

    @Override
    public void clear() {
        index = 0;
        currentHeight = 0;
        marginTop = 0;
    }

    @Override
    public void align(IComponent<R> comp, Alignment align) {
        Vector2D pos = comp.getPosition();

        pos.addY(marginTop);

        currentHeight += comp.getBox().getHeight();
        if (index != 0) currentHeight += spacing;

        index++;

        realign();
    }

    public void realign() {

        marginTop = (getContainer().getBox().getHeight() / 2) - (currentHeight / 2);

        double height = marginTop;

        for (IComponent<R> c : getContainer().getChildren()) {

            c.getPosition().setX((getContainer().getBox().getWidth() / 2) - (c.getBox().getWidth() / 2));
            c.getPosition().setY(height);
            height += spacing + c.getBox().getHeight();
        }
    }

    /**
     * @param spacing the spacing to set
     */
    public void setSpacing(double spacing) {
        this.spacing = spacing;
    }
    
}