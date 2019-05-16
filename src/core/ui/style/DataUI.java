package core.ui.style;

import core.IRenderer;
import core.ui.UserInterface;
import core.ui.layout.AbstractLayout;

/**
 * DataUI TODO
 */
public abstract class DataUI <R extends IRenderer<R>, O> extends UserInterface<R> {

    /**
     * Object.
     */
    private O obj;

    private boolean reloadOnShow = false;

    /**
     * @param obj object
     */
    public DataUI(O obj) {
        super(ID);
        this.obj = obj;
    }

    public DataUI(O obj, boolean reloadOnShow) {
        this(obj);
    }

    @Override
    public void setup() {
        if (getObj() != null)
        setup(getLayout(), getObj());
    }

    /**
     * @param l
     * @param obj
     */
    public abstract void setup(AbstractLayout<R> l, O obj);

    public void show(O obj) {
        onShow();
        setObj(obj);
    }

    @Override
    protected void onShow() {
        super.onShow();
        onShow(getObj());
    }

    /**
     * 
     * @param obj
     */
    public abstract void onShow(O obj);

    /**
     * @return the obj
     */
    public O getObj() {
        return obj;
    }

    /**
     * @param obj the obj to set
     */
    public void setObj(O obj) {
        this.obj = obj;
    }
    
}