package core.state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import core.Game;
import core.Logic;

/**
 * Contains the state of the {@link Game}. Can be handled, serialized and
 * deserialized with a {@link StateHandler}.
 * <p>
 * Used in {@link Logic} to store game data.
 */
public class State implements IState {

    // serial ID
    private static final long serialVersionUID = -1957553428397576783L;

    private ArrayList<IStateListener> listeners;
    private LinkedList<IStateEvent> events;

    public State() {
        listeners = new ArrayList<>();
        events = new LinkedList<>();
    }

    public void setup() {
    }

    @Override
    public void process() {
        int initialSize = events.size();

        while (events.size() > 0) {
            processEvent(events.pop());
        }

        if (initialSize > 0)
        IState.super.push(new StateProcessedEvent());
    }

    /**
     * Process a singular event.
     * @param e the event
     */
    protected void processEvent(IStateEvent e) {
        e.process();
    }

    @Override
    public void push(IStateEvent e) {
        IState.super.push(e);
        events.push(e);
    }

    @Override
    public Collection<IStateListener> stateListeners() {
        return listeners;
    }

}