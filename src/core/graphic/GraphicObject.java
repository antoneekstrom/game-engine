package core.graphic;

import core.IGraphic;
import core.IRenderer;
import core.math.Box;
import core.obj.GameObject;

/**
 * A {@link GameObject} which is an {@link IGraphic} itself.
 */
public abstract class GraphicObject<R extends IRenderer<R>> extends GameObject<R> implements IGraphic<R> {

    /**
     * 
     */
    private boolean visible = true;

    /**
     * 
     */
    private int layerIndex = 0;

    /**
     * @param box the box t use.
     */
    public GraphicObject(Box box) {
        super(box);
    }

    /**
     * Get the {@link IGraphic} object that is housed inside this component.
     * @return the graphic
     */
    public IGraphic<R> getInnerGraphic() {
        return super.getGraphic();
    }
    
    @Override
    public IGraphic<R> getGraphic() {
        return this;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public int getLayerIndex() {
        return layerIndex;
    }

    @Override
    public void setLayerIndex(int index) {
        layerIndex = index;
    }

    
}