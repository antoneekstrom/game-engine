package core.state;

/**
 * A special event that is sent to listeners when all events in the queue have been processed.
 */
public class StateProcessedEvent implements IStateEvent {

    public static final String TYPE = "PROCESSED_EVENT";

    @Override
    public void process() {}

    @Override
    public String getType() {
        return TYPE;
    }
    
}