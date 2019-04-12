package core.ui;

import java.util.ArrayList;

import core.IGraphic;
import core.IRenderer;
import core.obj.ObjectStorage;

/**
 * UIStorage TODO
 */
public class UIManager <R extends IRenderer<R>> extends ObjectStorage<UserInterface<R>, R> {

    /**
     * 
     */
    private UserInterface<R> active;

    /**
     * 
     */
    public UIManager() {
        super();
    }

    @Override
    public IGraphic<R> getGraphic() {
        return getActive().getGraphic();
    }

    /**
     * Get an ui by searching for it with an id.
     * 
     * @param id the id
     * @return the ui
     */
    public UserInterface<R> getById(String id) {
        for (UserInterface<R> ui : getObjects()) {
            if (ui.getId().equals(id)) return ui;
        }
        throw new IllegalArgumentException(String.format("UserInterface with the id '%s' does not exist.", id));
    }

    /**
     * @param ui
     */
    public void add(UserInterface<R> ui) {
        getInterfaces().add(ui);
    }

    /**
     * @param ui
     */
    public void remove(UserInterface<R> ui) {
        getInterfaces().remove(ui);
    }

    /**
     * @return the active
     */
    public UserInterface<R> getActive() {
        return active;
    }

    /**
     * Set an ui as active in the manager. This will make it the one that is shown by
     * 
     * @param active the ui to set to active
     */
    public void setActive(UserInterface<R> active) {
        this.active = active;
    }

    /**
     * Set an ui as active in the manager. This will make it the one that is shown by
     * 
     * @param id the id of the ui to set to active
     */
    public void setActive(String id) {
        setActive(getById(id));
    }

    /**
     * @return the interfaces
     */
    protected ArrayList<UserInterface<R>> getInterfaces() {
        return getObjects();
    }

    
}