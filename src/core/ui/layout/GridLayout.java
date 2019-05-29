package core.ui.layout;

import core.IRenderer;
import core.math.Box;
import core.math.Vector2D;
import core.ui.IComponent;

/**
 * A layout that puts it's items like a list. Items will be put on one row until they no longer
 * fit and then a new row will begin.
 */
public class GridLayout<R extends IRenderer<R>> extends AbstractLayout<R> {

    double curX = 0, curY = 0;
    int rowNum = 0, colNum = 0;

    int maxColNum = 10000;

    Vector2D itemSize;
    double itemSpacing = 0;

    boolean itemFullWidth = false;

    public GridLayout(Vector2D itemSize, double itemSpacing) {
        super();
        this.itemSize = itemSize;
        this.itemSpacing = itemSpacing;
    }

    public GridLayout(double itemSpacing, double itemHeight) {
        this(Vector2D.create(0, itemHeight), itemSpacing);
        itemFullWidth = true;
        maxColNum = 2;
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

        Box compBox = comp.getBox();

        compBox.setSize(itemSize);
        if (itemFullWidth) compBox.setWidth(getContainer().getBox().getHeight());

        if (compBox.getWidth() >= getContainer().getSize().getX()) compBox.setWidth(getContainer().getSize().getX() - 1);

        Vector2D pos = comp.getPosition();

        double x = colNum * (itemSize.getX() + itemSpacing);
        double y = rowNum * (itemSize.getY() + itemSpacing);

        curX = x;
        curY = y;

        colNum++;

        if (curX + itemSize.getX() > getContainer().getSize().getX() || colNum >= maxColNum) {
            curY = 0;
            colNum = 0; 
            rowNum++;

            align(comp, align);
        }
        else {
            pos.set(x, y);
        }
    }

    /**
     * @param maxColNum the maxColNum to set
     */
    public void setMaxColNum(int maxColNum) {
        this.maxColNum = maxColNum;
    }
    
}