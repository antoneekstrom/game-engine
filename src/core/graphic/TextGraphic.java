package core.graphic;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import core.math.Vector2D;
import core.swing.SwingGraphic;
import core.swing.SwingRenderer;

/**
 * Text graphic. TODO
 */
public class TextGraphic extends SwingGraphic {

    /**
     * 
     */
    private String text;

    /**
     * 
     */
    private Font font;

    /**
     * 
     */
    private Vector2D textBox;

    /**
     * @param text the text to use
     */
    public TextGraphic(String text) {
        super();
        setText(text);
        textBox = Vector2D.create();
    }

    /**
     * @param text
     * @param font
     */
    public TextGraphic(String text, Font font) {
        this(text);
        setFont(font);
    }

    @Override
    public void render(Graphics2D g, SwingRenderer renderer, Vector2D pos) {
        if (getFont() != null) g.setFont(getFont());

        textBox.set(getTextSize(g));

        DrawHelper.drawText(getText(), pos.addY(textBox.getY() / 1.5), g);
    }

    /**
     * Calculate the dimension of the current text.
     * 
     * @param g the graphics2d object used to do the calculations
     * @return the dimensions of the text
     */
    public Vector2D getTextSize(Graphics2D g) {
        FontMetrics m = g.getFontMetrics();
        return Vector2D.create(m.stringWidth(getText()), m.getHeight());
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param font the font to set
     */
    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * @return the font
     */
    public Font getFont() {
        return font;
    }

    /**
     * @return the textBox
     */
    public Vector2D getTextSize() {
        return textBox;
    }
    
}