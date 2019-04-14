package core.swing;

import java.awt.Font;

import core.io.resources.Resources;
import core.math.Vector2D;
import core.ui.UserInterface;

/**
 * This is an {@link UserInterface} that uses the {@link SwingRenderer}.
 * <p>This class is mainly for convenience so that I do not have write the same boilerplate all the time.
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
        setFont(Resources.getFont());
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