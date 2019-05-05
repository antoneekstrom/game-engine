package core;

/**
 * GameBuilder TODO
 */
public class GameBuilder <W extends Window<R>, R extends IRenderer<R>> {

    Logic<R> logic;
    W window;
    R renderer;

    /**
     * 
     * @return
     */
    public Game build() { //TODO

        Game game = new Game(getLogic(), getRenderer());

        getWindow().build(game);
        getLogic().build(getWindow());
        getRenderer().build(getWindow());

        return game;
    }

    /**
     * @return the logic
     */
    public Logic<R> getLogic() {
        return logic;
    }

    /**
     * @param logic the logic to set
     */
    public void setLogic(Logic<R> logic) {
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
     * @param window the window to set
     */
    public void setWindow(W window) {
        this.window = window;
    } 
    
}