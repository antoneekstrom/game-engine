package core;

import java.util.function.Consumer;

/**
 * A request that is to be processed at the end of a loop in {@link Logic}.
 */
public class Request <L extends Logic<?>> {

    public static enum RequestType {
        REMOVAL, OTHER
    }

    private Consumer<L> action;

    private RequestType type;

    /**
     * 
     * @param type
     * @param action
     */
    public Request(RequestType type, Consumer<L> action) {
        this.action = action;
        this.type = type;
    }

    protected Request() {}
    
    /**
     * @return the action
     */
    public Consumer<L> getAction() {
        return action;
    }

    /**
     * @return the type
     */
    public RequestType getType() {
        return type;
    }

    /**
     * @param action the action to set
     */
    protected void setAction(Consumer<L> action) {
        this.action = action;
    }

    /**
     * @param type the type to set
     */
    protected void setType(RequestType type) {
        this.type = type;
    }
    
}