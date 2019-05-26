package core.ui;

import java.awt.Color;
import java.util.function.Consumer;

import core.swing.SwingRenderer;

/**
 * ListItemComponent
 */
public class ListItemComponent extends ButtonComponent {

    ListComponent<SwingRenderer> list;

    public ListItemComponent(ListComponent<SwingRenderer> l, Consumer<String> action, String text, Color bgColor, Color fgColor) {
        super(text, () -> action.accept(text));
        setColor(fgColor);
        setBackground(bgColor);
        list = l;
    }
    
}