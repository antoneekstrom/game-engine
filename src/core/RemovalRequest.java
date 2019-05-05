package core;

import java.util.ArrayList;

import core.obj.IGameObject;

/**
 * Request to remove an object from the {@link Logic} at the end of the loop cycle.
 * 
 * @see Logic
 * @see IGameObject
 */
public class RemovalRequest <R extends IRenderer<R>, L extends Logic<R>> extends Request<L> {

    /**
     * The objects to remove.
     */
    private IGameObject<R>[] objects;

    /**
     * Create a request.
     * 
     * @param logic the logic instance
     * @param objects the objects to remove
     */
    @SafeVarargs
    public RemovalRequest(IGameObject<R>... objects) {

        this.objects = objects;

        setType(Request.RequestType.REMOVAL);

        setAction((logic) -> {
            for (IGameObject<R> obj : this.objects) {
                ArrayList<IGameObject<R>> lobjects = logic.getObjects();

                for (int i = 0; i < lobjects.size(); i++) {
                    lobjects.get(i).remove(lobjects, obj, i);
                }
            }
        });
    }
    
}