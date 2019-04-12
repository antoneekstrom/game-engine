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
        while (events.size() > 0) {
            events.pop().process();
        }
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