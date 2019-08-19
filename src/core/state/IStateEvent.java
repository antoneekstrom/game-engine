package core.state;

import core.Game;

/**
 * An event that can be pushed to an {@link IState}.
 */
public interface IStateEvent {

    public static final String TYPE = "EVENT";

    /**
     * Push this event to a state.
     * 
     * @param state the state
     */
    public default void push(IState<?> state) {
        state.push(this);
    }

    /**
     * Push this event to the state.
     */
    public default void push() {
        Game.getLogicInstance().getState().push(this);
    }

    /**
     * Get the type of event
     * @return the type
     */
    public default String getType() {
        return TYPE;
    }

    /**
     * Process this event.
     */
    public void process();
    
}