package core.ui;

import java.util.ArrayList;

import core.IGraphic;
import core.IRenderer;
import core.obj.ObjectStorage;

/**
 * Handles and stores multiple {@link UserInterface} objects. One UI can be set as active
 * and that one will be rendered and updated. More work is needed.
 */
public class UIManager <R extends IRenderer<R>> extends ObjectStorage<UserInterface<R>, R> {

    /**
     * The UI that is active.
     */
    private UserInterface<R> active;

    /**
     * 
     */
    public UIManager() {
        super();

        getWindow().addResizeListener((size) -> {
            getInterfaces().forEach(i -> i.onResize(size));
        });
    }

    @Override
    protected boolean shouldPropagate(UserInterface<R> obj) {
        return obj == getActive();
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
     * Rebuild the UI components of every {@link UserInterface}.
     */
    public void reload() {
        getInterfaces().forEach(UserInterface::reload);
    }

    /**
     * @param ui
     */
    public void remove(UserInterface<R> ui) {
        getInterfaces().remove(ui);
    }

    /**
     * Set an interface as active, show it, and hide the one that was active before.
     * @param ui the ui to show
     */
    public void show(UserInterface<R> ui) {
        getActive().setVisible(false);
        setActive(ui);
        getActive().setVisible(true);
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
    protected void setActive(UserInterface<R> active) {
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