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
    public DataUI(O obj, boolean reloadOnShow) {
        super();
        this.obj = obj;
        this.reloadOnShow = reloadOnShow;
    }

    public DataUI(O obj) {
        this(obj, false);
    }
    
    public DataUI(boolean reloadOnShow) {
        this(null, reloadOnShow);
    }

    @Override
    public void setup() {
        if (getObj() != null)
        setup(getObj());
    }

    /**
     * @param l
     * @param obj
     */
    public abstract void setup(O obj);

    /**
     * 
     * @param obj
     */
    public abstract void onShow(O obj);

    /**
     * 
     * @param obj
     */
    public void show(O obj) {
        onShow();
        setObj(obj);
    }

    @Override
    protected void onShow() {
        onShow(getObj());
        refresh();
        if (reloadOnShow) reload();
    }

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