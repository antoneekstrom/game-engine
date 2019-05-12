package core.math;

import java.awt.Rectangle;

/**
 * A 2D box that has a position and size. Quite epic.
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

    /**
     * Get the rectangle object representing this box.
     * 
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        rectangle.setLocation((int)position.x, (int)position.y);
        rectangle.setSize((int)size.x, (int)size.y);
        return rectangle;
    }

    /**
     * Create a new Box that has the same properties as this one.
     * @return the copy
     */
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

    /**
     * @param d
     * @return
     */
    public Box translate(Vector2D d) {
        this.position.add(d);
        return this;
    }

    /**
     * @param dx
     * @param dy
     * @return
     */
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

    /**
     * @param width
     * @param height
     * @return
     */
    public Box setSize(double width, double height) {
        this.size.set(width, height);
        return this;
    }

    /**
     * @param w the width
     */
    public void setWidth(double w) {
        this.size.setX(w);
    }

    /**
     * Set a vector as this boxes position.
     * 
     * @param pos the vector
     */
    public void usePosition(Vector2D pos) {
        this.position = pos;
    }

    /**
     * @param h the height
     */
    public void setHeight(double h) {
        this.size.setY(h);
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