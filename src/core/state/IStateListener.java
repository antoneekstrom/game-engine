package core.state;

/**
 * Can subscribe to {@link IState}.
 */
public interface IStateListener {

    public void eventPushed(IStateEvent a);
    
}