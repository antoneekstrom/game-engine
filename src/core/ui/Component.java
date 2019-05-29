package core.ui;

import java.awt.event.MouseEvent;

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
     * The UI this component belongs to.
     */
    private UserInterface<R> ui;

    /**
     * Create a component.
     */
    public Component() {
        super(new Box());
    }

    /**
     * Set the size of the component.
     * <p>I felt this method was lacking.
     * @param width the width
     * @param height the height
     */
    public void setSize(double width, double height) {
        getSize().set(width, height);
    }

    @Override
    public void refresh() {}

    @Override
    public void update() {
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
    public void updateMouseHover(Vector2D pos) {
        if (isVisible())
        super.updateMouseHover(pos);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
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