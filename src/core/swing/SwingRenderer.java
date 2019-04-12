package core.swing;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

import core.Game;
import core.IRenderer;
import core.SwingWindow;
import core.Window;
import core.graphic.Camera;
import core.graphic.ICamera;
import core.obj.IGameObject;

/**
 * Used to render {@link IGameObject}.
 * 
 * @see Window
 * @see Game
 */
public class SwingRenderer implements IRenderer<SwingRenderer> {

    /**
     * 
     */
    private Graphics2D graphics;

    /**
     * 
     */
    private Window<?> window;

    /**
     * 
     */
    private ICamera camera;

    /**
     * 
     */
    Consumer<SwingRenderer> renderMethod;

    /**
     * 
     */
    ArrayList<RenderEvent<SwingRenderer>> renderQueue;

    /**
     * 
     */
    private boolean ready = false;

    /**
     * 
     */
    public SwingRenderer() {
    }

    /**
     * 
     * @param window
     * @param camera
     */
    public SwingRenderer(SwingWindow window, Camera camera) {
        setWindow(window);
        setCamera(camera);
        window.setRenderer(this);

        renderQueue = new ArrayList<>();
    }

    /**
     * 
     * @param window
     */
    public SwingRenderer(SwingWindow window) {
        this(window, new Camera());
    }

    /**
     * Sort the queue by layer index.
     */
    protected class QueueSorter implements Comparator<RenderEvent<SwingRenderer>> {
        @Override
        public int compare(RenderEvent<SwingRenderer> o1, RenderEvent<SwingRenderer> o2) {

            int i1 = o1.getGraphic().getLayerIndex();
            int i2 = o2.getGraphic().getLayerIndex();

            if (i1 == i2) return 0;

            return (i1 > i2 ? -1 : (i2 > i1 ? 1 : 0));
        }
    }

    @Override
    public void processRendering() {
        ArrayList<RenderEvent<SwingRenderer>> list = getRenderQueue();

        list.sort(new QueueSorter());

        // I am using a i-loop instead of an enhanced/foreach loop because I think it is very slightly more performant,
        // and I am doing a lot of unnecessary things that may be kind of slow so I am compensating a little for that
        //
        // most likely won't matter at all though
        //
        // I iterate from the back so that the removal och items won't mess things up
        for (int i = list.size() - 1; i > 0; i--) {
            list.remove(i).process();
        }
    }

    @Override
    public void initialize(Window<?> window) {

        setWindow(window);
        setCamera(new Camera());

        ready = window != null;
    }

    @Override
    public Type getType() {
        return Type.SWING;
    }

    @Override
    public Window<?> getWindow() {
        return window;
    }

    @Override
    public void setWindow(Window<?> window) {
        this.window = window;
    }

    @Override
    public void setRenderMethod(Consumer<SwingRenderer> renderMethod) {
        this.renderMethod = renderMethod;
    }

    @Override
    public Consumer<SwingRenderer> getRenderMethod() {
        return renderMethod;
    }

    @Override
    public ArrayList<RenderEvent<SwingRenderer>> getRenderQueue() {
        return renderQueue;
    }

    /**
     * @param graphics the graphics to set
     */
    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    /**
     * @param camera the camera to set
     */
    public void setCamera(ICamera camera) {
        this.camera = camera;
    }

    /**
     * @return the graphics
     */
    public Graphics2D getGraphics() {
        return graphics;
    }

    /**
     * @return the camera
     */
    public ICamera getCamera() {
        return camera;
    }

    /**
     * @return the ready
     */
    public boolean isReady() {
        return ready;
    }

    @Override
    public SwingRenderer getSelf() {
        return this;
    }

}