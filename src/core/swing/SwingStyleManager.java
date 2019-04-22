package core.swing;

import core.ui.IComponent;
import core.ui.style.IStyleManager;

/**
 * SwingStyleManager TODO
 */
public abstract class SwingStyleManager<C extends IComponent<SwingRenderer>> implements IStyleManager {

    /**
     * @param comp
     * @return
     */
    public abstract C styleBorder(C comp);

    /**
     * @param comp
     * @return
     */
    public abstract C styleBackground(C comp);

    /**
     * @param comp
     * @return
     */
    public abstract C styleText(C comp);

    /**
     * @param comp
     * @return
     */
    public abstract C styleSize(C comp);

    /**
     * Apply all styles to a component.
     * 
     * @param comp the component
     * @return the component
     */
    public C styleComponent(C comp) {
        styleBorder(comp);
        styleBackground(comp);
        styleText(comp);
        styleSize(comp);
        return comp;
    }
    
}