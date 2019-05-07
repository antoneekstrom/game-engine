package core.util;

/**
 * An observer which can observer an observable.
 * 
 * @see Observable
 */
@FunctionalInterface
public interface Observer<A> {

    /**
     * Notify the observer of a change on the observable.
     * @param observable the observable
     * @param arg the argument
     */
    public void notify(Observable<A> observable, A arg);
    
}