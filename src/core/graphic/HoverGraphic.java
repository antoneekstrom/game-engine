package core.graphic;

import java.awt.Color;

import core.math.Vector2D;
import core.obj.GameObject;
import core.swing.SwingRenderer;

/**
 * A graphic that changes color when you hover over it with the mouse.
 */
public class HoverGraphic extends ObjectGraphic<GameObject<SwingRenderer>> {

    private Color defaultColor = Color.white, hoverColor = Color.gray;

    /**
     * @param obj
     */
    public HoverGraphic(GameObject<SwingRenderer> obj) {
        super(obj);
    }

    /**
     * 
     * @param obj
     * @param defaultColor
     * @param hoverColor
     */
    public HoverGraphic(GameObject<SwingRenderer> obj, Color defaultColor, Color hoverColor) {
        this(obj);
        setDefaultColor(defaultColor);
        setHoverColor(hoverColor);
    }

    @Override
    protected void renderSetup(SwingRenderer renderer, Vector2D pos) {
        setColor(getObject().mouseHover() ? getHoverColor() : getDefaultColor());
        super.renderSetup(renderer, pos);
    }

    /**
     * @param defaultColor the defaultColor to set
     */
    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
    }

    /**
     * @param hoverColor the hoverColor to set
     */
    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    /**
     * @return the defaultColor
     */
    public Color getDefaultColor() {
        return defaultColor;
    }

    /**
     * @return the hoverColor
     */
    public Color getHoverColor() {
        return hoverColor;
    }
    
}