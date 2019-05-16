package core.swing;

import java.awt.Font;

import core.io.resources.Resources;
import core.math.Vector2D;
import core.ui.IComponent;
import core.ui.style.DataUI;

/**
 * SwingInfoUI
 */
public abstract class SwingDataUI<O> extends DataUI<SwingRenderer, O> {

    /**
     * The font.
     */
    private Font font;

    public SwingDataUI(O obj, boolean reloadOnShow) {
        super(obj, reloadOnShow);
        setFont(Resources.getFont());
    }

    public SwingDataUI(O obj) {
        this(obj, false);
    }
    
    public SwingDataUI(boolean reloadOnShow) {
        this(null, reloadOnShow);
    }

    public SwingDataUI() {
        this(null, false);
    }

    @Override
    public void render(SwingRenderer renderer, Vector2D screenPos) {
        if (font != null) renderer.getGraphics().setFont(getFont());
        super.render(renderer, screenPos);
    }

    @Override
    public void add(IComponent<SwingRenderer> comp) {
        super.add(comp);
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