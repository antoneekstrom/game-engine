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

    /**
     * 
     */
    MeterComponent loadingBar;

    double progress = 0;

    Container<SwingRenderer> main;
    TextComponent statusTextComp;
    String statusText = "loading..";

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
        g.setColor(Color.white);

        CenteredLayout<SwingRenderer> layout = new CenteredLayout<>();
        layout.setWidthScale(0.6);

        main = new Container<>(layout);
        main.getBox().setSize(getBox().getSize());
        main.setGraphic(g);
        add(main);

        loadingBar = new MeterComponent(() -> progress, 100);
        loadingBar.setBackgroundColor(Color.white);
        loadingBar.setForegroundColor(Color.black);
        loadingBar.getBox().setHeight(125);

        TextComponent text = new TextComponent(() -> loadingBar.formatProgress());
        text.setFont(getFont());
        text.setColor(Color.white);

        loadingBar.add(text);

        statusTextComp = new TextComponent("");
        statusTextComp.setColor(Color.black);

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