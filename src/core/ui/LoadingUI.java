package core.ui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.function.Supplier;

import core.graphic.BoxGraphic;
import core.swing.SwingRenderer;
import core.swing.SwingUI;
import core.ui.layout.CenteredLayout;

/**
 * Shows a loading bar.
 */
public class LoadingUI extends SwingUI {

    public static final String ID = "loading_ui";

    /**
     * 
     */
    MeterComponent loadingBar;

    double progress = 0;

    Container<SwingRenderer> main;
    TextComponent statusTextComp;
    String statusText = "loading..";

    /**
     * 
     */
    public LoadingUI() {
        super(ID);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        progress = (e.getX() / getWindow().getSizeVector().getX()) * 100;
        statusTextComp.setText(statusText);
        main.refresh();
    }

    @Override
    public void setup() {

        BoxGraphic g = new BoxGraphic(getBox());
        g.setColor(Color.yellow);

        main = new Container<>(new CenteredLayout<SwingRenderer>());
        main.getBox().setSize(getBox().getSize());
        main.setGraphic(g);
        add(main);

        loadingBar = new MeterComponent(() -> progress, 100);
        loadingBar.getBox().setSize(getWindow().getSizeVector().mul(0.6)).getSize().setY(100);
        loadingBar.setBackgroundColor(Color.white);
        loadingBar.setForegroundColor(Color.green);

        TextComponent text = new TextComponent(() -> loadingBar.formatProgress());
        text.setFont(getFont());
        text.setColor(Color.red);

        loadingBar.add(text);

        statusTextComp = new TextComponent("");
        statusTextComp.setColor(Color.black);
        //statusTextComp.setFont(getFont().deriveFont(40f));

        main.add(statusTextComp);
        main.add(loadingBar);
    }

    /**
     * @param supplier
     */
    public void setSupplier(Supplier<Double> supplier) {
        loadingBar.setSupplier(supplier);
    }
    
}