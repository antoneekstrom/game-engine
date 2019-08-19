package core;

/**
 * AbstractGameEngine TODO
 */
public abstract class AbstractGameEngine implements IEngine {

    /**
     * 
     */
    public AbstractGameEngine(AbstractLogic<?> logic) {
        this.logic = logic;
    }

    /**
     * 
     */
    private EngineState state = EngineState.STOPPED_STATE;

    /**
     * 
     */
    protected AbstractLogic<?> logic;

    /**
     * @param newState
     */
    protected abstract void onStateChange(EngineState newState);

    @Override
    public void onEngineUpdate(double delta) {
        logic.onEngineUpdate(delta);
    }

    @Override
    public void start() {
        if (state != EngineState.RUNNING_STATE) {
            state = EngineState.RUNNING_STATE;
            onStateChange(state);
        }
    }

    @Override
    public void stop() {
        if (state != EngineState.STOPPED_STATE) {
            state = EngineState.STOPPED_STATE;
            onStateChange(state);
        }
    }

    @Override
    public EngineState getState() {
        return state;
    }
    
}