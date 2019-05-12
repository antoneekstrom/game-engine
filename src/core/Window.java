package core;

import java.awt.Dimension;
import java.io.File;
import java.util.function.Consumer;

import core.math.Vector2D;

/**
 * A window. A {@code Window} is supposed to represent the window in which the application takes place
 * and encapsulates everything regarding to that.
 */
public abstract class Window <R extends IRenderer<R>> {

    /**
     * The render this window uses.
     */
    private R renderer;

    /**
     * The module that is responsible for handling input from the users keyboard.
     */
    protected IKeyInput keyInput;

    /**
     * The module that is responsible for handling input from the users mouse.
     */
    protected IMouseInput mouseInput;

    /**
     * Create the window.
     */
    public Window() {
    }

    /**
     * Initialize the window.
     */
    public abstract void build(Game game);

    /**
     * Make the window visible.
     */
    public abstract void show();

    /**
     * Start the window.
     */
    public abstract void run();

    /**
     * This method is supposed to do setup to allow subsequent rendering calls from an {@link IRenderer} to be able to render graphics to the {@link Window}.
     * @param renderConsumer the consumer to be invoked when graphics are ready to be rendered
     */
    public abstract void render(Consumer<R> renderConsumer);

    /**
     * Maximise the size of the window.
     */
    public abstract void maximize();

    /**
     * Maximise the window and make it take up the whole screen.
     */
    public abstract void fullscreen();

    /**
     * Set the title of the window that is shown in the title bar.
     * 
     * @param title the title
     */
    public abstract void setTitle(String title);

    /**
     * Set the icon of this window that is shown in the top left corner of the title bar.
     * @param icon the icon file to use
     */
    public abstract void setIcon(File icon);

    /**
     * Invoked when the window is resized.
     * 
     * @param size the new size of the window
     */
    public abstract void onResize(Vector2D size);

    /**
     * Add a consumer that will be invoked when the window is resized.
     * 
     * @param consumer the consumer
     */
    public abstract void addResizeListener(Consumer<Vector2D> consumer);

    /**
     * Remove a resize listener.
     * 
     * @param consumer the consumer
     */
    public abstract void removeResizeListener(Consumer<Vector2D> consumer);

    /**
     * Get the size of the window.
     * 
     * @return the size of the window
     */
    public abstract Dimension getSize();

    /**
     * Get the size of the window.
     * 
     * @return
     */
    public Vector2D getSizeVector() {
        return new Vector2D(getSize());
    }

    /**
     * Get the center of the window.
     * 
     * @return the center
     */
    public Vector2D getCenter() {
        return getSizeVector().div(2);
    }

    /**
     * Get the key input.
     * 
     * @return the key input
     */
    public IKeyInput getKeyInput() {
        return keyInput;
    }

    /**
     * Get the mouse input.
     * 
     * @return the mouse input
     */
    public IMouseInput getMouseInput() {
        return mouseInput;
    }

    /**
     * Get the renderer.
     * 
     * @return the renderer
     */
    public R getRenderer() {
        return renderer;
    }

    /**
     * Set the renderer. When set, this renderer will use this window to show it's graphics that it receives to render.
     * 
     * @param renderer the renderer
     */
    public void setRenderer(R renderer) {
        this.renderer = renderer;
    }

}