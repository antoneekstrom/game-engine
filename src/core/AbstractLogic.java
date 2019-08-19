package core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Consumer;

import core.Request.RequestType;
import core.obj.IGameObject;
import core.state.IState;
import core.state.State;
import core.state.StateHandler;
import core.swing.SwingRenderer;
import core.threads.ThreadHandler;
import core.ui.UIManager;

/**
 * The logic component of a {@link core.Game}.
 * 
 * <p>Handles the {@link GamePanel} and the {@link SwingRenderer} which is used to render the game. Mainly takes care of the logic of the game.
 * 
 * <p> {@link AbstractLogic#setup()} is run sometime at the beginning of starting the game.
 * <p> Add an {@link IGameObject} with {@link AbstractLogic#add(IGameObject)} and it will be updated and painted when the logic has started.
 * <p> {@link State} is used to store the state of the game. Get with {@link AbstractLogic#getState()} and handle with {@link AbstractLogic#getStateHandler()}.
 * <p> {@link UIManager} stores the UI.
 * 
 * <p>Extend {@code Logic} and use a {@code Renderer} with a {@link GameBuilder} to construct a {@link Game} instance. Finally, use {@link Game#run(Game)} to start the game.
 */
public abstract class AbstractLogic <R extends IRenderer<R>> {
    
    /**
     * Where the graphics are drawn.
     */
    Window<R> window;

    /**
     * Queue for requests that are unable to happen in the middle of the loop execution, like removing an object.
     */
    LinkedList<Request<AbstractLogic<R>>> requests;

    /**
     * Manages UI components.
     */
    private UIManager<R> uiManager;

    /**
     * Where the state of the game is stored.
     */
    IState<R> state;

    /**
     * Do things with state.
     */
    StateHandler stateHandler;

    /**
     * Handles tasks.
     */
    ThreadHandler threadHandler;

    /**
     * 
     */
    protected AbstractLogic() {}

    /**
     * 
     */
    public void run() {

        requests = new LinkedList<>();

        uiManager = new UIManager<>();

        threadHandler = new ThreadHandler();

        stateHandler = new StateHandler();

        preSetup();

        state = createState();
        state.setup();

        getWindow().run();

        setup();
    }

    /**
     * Process requests.
     */
    private void processRequests() {
        while (requests.size() > 0) {

            Request<AbstractLogic<R>> r = requests.pop();
            r.getAction().accept(this);
        }
    }

    /**
     * @return
     */
    protected abstract IState<R> createState();

    /** 
     * 
    */
    protected void preSetup() {
    }

    /**
     * Is run sometime at the start of the game.
     * 
     * @see {@link Game#run()}
     */
    protected abstract void setup();

    /**
     * 
     */
    public void onEngineUpdate(double delta) {

        state.onUpdate();

        // Call the update method of this logic
        update();

        // Process requests of things that couldn't be processed while looping over game objects
        processRequests();

        // Initialize rendering
        R renderer = getRenderer();
        renderer.startRendering(this::onRender);
    }

    /**
     * Here you are supposed to render things. This method is called after {@link IRenderer#render()} has been called.
     * 
     * @param renderer the renderer that is used
     */
    protected abstract void onRender(R renderer);

    /**
     * Update is called on a timer.
     */
    protected abstract void update();

    /**
     * Add an object.
     * 
     * @param obj the object to add
     */
    public void add(IGameObject<R> obj) {
        getObjects().add(obj);
        obj.onMount();
    }

    /**
     * Add a collection of objects.
     * 
     * @param objs the objects to add
     */
    public void add(Collection<IGameObject<R>> objs) {
        getObjects().addAll(objs);
    }

    /**
     * Get the state.
     * @param <S> the type of state
     * @return the state
     */
    @SuppressWarnings("unchecked")
    public <S extends IState<?>> S getState() {
        return (S) state;
    }

    /**
     * @return the threadHandler
     */
    public ThreadHandler getThreadHandler() {
        return threadHandler;
    }

    /**
     * @return the renderer
     */
    public R getRenderer() {
        return getWindow().getRenderer();
    }

    /**
     * @return the stateHandler
     */
    public StateHandler getStateHandler() {
        return stateHandler;
    }

    /**
     * @return
     */
    public IEngine getEngine() {
        return Game.getInstance().getEngine();
    }

    /**
     * @return the panel
     */
    public Window<R> getWindow() {
        return window;
    }

    /**
     * @return the mouse
     */
    public IMouseInput getMouseInput() {
        return getWindow().getMouseInput();
    }

    /**
     * @return the keyInput
     */
    public IKeyInput getKeyInput() {
        return getWindow().getKeyInput();
    }

    /**
     * @return the objects
     */
    public ArrayList<IGameObject<R>> getObjects() {
        return state.getObjects();
    }

    /**
     * @return the uiManager
     */
    public UIManager<R> getUIManager() {
        return uiManager;
    }

    /**
     * Add a {@link Request} to be processed.
     * @param req the request
     */
    public void dispatch(Request<AbstractLogic<R>> req) {
        requests.add(req);
    }

    /**
     * Dispatch a request with a consumer.
     * @param consumer the consumer
     */
    public void dispatch(Consumer<AbstractLogic<R>> consumer) {
        dispatch(new Request<>(RequestType.OTHER, consumer));
    }

    /**
     * Remove game objects.
     * 
     * <p>Adding the SafeVarargs annotation suppresses the warning, which is nice. The method has to be final though.
     * I am unsure if my handling of the objects in the arguments is actually safe, but whatever.
     * 
     * @param objs the objects to remove
     */
    @SafeVarargs
    public final void remove(IGameObject<R>... objs) {
        dispatch(new RemovalRequest<R, AbstractLogic<R>>(objs));
    }

    /**
     * @param window the window to set
     */
    public void setWindow(Window<R> window) {
        this.window = window;
    }

    /**
     * @param state the state to set
     */
    public void setState(IState<R> state) {
        this.state = state;
    }

    /**
     * @param stateHandler the stateHandler to set
     */
    public void setStateHandler(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }
    
}