package core.ui;

import core.IGraphic;
import core.IRenderer;

/**
 * GraphicComponent TODO
 */
public abstract class GraphicComponent<R extends IRenderer<R>> extends Component<R> implements IGraphic<R> {

    /**
     * The layer index.
     */
    private int layerIndex = 0;

    @Override
    public int getLayerIndex() {
        return layerIndex;
    }

    @Override
    public void setLayerIndex(int index) {
        layerIndex = index;
    }

    @Override
    public IGraphic<R> getGraphic() {
        return this;
    }

}