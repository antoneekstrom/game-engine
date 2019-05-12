package core.ui;

import core.IRenderer;
import core.obj.IGameObject;

/**
 * An {@link IGameObject} that is supposed to be a part of an {@link UserInterface}.
 * This object represents an element on the UI.
 */
public interface IComponent<R extends IRenderer<R>> extends IGameObject<R> {

    /**
     * Set if this component should be visible.
     * 
     * @param visible if it should be visible
     */
    public void setVisible(boolean visible);

    /**
     * @return if this component is visible
     */
    public boolean isVisible();

    /**
     * Refresh the state of the component.
     * 
     * <p>It is unnecessary to constantly update the UI with {@link #update()}, because the UI does usually not change that much.
     * Instead you should do the updating in this method which is called less often.
     */
    public void refresh();

    /**
     * Get the UI this component currently resides in.
     * @return the UI
     */
    public UserInterface<R> getUI();

    /**
     * Set the UI this component belongs to.
     * @param ui the ui to set
     */
    public void setUI(UserInterface<R> ui);
    
}