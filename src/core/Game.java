package core;

import core.swing.SwingRenderer;

/**
 * A {@code Game} is responsible for encapsulating and handling the communication between and management of {@link AbstractLogic} and {@link SwingRenderer}.
 * 
 * <p>Extend {@link AbstractLogic} and use a {@link SwingRenderer} with a {@link GameBuilder} to construct a {@code Game} instance. Finally, use {@link Game#run(Game)} to start the game.
 * 
 * @see GameBuilder
 * @see SwingRenderer
 * @see AbstractLogic
 */
public class Game implements IGame {

    /**
     * 
     */
    private static Game game;

    /**
     * 
     */
    private AbstractLogic<?> logic;

    /**
     * 
     */
    private IRenderer<?> renderer;

    /**
     * 
     */
    private IEngine engine;

    /**
     * Create a {@code Game} using a logic instance. Using this constructor is not recommended,
     * use a {@link GameBuilder} for an easier time.
     * 
     * @param logic the logic to use
     */
    protected Game(IEngine engine, AbstractLogic<?> logic, IRenderer<?> renderer) {
        setEngine(engine);
        setLogic(logic);
        setRenderer(renderer);
    }

    /**
     * Create a game with default settings from a logic instance.
     * 
     * @param logic the logic to use
     * 
     * @see {@link AbstractLogic}
     * @see {@link Game#setInstance(Game)}
     * @see {@link Game#create()}
     */
    public static void create(IEngine engine, AbstractLogic<?> logic, IRenderer<?> renderer) {
        setInstance(new Game(engine, logic, renderer));
    }

    /**
     * Convenience method for creating, setting the instance of, running and showing a game.
     * 
     * @param game the game to run
     * 
     * @see {@link Game#setInstance(Game)}.
     * @see {@link Game#run()}
     * @see {@link Game#show()}.
     */
    public static void run(Game game) {
        Game.setInstance(game);
        game.run();
    }

    /**
     * Run the logic of the game.
     */
    public void run() {
        getEngine().setup();
        getLogic().run();
        getWindow().run();
    }

    /**
     * @return the renderer
     */
    public IRenderer<?> getRenderer() {
        return renderer;
    }

    /**
     * @return
     */
    public Window<?> getWindow() {
        return getRenderer().getWindow();
    }
    
    /**
     * @return the logic
     */
    public AbstractLogic<?> getLogic() {
        return logic;
    }

    /**
     * @return the engine
     */
    public IEngine getEngine() {
        return engine;
    }

    /**
     * @param logic the logic to set
     */
    public void setLogic(AbstractLogic<?> logic) {
        this.logic = logic;
    }

    /**
     * @param renderer the renderer to set
     */
    public void setRenderer(IRenderer<?> renderer) {
        this.renderer = renderer;
    }

    /**
     * @param engine the engine to set
     */
    public void setEngine(IEngine engine) {
        this.engine = engine;
    }

    /**
     * Set the static (singleton) instance of {@code Game}.
     * @param game the game instance
     */
    public static void setInstance(Game game) {
        Game.game = game;
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
     * Get the {@link AbstractLogic} instance.
     * 
     * @param <L> The type of logic. Purpose for this generic type is to allow the user to get their logic without casting it, which is kind of annoying tbh.
     * 
     * @return the logic instance
     */
    @SuppressWarnings("unchecked")
    public static <L extends AbstractLogic<?>> L getLogicInstance() {
        return (L) getInstance().getLogic();
    }
    
}