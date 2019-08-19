package core.graphic;

import java.util.ArrayList;

import core.IGraphic;
import core.IRenderer;
import core.math.Vector2D;

/**
 * A graphic that contains multiple graphics.
 */
public class GraphicContainer<R extends IRenderer<R>> implements IGraphic<R> {

    /**
     * pee
     */
    private ArrayList<IGraphic<R>> graphics;

    /**
     * 
     */
    private boolean visible = true;

    /**
     * 
     */
    private int layerIndex = 0;

    /**
     * @param graphics
     */
    @SafeVarargs
    public GraphicContainer(IGraphic<R>... graphics) {
        this.graphics = new ArrayList<>();
        addGraphics(graphics);
    }

    @Override
    public void render(R renderer, Vector2D pos) {
        for (IGraphic<R> g : getGraphics()) {
            g.render(renderer, pos);
        } 
    }

    @Override
    public int getLayerIndex() {
        return layerIndex;
    }

    @Override
    public void setLayerIndex(int index) {
        layerIndex = index;
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
     * @return the graphics
     */
    public ArrayList<IGraphic<R>> getGraphics() {
        return graphics;
    }

    /**
     * @param graphics
     */
    @SafeVarargs
    public final void addGraphics(IGraphic<R>... graphics) {
        for (IGraphic<R> g : graphics) {
            getGraphics().add(g);
        }
    }
    
}