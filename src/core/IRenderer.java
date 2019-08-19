package core;

import java.util.ArrayList;
import java.util.function.Consumer;

import core.graphic.ICamera;
import core.math.Vector2D;
import core.obj.IGameObject;

/**
 * A renderer that is capable of rendering things. It uses a {@link Window} to render {@link IGraphic} objects
 * onto it. Many methods have a default and should not be overrided.
 */
public interface IRenderer <R extends IRenderer<R>> {

    /**
     * The type of renderer.
     */
    public static enum Type {
        SWING,
        JAVAFX,
        OPENGL
    }

    /**
     * 
     */
    public class RenderEvent <R extends IRenderer<R>> {

        R renderer;
        IGraphic<R> graphic;
        Vector2D screenPos;

        public RenderEvent(R renderer, IGraphic<R> graphic, Vector2D screenPos) {
            this.graphic = graphic;
            this.screenPos = screenPos;
            this.renderer = renderer;
        }

        /**
         * @return the graphic
         */
        public IGraphic<R> getGraphic() {
            return graphic;
        }

        /**
         * @return the screenPos
         */
        public Vector2D getScreenPos() {
            return screenPos;
        }

        /**
         * @return the renderer
         */
        public R getRenderer() {
            return renderer;
        }

        /**
         * Process the event and render the graphic.
         */
        public void process() {
            graphic.render(getRenderer(), screenPos);
        }
    }

    /**
     * Intitialize the renderer with a {@link Window}.
     * 
     * @param window the window
     */
    public void build(Window<R> window, ICamera camera);

    /**
     * Start the rendering cycle and target the currently set {@link Window} of this renderer.
     * 
     * <p>By starting the rendering process with this method you can then proceed to render {@link IGraphic} objects with {@link #onRender(IGraphic)} and show them on the {@link Window}.
     * 
     */
    public default void startRendering(Consumer<R> renderConsumer) {
        // let the consumer do the rendering and automatically process the graphics afterwards
        getWindow().render((r) -> {
            renderConsumer.accept(r);
            processRendering();
        });
    }

    /**
     * Render an {@link IGraphic} object to the current {@link Window}.
     * 
     * <p>Before rendering a graphic, the rendering process has to be started with {@link #render()}. Usually, the process has already been started when rendering.
     * This method is called before {@link AbstractLogic#onRender(javax.swing.Renderer)}.
     * 
     * @param graphic the graphic to render
     * @param screenPos the position on the screen to render the graphic
     */
    public default void pushGraphic(IGraphic<R> graphic, Vector2D screenPos) {
        if (graphic != null)
            getRenderQueue().add(new RenderEvent<R>(getSelf(), graphic, screenPos));
    }

    /**
     * Render an object.
     * 
     * @param obj the object to render
     * 
     * @see #onRender(IGraphic)
     *
     */
    public default void pushObject(IGameObject<R> obj) {
        if (obj.hasGraphic())
        pushGraphic(obj.getGraphic(), getCamera().getDisplayCoordinates(obj));
    }

    /**
     * Render multiple objects.
     * 
     * @param objects the objects to render
     * 
     * @see #pushObject(IGameObject)
     */
    public default void pushObjects(ArrayList<IGameObject<R>> objects) {
        for (IGameObject<R> obj : objects) {
            pushObject(obj);
        }
    }

    /**
     * Get this renderer with the correct type.
     * 
     * <p>This had to be done for my overusage of generic types to work correctly.
     * Otherwise the whole renderqueue and {@link RenderEvent} thing wouldn't work.
     * It is kind of tricky for a generic typed object to use it's own type for method arguments and such, this was the only solution I could find.
     * 
     * @return this renderer
     */
    public R getSelf();

    /**
     * Process the graphics that have been queued up to be rendered.
     */
    public void processRendering();

    /**
     * @return the queue of graphics
     */
    public ArrayList<RenderEvent<R>> getRenderQueue();

    /**
     * Get the type of renderer this is.
     * 
     * @return the type
     */
    public Type getType();

    /**
     * Set the window that this renderer is using.
     * 
     * @param window the window
     */
    public void setWindow(Window<R> window);

    /**
     * Get the current window of this renderer.
     * 
     * @return the window
     */
    public Window<R> getWindow();

    /**
     * Set the camera.
     * 
     * @param camera the camera
     */
    public void setCamera(ICamera camera);

    /**
     * Get the current camera.
     * 
     * @return the camera
     */
    public ICamera getCamera();
    
}