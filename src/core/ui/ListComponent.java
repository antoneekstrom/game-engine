package core.ui;

import core.IRenderer;
import core.math.Vector2D;
import core.ui.layout.AbstractLayout;
import core.ui.layout.GridLayout;

/**
 * A component that shows it's children in a scrollable list.
 */
public class ListComponent <R extends IRenderer<R>> extends Container<R> {

    /**
     * 
     * @param listSize
     * @param itemSize
     * @param itemSpacing
     * @param vertical if the amount of columns should be larger than one
     */
    public ListComponent(Vector2D listSize, Vector2D itemSize, double itemSpacing) {
        super(listSize);
        
        GridLayout<R> l = new GridLayout<>(itemSize, itemSpacing);
        setLayout(l);
    }

    public ListComponent(Vector2D listSize, double itemSpacing, double itemHeight) {
        super(listSize);
        GridLayout<R> l = new GridLayout<>(itemSpacing, itemHeight);
        setLayout(l);
    }

    @Override
    public void setLayout(AbstractLayout<R> layout) {
        if (GridLayout.class.isAssignableFrom(layout.getClass()))
            super.setLayout(layout);
    }

    @Override
    public void refresh() {
        super.refresh();
    }

    @Override
    public GridLayout<R> getLayout() {
        return (GridLayout<R>) super.getLayout();
    }
    
}