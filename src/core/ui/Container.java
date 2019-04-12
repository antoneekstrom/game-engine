package core.ui;

import java.util.ArrayList;

import core.IRenderer;

/**
 * Container TODO
 */
public class Container <R extends IRenderer<R>> extends Component<R> {

    /**
     * 
     */
    private ArrayList<IComponent<R>> children; 

    /**
     * 
     */
    public Container() {
    }

    /**
     * @return the children
     */
    public ArrayList<IComponent<R>> getChildren() {
        return children;
    }
    
}