package core.obj;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import core.Game;
import core.IGraphic;
import core.IMouseInput;
import core.IRenderer;
import core.AbstractLogic;
import core.Window;
import core.math.Box;
import core.math.Vector2D;
import core.state.State;

/**
 * The {@code GameObject} is a concrete implementation of the {@link IGameObject} interface and provides convenience methods and does some boilerplate setup for you.
 */
public class GameObject <R extends IRenderer<R>> implements IGameObject<R> {

    /**
     * The bounds and position of the object.
     */
    private Box box;

    /**
     * The amount the object is rotated (in radians) from it's default orientation.
     */
    private double rotation = 0;

    /**
     * If the mouse is hovering over this objects box on screen.
     */
    private boolean mouseHover = false;

    /**
     * The graphic that this object should display.
     */
    private IGraphic<R> graphic;

    /**
     * @param box the bounds and position of the object
     */
    public GameObject(Box box) {
        this.box = box.copy();
    }

    /**
     * @param pos the position
     * @param size the dimensions
     */
    public GameObject(Vector2D pos, Vector2D size) {
        this(new Box(pos, size));
    }

    /**
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width
     * @param height the height
     */
    public GameObject(double x, double y, double width, double height) {
        this(new Box(x, y, width, height));
    }

    @Override
    public boolean remove(ArrayList<IGameObject<R>> objects, IGameObject<R> obj, int index) {
        boolean remove = obj == this;
        if (remove) objects.remove(index);
        return remove;
    }

    @Override
    public void onMount() {
    }

    @Override
    public void remove() {
        getLogic().remove(this);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateMouseHover();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updateMouseHover();
    }

    @Override
    public void update() {
        if (mouseHover())
            mouseHovered();
    }

    @Override
    public IGraphic<R> getGraphic() {
        return graphic;
    }

    /**
     * @return if the objects graphic exists
     */
    public boolean hasGraphic() {
        return getGraphic() != null;
    }

    /**
     * Set the graphic for this object to use.
     * 
     * @param graphic the graphic to set
     */
    public void setGraphic(IGraphic<R> graphic) {
        this.graphic = graphic;
    }

    /**
     * Update the {@link #updateMouseHover()} state.
     */
    protected void updateMouseHover() {
        updateMouseHover(Game.getRendererInstance().getCamera().getDisplayCoordinates(this));
    }

    /**
     * Update the {@link #updateMouseHover()} state.
     */
    public void updateMouseHover(Vector2D pos) {
        Rectangle r = getBox().getRectangle();
        r.setLocation((int) pos.getX(), (int) pos.getY());

        boolean newState = r.contains(
                new Point((int) getMouseInput().getPosition().getX(), (int) getMouseInput().getPosition().getY()));

        if (newState && !mouseHover())
            mouseEntered();
        else if (!newState && mouseHover())
            mouseExited();

        mouseHover = newState;
    }

    /**
     * Invoked when this object is being hovered upon by the mouse.
     */
    protected void mouseHovered() {}

    /**
     * Invoked when the mouse enters the bounds of this object.
     */
    protected void mouseEntered() {}

    /**
     * Invoked when the mouse exits the bounds of this object.
     */
    protected void mouseExited() {}

    /**
     * Get a copy of the box and set it's position.
     * 
     * @param position the position
     * @return a copy of the box with another position
     */
    public Box getBox(Vector2D position) {
        return getBox().copy().setPosition(position);
    }

    /**
     * Get the size.
     * 
     * @return the size
     */
    public Vector2D getSize() {
        return getBox().getSize();
    }

    /**
     * Set the size of the object.
     * 
     * @param width the width
     * @param height the height
     */
    public void setSize(double width, double height) {
        getSize().set(width, height);
    }

    /**
     * Set the position.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setPosition(double x, double y) {
        getBox().setPosition(x, y);
    }

    /**
     * @return the rotation in radians
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * @param rotation the rotation to set in radians
     */
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    /**
     * @param angle the rotation in radians
     */
    public void rotate(double angle) {
        this.rotation += angle;
    }

    /**
     * 
     * @param pos
     */
    public void setPosition(Vector2D pos) {
        setPosition(pos.getX(), pos.getY());
    }

    /**
     * 
     * @param x
     * @param y
     */
    public void translate(double x, double y) {
        getBox().translate(x, y);
    }

    /**
     * 
     * @param dp
     */
    public void translate(Point dp) {
        translate(dp.x, dp.y);
    }

    /**
     * 
     * @param d
     */
    public void translate(Vector2D d) {
        getBox().translate(d);
    }

    /**
     * @return if the mouse is hovering over this object
     */
    public boolean mouseHover() {
        return mouseHover;
    }

    /**
     * Get the size and position.
     * 
     * @return the box
     */
    public Box getBox() {
        return box;
    }

    /**
     * Get the mouse input of the current game instance.
     * 
     * @return the mouse input
     */
    protected IMouseInput getMouseInput() {
        return getLogic().getMouseInput();
    }

    /**
     * Get the logic of the current game instance.
     * 
     * @param <L> the type of logic
     * @return the logic
     */
    @SuppressWarnings("unchecked")
    protected <L extends AbstractLogic<R>> L getLogic() {
        return (L) Game.getInstance().getLogic();
    }

    /**
     * Get the state of the current game instance.
     * 
     * @param <S> the type of state
     * @return the state
     */
    protected <S extends State> S getState() {
        return Game.getLogicInstance().getState();
    }

    /**
     * @param <W>
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <W extends Window<R>> W getWindow() {
        return (W) Game.getWindowInstance();
    }

}