package core.ui;

import core.IRenderer;
import core.math.Box;
import core.math.Vector2D;
import core.obj.GameObject;
import core.swing.SwingRenderer;

/**
 * An {@link IComponent} that uses the {@link SwingRenderer}.
 */
public abstract class Component<R extends IRenderer<R>> extends GameObject<R> implements IComponent<R> {

    /**
     * 
     */
    public Component() {
        super(new Box());
    }

    @Override
    public void refresh() {}

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void setVisible(boolean visible) {
        getGraphic().setVisible(visible);
    }

    @Override
    public boolean isVisible() {
        return getGraphic().isVisible();
    }

    @Override
    protected void updateMouseHover(Vector2D pos) {
        super.updateMouseHover(pos);
    }
    
}