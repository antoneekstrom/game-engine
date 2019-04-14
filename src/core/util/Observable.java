package core.util;

import java.util.ArrayList;

/**
 * Observable TODO
 */
public class Observable<A> {

    private ArrayList<Observer<A>> observers;

    public Observable() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer<A> o) {
        observers.add(o);
    }

    public void removeObserver(Observer<A> o) {
        observers.remove(o);
    }

    protected void notifyObservers(A arg) {
        observers.forEach(o -> o.update(this, arg));
    }
    
}