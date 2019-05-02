package core.ui.layout;

import core.IRenderer;
import core.ui.IComponent;

/**
 * CenteredLayout
 */
public class CenteredLayout<R extends IRenderer<R>> extends DefaultLayout<R> {
    
    @Override
    public void align(IComponent<R> comp, Alignment align) {
        super.align(comp, align);
        realign();
    }

    /**
     * 
     */
    public void realign() {
        double y = getContainer().getBox().getHeight() / 2;
        for (IComponent<R> comp : getContainer().getChildren()) {
            comp.getBox().getPosition().addY(y);
            y -= comp.getBox().getHeight() / 2;
        }
    }
    
}