package core;

import core.math.Vector2D;
import core.obj.IGameObject;

/**
 * A graphic that can be rendered with an appropriate {@link IRenderer}.
 * 
 * <p>The main goal of encapsulating the graphics into it's own object is to overcomplicate things even more.
 * 
 * <p>The other goal is to be able to render things in a certain order without having to reorder the actual list of game objects in {@link Logic}.
 * This can be achieved by iterating over the objects, getting their {@code IGraphic} objects and queueing them into a list that can then be reorder however you want.
 */
public interface IGraphic <R extends IRenderer<R>> {

    /**
     * Render the object. This method will primarily be called by the {@link IRenderer}.
     * 
     * @param renderer the renderer to use
     * @param screenPos the position on the screen to draw the object
     * 
     * @see IRenderer
     */
    public void render(R renderer, Vector2D pos);

    /**
     * Get the layer this graphic is to be rendered in. This number affects the "z-depth", if this graphic should be drawn in front of others or behind.
     * <p>A larger number means a higher priority to be rendered on top.
     * 
     * @return the index
     */
    public int getLayerIndex();

    /**
     * Set the layer index of the graphic.
     * 
     * <p>I tried to come up with a better method to do this but I was limited by the fact that {@link IGameObject} takes in a type of {@link IRenderer} and not a subtype of {@link IGraphic}. This means that you cannot perform things that rely on knowledge about what kind of graphic that is stored in the {@code IGameObject}. You can only know that it is <i>some</i> type of {@code IGraphic} and what kind of renderer it has, but you don't know what kind of subtype of {@code IGraphic} that is.
     * 
     * @param index the index of the layer this graphic is supposed to be rendered in
     */
    public void setLayerIndex(int index);

    /**
     * @return
     */
    public boolean isVisible();

    /**
     * @param visible
     */
    public void setVisible(boolean visible);
    
}