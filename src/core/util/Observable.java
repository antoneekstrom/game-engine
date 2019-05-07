package core.util;

import java.util.ArrayList;

/**
 * Can be observed by an {@link Observer}. This observer can then be notified by the observable.
 */
public class Observable<A> {

    private ArrayList<Observer<A>> observers;

    public Observable() {
        observers = new ArrayList<>();
    }

    public void observe(Observer<A> o) {
        observers.add(o);
    }

    public void unobserve(Observer<A> o) {
        observers.remove(o);
    }

    protected void notifyObservers(A arg) {
        observers.forEach(o -> o.notify(this, arg));
    }
    
}