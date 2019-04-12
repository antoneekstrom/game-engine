package core.state;

/**
 * An event that can be pushed to an {@link IState}.
 */
public interface IStateEvent {

    /**
     * 
     * @param state
     */
    public default void push(IState state) {
        state.push(this);
    }

    /**
     * Process this event.
     */
    public void process();
    
}