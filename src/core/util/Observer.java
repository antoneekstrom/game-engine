package core.util;

/**
 * Observe TODO
 */
public interface Observer<A> {

    public void update(Observable<A> observer, A arg);
    
}