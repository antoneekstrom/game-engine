package core;

import core.obj.IGameObject;

/**
 * Request to remove an object from the {@link Logic} at the end of the loop cycle.
 * 
 * @see Logic
 * @see IGameObject
 */
public class RemovalRequest <L extends Logic<?>> extends Request<L> {

    /**
     * The objects to remove.
     */
    private IGameObject<?>[] objects;

    /**
     * Create a request.
     * 
     * @param objects the objects to remove
     */
    public RemovalRequest(IGameObject<?>... objects) {

        this.objects = objects;

        setType(Request.RequestType.REMOVAL);

        setAction((logic) -> {
            for (IGameObject<?> obj : this.objects) {
                logic.getObjects().remove(obj);
            }
        });
    }
    
}