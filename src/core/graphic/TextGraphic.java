package core.graphic;

import java.awt.Font;
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
     * @param text the text to use
     */
    public TextGraphic(String text) {
        super();
        setText(text);
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
        g.setFont(getFont());
        DrawHelper.drawText(getText(), pos, g);
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
    
}