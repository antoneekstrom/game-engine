package core.ui;

import java.awt.Color;
import java.awt.Font;

import core.graphic.TextGraphic;
import core.swing.SwingRenderer;

/**
 * A {@link Component} that displays text.TODO
 */
public class TextComponent extends Component<SwingRenderer> {

    /**
     * @param text
     */
    public TextComponent(String text) {
        super();

        TextGraphic g = new TextGraphic(text);
        setGraphic(g);
    }

    /**
     * @param text
     * @param color
     */
    public TextComponent(String text, Color color) {
        this(text);
        getGraphic().setColor(color);
    }

    /**
     * @param text
     * @param font
     */
    public TextComponent(String text, Font font) {
        super();

        TextGraphic g = new TextGraphic(text, font);
        setGraphic(g);
    }

    @Override
    public TextGraphic getGraphic() {
        return (TextGraphic) super.getGraphic();
    }
    
    /**
     * @param text
     */
    public void setText(String text) {
        getGraphic().setText(text);
    }

    /**
     * @return
     */
    public String getText() {
        return getGraphic().getText();
    }

}