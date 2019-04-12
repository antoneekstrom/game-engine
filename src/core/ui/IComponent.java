package core.ui;

import core.IRenderer;
import core.obj.IGameObject;

/**
 * UIComponent
 */
public interface IComponent <R extends IRenderer<R>> extends IGameObject<R> {

    public void setVisible(boolean visible);
//TODO
    public boolean isVisible();

    
}