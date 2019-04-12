package core.state;

import java.io.Serializable;
import java.util.Collection;

import core.Logic;

/**
 * The current state of the {@link Logic}.
 * 
 * @see State
 * @see Logic
 * @see IStateEvent
 */
public interface IState extends Serializable {

    /**
     * 
     */
    public void setup();

    /**
     * 
     */
    public void process();

    /**
     * 
     * @param l
     */
    public default void subscribe(IStateListener l) {
        stateListeners().add(l);
    }

    /**
     * 
     * @param l
     */
    public default void unsubscribe(IStateListener l) {
        stateListeners().remove(l);
    }

    /**
     * 
     * @return
     */
    public Collection<IStateListener> stateListeners();

    /**
     * 
     * @param a
     */
    public default void push(IStateEvent a) {
        
        for (IStateListener l : stateListeners()) {
            l.eventPushed(a);
        }
    }
    
}