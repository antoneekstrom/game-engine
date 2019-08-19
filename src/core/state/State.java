package core.state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import core.Game;
import core.IRenderer;
import core.obj.IGameObject;
import core.AbstractLogic;

/**
 * Contains the state of the {@link Game}. Can be handled, serialized and
 * deserialized with a {@link StateHandler}.
 * <p>
 * Used in {@link AbstractLogic} to store game data.
 */
public class State<R extends IRenderer<R>> implements IState<R> {

    // serial ID
    private static final long serialVersionUID = -1957553428397576783L;

    private ArrayList<IStateListener> listeners;
    private LinkedList<IStateEvent> events;

    /**
     * The list of objects that are updated by logic.
     */
    ArrayList<IGameObject<R>> objects;

    public State() {
    }

    public void setup() {
        listeners = new ArrayList<>();
        events = new LinkedList<>();
        objects = new ArrayList<>();
    }

    @Override
    public ArrayList<IGameObject<R>> getObjects() {
        return objects;
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