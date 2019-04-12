package core.math;

import java.awt.Rectangle;

/**
 * @author Anton Ekström
 * 
 * Box
 */
public class Box {

    private Vector2D position, size;

    private Rectangle rectangle;

    public Box() {
        position = new Vector2D();
        size = new Vector2D();
        rectangle = new Rectangle();
    }

    public Box(Box box) {
        this();
        setPosition(box.getPosition());
        setSize(box.getSize());
    }

    public Box(Vector2D position, double width, double height) {
        this();
        this.position.set(position);
        this.size.set(width, height);
    }

    public Box(double x, double y, double width, double height) {
        this();
        this.position.set(x, y);
        this.size.set(height, width);
    }

    public Box(Vector2D position, Vector2D size) {
        this();
        setPosition(position);
        setSize(size);
    }

    public Vector2D getCenter() {
        return getPosition().copy().add(getWidth() / 2, getHeight() / 2);
    }

    public boolean contains(Vector2D point) {
        return
            getX() <= point.getX() &&
            getMaxX() >= point.getX() &&
            getY() <= point.getY() &&
            getMaxY() >= point.getX();
    }

    /**
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        rectangle.setLocation((int)position.x, (int)position.y);
        rectangle.setSize((int)size.x, (int)size.y);
        return rectangle;
    }

    public Box copy() {
        return new Box(this);
    }

    /**
     * @return the position
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * @return the size
     */
    public Vector2D getSize() {
        return size;
    }

    /**
     * @param position the position to set
     */
    public Box setPosition(Vector2D position) {
        this.position.set(position);
        return this;
    }

    /**
     * @param x the x
     * @param y the y
     */
    public Box setPosition(double x, double y) {
        this.position.set(x, y);
        return this;
    }

    public Box translate(Vector2D d) {
        this.position.add(d);
        return this;
    }

    public Box translate(double dx, double dy) {
        this.position.add(dx, dy);
        return this;
    }

    /**
     * @param size the size to set
     */
    public Box setSize(Vector2D size) {
        this.size.set(size);
        return this;
    }

    public Box setSize(double width, double height) {
        this.size.set(width, height);
        return this;
    }

    public double getX() {
        return getPosition().getX();
    }

    public double getY() {
        return getPosition().getY();
    }

    public double getWidth() {
        return getSize().getX();
    }

    public double getHeight() {
        return getSize().getY();
    }

    public double getMaxX() {
        return getX() + getWidth();
    }

    public double getMaxY() {
        return getY() + getHeight();
    }

}