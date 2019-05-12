package core;

import core.swing.SwingRenderer;

/**
 * A {@code Game} is responsible for encapsulating and handling the communication between and management of {@link Logic} and {@link SwingRenderer}.
 * 
 * <p>Extend {@link Logic} and use a {@link SwingRenderer} with a {@link GameBuilder} to construct a {@code Game} instance. Finally, use {@link Game#run(Game)} to start the game.
 * 
 * @see GameBuilder
 * @see SwingRenderer
 * @see Logic
 */
public class Game {

    // singleton instance of Game
    private static Game game;

    // game
    private Logic<?> logic;

    // graphics
    private IRenderer<?> renderer;

    // if the game has been initialized
    private boolean init = false;

    /**
     * Create a {@code Game} using a logic instance. Using this constructor is not recommended,
     * use a {@link GameBuilder} for an easier time.
     * 
     * @param logic the logic to use
     */
    public Game(Logic<?> logic, IRenderer<?> renderer) {
        setLogic(logic);
        setRenderer(renderer);
    }

    /**
     * Create a game with default settings from a logic instance.
     * 
     * @param logic the logic to use
     * 
     * @see {@link Logic}
     * @see {@link Game#setInstance(Game)}
     * @see {@link Game#create()}
     */
    public static void create(Logic<?> logic, IRenderer<?> renderer) {
        setInstance(new Game(logic, renderer));
        getInstance().create();
    }

    /**
     * Convenience method for creating, setting the instance of, running and showing a game.
     * 
     * @param game the game to run
     * 
     * @see {@link Game#create()}
     * @see {@link Game#initialize()}
     * @see {@link Game#show()}.
     * @see {@link Game#setInstance(Game)}.
     */
    public static void run(Game game) {
        Game.setInstance(game);
        game.create();
        game.initialize();
        game.show();
    }

    /**
     * Run the logic of the game.
     */
    public void initialize() {
        getLogic().initialize();
    }

    /**
     * Create/initialize the game.
     */
    public void create() {

        if (isCreated()) return;

        init = true;
    }

    /**
     * Show the game window.
     */
    public void show() {
        getWindow().show();
    }

    /**
     * Convenience method for setting the static instance of game to this object.
     */
    public void setInstance() {
        Game.setInstance(this);
    }

    /**
     * @return the renderer
     */
    public IRenderer<?> getRenderer() {
        return renderer;
    }

    public Window<?> getWindow() {
        return getRenderer().getWindow();
    }
    
    /**
     * @return the logic
     */
    public Logic<?> getLogic() {
        return logic;
    }

    /**
     * @return the init
     */
    public boolean isCreated() {
        return init;
    }

    /**
     * @param logic the logic to set
     */
    public void setLogic(Logic<?> logic) {
        this.logic = logic;
    }

    /**
     * @param renderer the renderer to set
     */
    public void setRenderer(IRenderer<?> renderer) {
        this.renderer = renderer;
    }

    /**
     * @return the game instance
     */
    public static Game getInstance() {
        return game;
    }

    /**
     * @return the frame instance
     */
    public static Window<?> getWindowInstance() {
        return getRendererInstance().getWindow();
    }

    /**
     * Get the renderer instance.
     * 
     * @param <R> The type of renderer. Purpose for this generic type is to allow the user to get their renderer without casting it, which is kind of annoying tbh.
     * 
     * @return the renderer
     */
    @SuppressWarnings("unchecked")
    public static <R extends IRenderer<?>> R getRendererInstance() {
        return (R) getInstance().getRenderer();
    }

    /**
     * Get the {@link Logic} instance.
     * 
     * @param <L> The type of logic. Purpose for this generic type is to allow the user to get their logic without casting it, which is kind of annoying tbh.
     * 
     * @return the logic instance
     */
    @SuppressWarnings("unchecked")
    public static <L extends Logic<?>> L getLogicInstance() {
        return (L) getInstance().getLogic();
    }

    /**
     * Set the static (singleton) instance of {@code Game}.
     * @param game the game instance
     */
    public static void setInstance(Game game) {
        Game.game = game;
    }
    
}