package core.ui;

import core.IRenderer;
import core.math.Vector2D;
import core.ui.layout.GridLayout;

/**
 * A component that shows it's children in a scrollable list.
 */
public class ListComponent <R extends IRenderer<R>> extends Container<R> {

    public ListComponent(Vector2D itemSize, double itemSpacing) {
        super();
        
        GridLayout<R> l = new GridLayout<>(itemSize, itemSpacing);
        setLayout(l);
    }
    
}