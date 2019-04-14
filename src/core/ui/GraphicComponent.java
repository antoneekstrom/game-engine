package core.ui;

import core.IGraphic;
import core.IRenderer;
import core.obj.IGameObject;

/**
 * A GraphicComponent is a {@link Component} that implements {@link IGraphic}.
 * 
 * <p>This means that the render method is located in the component, instead of the graphic.
 * This allows for easier control of how the component should be rendered, and usage of multiple graphics.
 * 
 * <p>It is actually quite intuitive to make this happen; An {@link IGameObject} is rendered using {@link IGameObject#getGraphic()}.
 * So, the only thing you have to do is to return the component itself from {@code getGraphic()}.
 * 
 * <p>This class is just yet another convenience object. I sure love those.
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

    /**
     * Get the {@link IGraphic} object that is housed inside this component.
     * @return the graphic
     */
    public IGraphic<R> getInnerGraphic() {
        return super.getGraphic();
    }

}