package core.ui.layout;

import core.IRenderer;
import core.math.Vector2D;
import core.ui.IComponent;

/**
 * A layout that puts it's items like a list. Items will be put on one row until they no longer
 * fit and then a new row will begin.
 */
public class GridLayout<R extends IRenderer<R>> extends AbstractLayout<R> {

    double curX = 0, curY = 0;
    int rowNum = 0, colNum = 0;

    Vector2D itemSize;
    double itemSpacing = 0;

    public GridLayout(Vector2D itemSize, double itemSpacing) {
        super();
        this.itemSize = itemSize;
        this.itemSpacing = itemSpacing;
    }

    @Override
    protected void setup() {
    }

    @Override
    public void clear() {
        curX = 0;
        curY = 0;
        rowNum = 0;
        colNum = 0;
    }

    @Override
    public void align(IComponent<R> comp, Alignment align) {
        Vector2D pos = comp.getPosition();

        double x = colNum * (itemSize.getX() + itemSpacing);
        double y = rowNum * (itemSize.getY() + itemSpacing);

        curX = x;
        curY = y;

        colNum++;

        if (curX + itemSize.getX() > getContainer().getSize().getX()) {
            curY = 0;
            colNum = 0;
            rowNum++;

            align(comp, align);
        }
        else {
            pos.set(x, y);
        }
    }

    
}