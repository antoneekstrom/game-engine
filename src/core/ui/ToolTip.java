package core.ui;

import java.awt.Color;

import core.math.Box;
import core.math.Vector2D;
import core.swing.SwingComponent;
import core.swing.SwingRenderer;

/**
 * A box that shows contextual information about something.
 */
public class ToolTip extends SwingComponent {

    /**
     * 
     */
    private Vector2D offset = new Vector2D(0, 0);

    /**
     * 
     */
    TextComponent textComponent;

    /**
     * @param target
     * @param text
    */
    public ToolTip(Box target, String text) {
        this(target.getPosition(), text);
        setOffset(target.getSize().copy().setY(0));
    }

    /**
     * @param target
     * @param text
     */
    public ToolTip(Vector2D target, String text) {
        super();
        getBox().usePosition(target);

        textComponent = new TextComponent(text);
        textComponent.setColor(Color.black);
        textComponent.setBackground(Color.white);
        textComponent.setCenterText(false);
        textComponent.setPadding(20, 20);
        setGraphic(textComponent);
    }

    @Override
    public void render(SwingRenderer renderer, Vector2D pos) {
        getInnerGraphic().render(renderer, pos.add(offset));
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(Vector2D offset) {
        this.offset = offset;
    }

    /**
     * @param text
     */
    public void setText(String text) {
        textComponent.setText(text);
    }

    /**
     * @return the textComponent
     */
    public TextComponent getTextComponent() {
        return textComponent;
    }

}