package core.math;

import java.awt.Dimension;
import java.awt.Point;

/**
 * A very basic vector class.
 * 
 * @author Anton Ekström
 */
public class Vector2D {

    // The x and y component of this vector.
    double x, y;

    /**
     * Construct a vector.
     * 
     * @return the vector
     */
    public static Vector2D create() {
        return new Vector2D();
    }

    /**
     * Construct a vector.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * 
     * @return the vector
     */
    public static Vector2D create(double x, double y) {
        return new Vector2D(x, y);
    }

    /**
     * Construct a vector.
     */
    public Vector2D() {
        this(1, 1);
    }

    /**
     * Construct a vector from a point.
     * 
     * @param p the point to use
     */
    public Vector2D(Point p) {
        set(p);
    }

    /**
     * Construct a vector.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construct a vector.
     * 
     * @param d the dimension to use
     */
    public Vector2D(Dimension d) {
        this(d.getWidth(), d.getHeight());
    }

    /**
     * Construct a vector.
     * 
     * @param v the vector to copy
     */
    public Vector2D(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Copy the vector.
     * 
     * @return a copy of this vector
     */
    public Vector2D copy() {
        return new Vector2D(this);
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", getX(), getY());
    }

    /**
     * Calculate the delta.
     * 
     * @param v a vector
     * 
     * @return the delta between the vectors
     */
    public Vector2D delta(Vector2D v) {
        return copy().sub(v);
    }

    /**
     * Normalize this vector.
     * 
     * @return this vector
     */
    public Vector2D normalize() {

        double l = length();

        x /= l;
        y /= l;

        return this;
    }

    /**
     * Return the length of this vector.
     * 
     * @return the length
     */
    public double length() {
        return Math.sqrt(sqlength());
    }

    /**
     * Return the squared length of this vector.
     * 
     * @return the length
     */
    public double sqlength() {
        return (x * x) + (y * y);
    }

    /**
     * Calculate the dot product of this vector and another.
     * 
     * @param v another vector
     * 
     * @return the dot product of the two vectors
     */
    public double dot(Vector2D v) {
        return (x * v.x) + (y * v.y);
    }

    /**
     * Calculate the angle of this vector and another.
     * 
     * @param v another vector
     * 
     * @return the angle of the two vectors
     */
    public double angle(Vector2D v) {
        return Math.sin(dot(v) / (length() * v.length()));
    }

    /**
     * Change sign of each component to the opposite.
     * 
     *  @return this vector
     */
    public Vector2D negate() {
        this.x = -this.x;
        this.y = -this.y;

        return this;
    }

    /**
     * Multiply this vector by a scalar on each component.
     * 
     * @param m the scalar
     * 
     * @return this vector
     */
    public Vector2D mul(double m) {
        this.x *= m;
        this.y *= m;
        return this;
    }

    /**
     * Divide this vector by a scalar on each component.
     * 
     * @param d the scalar
     * 
     * @return this vector
     */
    public Vector2D div(double d) {
        this.x /= d;
        this.y /= d;
        return this;
    }

    /**
     * Add another vector to this one.
     * 
     * @param v the other vector
     * 
     * @return this vector
     */
    public Vector2D add(Vector2D v) {

        this.x += v.getX();
        this.y += v.getY();

        return this;
    }

    /**
     * Add to the x and y component.
     * 
     * @param x the x component
     * @param y the y component
     * 
     * @return this vector
     */
    public Vector2D add(double x, double y) {
        addX(x);
        addY(y);
        return this;
    }

    /**
     * Add a number to both components.
     * 
     * @param d the number to add
     * @return this vector
     */
    public Vector2D add(double d) {
        addX(d);
        addY(d);
        return this;
    }

    /**
     * Subtract another vector from this one.
     * 
     * @param v the other vector
     * 
     * @return this vector
     */
    public Vector2D sub(Vector2D v) {
        this.x -= v.getX();
        this.y -= v.getY();

        return this;
    }

    /**
     * Subtract from this vector.
     * 
     * @param x the x component
     * @param y the y component
     * 
     * @return this vector
     */
    public Vector2D sub(double x, double y) {
        addX(-x);
        addY(-y);
        return this;
    }

    /**
     * Subtract a number to both components.
     * 
     * @param d the number to subtract
     * @return this vector
     */
    public Vector2D sub(double d) {
        subX(d);
        subY(d);
        return this;
    }

    /**
     * Set the components of the vector.
     * 
     * @param x the x component
     * @param y the y component
     * 
     * @return this vector
     */
    public Vector2D set(double x, double y) {
        setX(x);
        setY(y);
        return this;
    }

    /**
     * Use the x and y of a {@link Point}.
     * 
     * @param p the point
     * 
     * @return this vector
     */
    public Vector2D set(Point p) {
        setX(p.getX());
        setY(p.getY());
        return this;
    }

    /**
     * Set x and y to another vector.
     * 
     * @param v another vector
     * 
     * @return this vector
     */
    public Vector2D set(Vector2D v) {
        setX(v.getX());
        setY(v.getY());
        return this;
    }

    /**
     * @param x
     * 
     * @return this vector
     */
    public Vector2D addX(double x) {
        this.x += x;
        return this;
    }

    /**
     * @param y
     * 
     * @return this vector
     */
    public Vector2D addY(double y) {
        this.y += y;
        return this;
    }

    /**
     * @param x
     * 
     * @return this vector
     */
    public Vector2D subX(double x) {
        this.x -= x;
        return this;
    }

    /**
     * @param y
     * 
     * @return this vector
     */
    public Vector2D subY(double y) {
        this.y -= y;
        return this;
    }

    /**
     * @param x the x to set
     */
    public Vector2D setX(double x) {
        this.x = x;
        return this;
    }

    /**
     * @param y the y to set
     */
    public Vector2D setY(double y) {
        this.y = y;
        return this;
    }

    /**
     * @param d
     * 
     * @return this vector
     */
    public Vector2D translate(Vector2D d) {
        return add(d);
    }

    /**
     * @param d
     * 
     * @return this vector
     */
    public Vector2D translateX(double d) {
        return addX(d);
    }

    /**
     * @param d
     * 
     * @return this vector
     */
    public Vector2D translateY(double d) {
        return addY(d);
    }

    /**
     * @param dx
     * @param dy
     * 
     * @return this vector
     */
    public Vector2D translate(double dx, double dy) {
        return add(dx, dy);
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }
    
}
