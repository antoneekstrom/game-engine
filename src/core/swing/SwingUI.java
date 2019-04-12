package core.swing;

import java.awt.Font;

import core.math.Vector2D;
import core.ui.UserInterface;

/**
 * SwingUI TODO
 */
public abstract class SwingUI extends UserInterface<SwingRenderer> {

    /**
     * 
     */
    private Font font;

    /**
     * Create a swing ui.
     * 
     * @param id the id to use
     */
    public SwingUI(String id) {
        super(id);
    }

    @Override
    public void render(SwingRenderer renderer, Vector2D screenPos) {
        if (font != null) renderer.getGraphics().setFont(getFont());
        super.render(renderer, screenPos);
    }

    /**
     * @return the font
     */
    public Font getFont() {
        return font;
    }

    /**
     * @param font the font to set
     */
    public void setFont(Font font) {
        this.font = font;
    }
    
}