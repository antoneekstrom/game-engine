package core.ui;

import java.awt.Color;
import java.awt.Font;

import core.IRenderer;
import core.IRenderer.Type;
import core.graphic.BoxGraphic;
import core.graphic.TextGraphic;
import core.math.Vector2D;
import core.swing.SwingRenderer;

/**
 * A {@link Component} that displays text.TODO
 */
public class TextComponent extends GraphicComponent<SwingRenderer> {

    /**
     * 
     */
    private TextGraphic textGraphic;

    /**
     * 
     */
    private BoxGraphic boxGraphic;

    /**
     * 
     */
    private boolean showBackground = false;

    /**
     * @param text
     */
    public TextComponent(String text) {
        this(text, (Font) null);
    }

    /**
     * @param text
     * @param textColor
     */
    public TextComponent(String text, Color textColor) {
        this(text, (Font) null);
        textGraphic.setColor(textColor);
    }


    public TextComponent(String text, Color textColor, Color backgroundColor) {
        this(text, textColor);
        setBackground(backgroundColor);
    }

    /**
     * @param text
     * @param font
     */
    public TextComponent(String text, Font font) {
        super();
        textGraphic = new TextGraphic(text, font);
        boxGraphic = new BoxGraphic(getBox());
    }
    
    @Override
    public void render(SwingRenderer renderer, Vector2D pos) {

        if (showBackground) {
            resizeBackground();
            boxGraphic.render(renderer, pos);
        }
        
        textGraphic.render(renderer, pos);
    }

    @Override
    public boolean isCompatible(IRenderer<?> renderer) {
        return renderer.getType() == Type.SWING;
    }

    /**
     * Resize the background to fit the size of the text.
     */
    public void resizeBackground() {
        boxGraphic.getBox().setSize(textGraphic.getTextSize());
    }

    /**
     * @param text
     */
    public void setText(String text) {
        textGraphic.setText(text);
    }

    /**
     * @return
     */
    public String getText() {
        return textGraphic.getText();
    }

    /**
     * @param showBackground the showBackground to set
     */
    public void showBackground(boolean showBackground) {
        this.showBackground = showBackground;
    }

    /**
     * @param color
     */
    public void setBackground(Color color) {
        showBackground(true);
        boxGraphic.setColor(color);
    }

}