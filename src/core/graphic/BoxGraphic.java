package core.graphic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import core.IGraphic;
import core.math.Box;
import core.math.Vector2D;
import core.swing.SwingGraphic;
import core.swing.SwingRenderer;

/**
 * A graphic that represents a rectangle. This graphic uses the {@link SwingRenderer}.
 * 
 * @see SwingGraphic
 * @see IGraphic
 * @see SwingRenderer
 */
public class BoxGraphic extends SwingGraphic {

    /**
     * The box of the graphic.
     */
    private Box box;

    /**
     * The transform mostly represents the rotation of the graphic.
     */
    private AffineTransform transform;

    /**
     * The renderer.
     */
    private SwingRenderer renderer;

    /**
     * The rotation of the graphic.
     */
    private double rotation;

    /**
     * When passing a {@link Box} to a {@link BoxGraphic} it uses that exact instance of the box.
     * Meaning that the graphic will change place if one transforms the box anywhere else in the program.
     * This is very convenient I think. Though, this heavy use of object mutation may sometimes result in confusing bugs to try and sort out.
     * 
     * @param box the box to use
     */
    public BoxGraphic(Box box) {
        super();
        setBox(box);

        transform = new AffineTransform();
    }

    /**
     * 
     * @param box the box to use
     * @param color the color to use
     * 
     * @see #BoxGraphic(Box)
     */
    public BoxGraphic(Box box, Color color) {
        this(box);
        setColor(color);
    }

    /**
     * 
     * @param box the box to use
     * @param rotation
     */
    public BoxGraphic(Box box, double rotation) {
        this(box);
        rotate(rotation);
    }

    @Override
    public void render(Graphics2D g, SwingRenderer renderer, Vector2D screenPos) {
        DrawHelper.drawBox(getBox().copy().setPosition(screenPos), g);
    }

    @Override
    public void render(SwingRenderer renderer, Vector2D pos) {
        renderSetup(renderer, pos);
        super.render(renderer, pos);
    }

    /**
     * Setup the rendering.
     * 
     * @param renderer the renderer
     * @param pos the position on the screen to render the graphic
     */
    protected void renderSetup(SwingRenderer renderer, Vector2D pos) {

        setRenderer(renderer);

        Graphics2D graphics = getGraphics();
        Vector2D center = getRenderer().getCamera().getDisplayCoordinates(getBox().getCenter());
        AffineTransform transform = new AffineTransform();

        transform.rotate(getRotation(), center.getX(), center.getY());

        graphics.setColor(getColor());
        graphics.setTransform(getTransform());
    }


    /**
     * @return the box
     */
    public Box getBox() {
        return box;
    }

    /**
     * @param pos the position to use
     * @return the box
     */
    public Box getBox(Vector2D pos) {
        return box.copy().setPosition(pos);
    }

    /**
     * @return the graphics
     */
    public Graphics2D getGraphics() {
        return getRenderer().getGraphics();
    }

    /**
     * @return the transform
     */
    public AffineTransform getTransform() {
        return transform;
    }

    /**
     * @return the renderer
     */
    public SwingRenderer getRenderer() {
        return renderer;
    }

    /**
     * @return the rotation
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * @param renderer the renderer to set
     */
    public void setRenderer(SwingRenderer renderer) {
        this.renderer = renderer;
    }

    /**
     * @param rotation the rotation to set
     */
    public void rotate(double angle) {
        this.rotation += angle;
    }

    /**
     * @param box the box to set
     */
    public void setBox(Box box) {
        this.box = box;
    }
    
}