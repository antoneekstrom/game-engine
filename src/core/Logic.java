package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Consumer;

import javax.swing.Timer;

import core.Request.RequestType;
import core.obj.IGameObject;
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
 * <p> {@link Logic#setup()} is run sometime at the beginning of starting the game.
 * <p> Add an {@link IGameObject} with {@link Logic#add(IGameObject)} and it will be updated and painted when the logic has started.
 * <p> {@link State} is used to store the state of the game. Get with {@link Logic#getState()} and handle with {@link Logic#getStateHandler()}.
 * <p> {@link UIManager} stores the UI.
 * 
 * <p>Extend {@code Logic} and use a {@code Renderer} with a {@link GameBuilder} to construct a {@link Game} instance. Finally, use {@link Game#run(Game)} to start the game.
 */
public abstract class Logic <R extends IRenderer<R>> implements ActionListener {
    
    // Timer
    private Timer timer;

    /**
     * The interval the timer should update on
     */
    private int timerDelay = 10;

    /**
     * Where the graphics are drawn.
     */
    Window<R> window;

    /**
     * Logic can only run if ready is true.
     */
    private boolean ready = false;

    /**
     * If the game loop should be executed.
     */
    private boolean loop = false;

    /**
     * The list of objects that are updated by logic.
     */
    ArrayList<IGameObject<R>> objects;

    /**
     * Queue for requests that are unable to happen in the middle of the loop execution, like removing an object.
     */
    LinkedList<Request<Logic<R>>> requests;

    /**
     * Manages UI components.
     */
    private UIManager<R> uiManager;

    /**
     * Where the state of the game is stored.
     */
    State state;

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
    protected Logic() {}

    /**
     * Build the logic.
     * 
     * @param window the window to be used
     */
    public void build(Window<R> window) {
        setWindow(window);

        ready = window != null;
    }

    /**
     * 
     */
    public void initialize() {

        if (!ready) throw new IllegalStateException("Has not been initialized yet.");

        getWindow().run();

        objects = new ArrayList<>();

        requests = new LinkedList<>();

        uiManager = new UIManager<>();

        threadHandler = new ThreadHandler();

        timer = new Timer(timerDelay, this);

        state = new State();
        stateHandler = new StateHandler();

        setup();
    }

    /**
     * Process requests.
     */
    private void processRequests() {
        while (requests.size() > 0) {

            Request<Logic<R>> r = requests.pop();
            r.getAction().accept(this);
        }
    }

    /**
     * Is run sometime at the start of the game.
     * 
     * @see {@link Game#initialize()}
     */
    protected void setup() {}

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!loop) return;

        // Update game objects
        for (IGameObject<R> obj : objects) {
            obj.update();
        }

        // Call the update method of this logic
        update();

        // Process requests of things that couldn't be processed while looping over game objects
        processRequests();

        // Initialize rendering
        R renderer = getRenderer();
        renderer.startRendering(this::render);
    }

    /**
     * Here you are supposed to render things. This method is called after {@link IRenderer#render()} has been called.
     * 
     * @param renderer the renderer that is used
     */
    protected abstract void render(R renderer);

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
        objects.add(obj);
    }

    /**
     * Add a collection of objects.
     * 
     * @param objs the objects to add
     */
    public void add(Collection<IGameObject<R>> objs) {
        objects.addAll(objs);
    }

    /**
     * Allow the logic loop to by executed.
     */
    public void startLoop() {
        timer.start();
        loop = true;
    }

    /**
     * Stop the loop from executing.
     */
    public void stopLoop() {
        timer.stop();
        loop = false;
    }

    /**
     * Get the state.
     * @param <S> the type of state
     * @return the state
     */
    @SuppressWarnings("unchecked")
    public <S extends State> S getState() {
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
     * Get the timer that is executing the update loop.
     * 
     * @return the timer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * @return the stateHandler
     */
    public StateHandler getStateHandler() {
        return stateHandler;
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
        return objects;
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
    public void dispatch(Request<Logic<R>> req) {
        requests.add(req);
    }

    /**
     * Dispatch a request with a consumer.
     * @param consumer the consumer
     */
    public void dispatch(Consumer<Logic<R>> consumer) {
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
        dispatch(new RemovalRequest<R, Logic<R>>(objs));
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
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @param stateHandler the stateHandler to set
     */
    public void setStateHandler(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }
    
}