package core.state;

import java.io.Serializable;
import java.util.Collection;

import core.AbstractLogic;

/**
 * The current state of the {@link AbstractLogic}.
 * 
 * @see State
 * @see AbstractLogic
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
        System.out.println("statelisteners: " + stateListeners().size());
    }

    /**
     * 
     * @param l
     */
    public default void unsubscribe(IStateListener l) {
        stateListeners().remove(l);
        System.out.println("statelisteners: " + stateListeners().size());
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