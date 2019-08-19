package core;

/**
 * EngineState
 */
public class EngineState {

    public static final EngineState RUNNING_STATE = new EngineState(true), STOPPED_STATE = new EngineState(false);

    /**
     */
    private boolean isRunning;

    /**
     * @param isRunning
     */
    public EngineState(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * @return
     */
    public boolean isRunning() {
        return isRunning;
    }
}