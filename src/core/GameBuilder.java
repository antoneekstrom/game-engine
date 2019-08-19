package core;

import core.graphic.Camera;

/**
 * GameBuilder assembles a {@link Game} instance from a collection of components.
 * These components are {@link AbstractLogic}, {@link Window} and {@link IRenderer}. Creating
 * a {@link Game} instance by yourself should probably not be attempted as it is very confusing
 * and does not work well.
 */
public class GameBuilder <W extends Window<R>, R extends IRenderer<R>> {

    IEngine engine;
    AbstractLogic<R> logic;
    W window;
    R renderer;

    /**
     * Create the instance.
     * @return the {@link Game} instance
     */
    public Game build() throws Exception {

        if (logic == null) throw new Exception("A Logic component has to be set.");
        if (window == null) throw new Exception("A Window component has to be set.");
        if (renderer == null) throw new Exception("A Renderer component has to be set.");
        if (engine == null) throw new Exception("An Engine component has to be set.");

        Game game = new Game(getEngine(), getLogic(), getRenderer());

        getWindow().build(game);
        getLogic().setWindow(getWindow());
        getRenderer().build(getWindow(), new Camera());

        return game;
    }

    /**
     * @return the logic
     */
    public AbstractLogic<R> getLogic() {
        return logic;
    }

    /**
     * @param logic the logic to set
     */
    public void setLogic(AbstractLogic<R> logic) {
        this.logic = logic;
    }

    /**
     * @return the renderer
     */
    public R getRenderer() {
        return renderer;
    }

    /**
     * @param renderer the renderer to set
     */
    public void setRenderer(R renderer) {
        this.renderer = renderer;
    }

    /**
     * @return the window
     */
    public W getWindow() {
        return window;
    }

    /**
     * @return the engine
     */
    public IEngine getEngine() {
        return engine;
    }

    /**
     * @param engine the engine to set
     */
    public void setEngine(IEngine engine) {
        this.engine = engine;
    }

    /**
     * @param window the window to set
     */
    public void setWindow(W window) {
        this.window = window;
    } 
    
}