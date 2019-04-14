package core.swing;

import java.awt.Color;
import java.awt.Graphics2D;

import core.IGraphic;
import core.math.Vector2D;

/**
 * A graphic that uses the {@link SwingRenderer}.
 * 
 * @see IGraphic
 * @see SwingRenderer
 */
public abstract class SwingGraphic implements IGraphic<SwingRenderer> {

    /**
     * The color of the graphic. I choose to place the color in the base {@link SwingGraphic} because most graphics will utilize color in some way.
     */
    private Color color = Color.white;

    /**
     * The layer to render this graphic, larger number means more on top.
     */
    private int layerIndex = 0;

    /**
     * If the grpahic should be rendered.
     */
    private boolean visible = true;

    /**
     * Construct a graphic.
     */
    public SwingGraphic() {}

    @Override
    public void render(SwingRenderer renderer, Vector2D screenPos) {

        if (!isVisible()) return;

        Graphics2D g = renderer.getGraphics();
        g.setColor(getColor());

        render(renderer.getGraphics(), renderer, screenPos);
    }
    
    @Override
    public int getLayerIndex() {
        return layerIndex;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Render the graphic. This method is called by {@link #render(SwingRenderer, Vector2D)}. This method is where the actual graphics are to be programmed.
     * 
     * <p>It is separated into another method so that it will be easier to use and can provide additional parameters which are more relevant when rendering with swing.
     * 
     * <p>Another reason to separate the method is to allow for subclasses to perform some setup before the user is allowed to render.
     * 
     * @param g the swing graphics instance
     * @param renderer the renderer
     * @param transform the transform
     */
    public abstract void render(Graphics2D g, SwingRenderer renderer, Vector2D pos);

    /**
     * @param layerIndex the layerIndex to set
     */
    public void setLayerIndex(int layerIndex) {
        this.layerIndex = layerIndex;
    }

    /**
     * Set the color of the graphic.
     * 
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Get the current color of this graphic.
     * 
     * @return the color
     */
    public Color getColor() {
        return color;
    }

}