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
    private UserInterface<R> ui;

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
        if (isVisible())
        super.updateMouseHover(pos);
    }

    @Override
    public boolean mouseHover() {
        return super.mouseHover() && isVisible();
    }

    @Override
    public UserInterface<R> getUI() {
        return ui;
    }

    @Override
    public void setUI(UserInterface<R> ui) {
        this.ui = ui;
    }
    
}