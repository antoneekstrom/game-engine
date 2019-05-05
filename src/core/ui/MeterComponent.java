package core.ui;

import java.awt.Color;
import java.util.function.Supplier;

import core.graphic.BoxGraphic;
import core.graphic.GraphicContainer;
import core.math.Vector2D;
import core.swing.SwingRenderer;
import core.ui.layout.CenteredLayout;
import core.ui.layout.NoLayout;

/**
 * MeterComponent TODO
 */
public class MeterComponent extends Container<SwingRenderer> {

    /**
     * The maximum value the meter can display.
     */
    private double maxValue;

    /**
     * The current value that is displayed. This value can explicitly be set, but is also set by the supplier.
     */
    private double value;

    /**
     * Used to get a new value when refreshed.
     */
    private Supplier<Double> supplier;

    /**
     * The background of the meter.
     */
    private BoxGraphic bg;

    /**
     * The foreground/loading bar.
     */
    private BoxGraphic bar;

    /**
     * Color of the background.
     */
    private Color backgroundColor;
    
    /**
     * Color of the bar.
     */
    private Color foregroundColor;

    /**
     * Create a MeterComponent.
     * 
     * @param size the size of the meter
     * @param fgColor the foreground color of the bar
     * @param supplier a source to retrieve the value from
     * @param maxValue the maximum value the meter displays
     */
    public MeterComponent(Vector2D size, Color fgColor, Supplier<Double> supplier, double maxValue) {
        super();

        setValue(0);
        setMaxValue(maxValue);
        setSupplier(supplier);

        setForegroundColor(fgColor);

        getBox().setSize(size);

        setup();
    }

    /**
     * Create a MeterComponent.
     * 
     * @param supplier a source to retrieve the value from
     * @param maxValue the maximum value the meter displays
     */
    public MeterComponent(Supplier<Double> supplier, double maxValue) {
        this(Vector2D.create(350, 45), Color.orange, supplier, maxValue);
    }

    /**
     * Create a MeterComponent.
     * 
     * @param value the current value of the meter
     * @param maxValue the maximum value the meter displays
     */
    public MeterComponent(double value, double maxValue) {
        this(null, maxValue);

        setValue(value);
        setSupplier(this::getValue);
    }

    @Override
    public void render(SwingRenderer renderer, Vector2D pos) {

        bg.setColor(getBackgroundColor());
        bar.setColor(getForegroundColor());

        super.render(renderer, pos);
    }

    /**
     * Convenience for adding a text component to the meter.
     * @param textColor the text color
     * @param supplier supplier of the text to be displayed
     * @return the text component that was added
     */
    public TextComponent addText(Color textColor, Supplier<String> supplier) {
        TextComponent t = new TextComponent(supplier);
        t.setColor(textColor);
        t.getSize().set(getSize());
        t.setFitToText(false);
        t.setCenterText(true);
        add(t);
        return t;
    }

    /**
     * Setup.
     */
    private void setup() {

        setLayout(new NoLayout<>());

        bg = new BoxGraphic(getBox(), getBackgroundColor());
        bar = new BoxGraphic(getBox().copy(), getForegroundColor());

        GraphicContainer<SwingRenderer> c = new GraphicContainer<>(bg, bar);
        setGraphic(c);

        refresh();
    }

    /**
     * Refresh the meter by updating the value and the graphic.
     */
    @Override
    public void refresh() {
        super.refresh();

        updateValue();

        double progress = getProgress();
        double width = getBox().getWidth() * progress;

        bar.getBox().setSize(width, getBox().getHeight());
    }

    /**
     * Retrieve a new value from the supplier.
     */
    public void updateValue() {
        setValue(getSupplier().get());
    }

    /**
     * How completed the meter is from 0-1.
     * @return the progress
     */
    public double getProgress() {
        return getValue() / getMaxValue();
    }

    /**
     * @return TODO
     */
    public String formatProgress() {
        return String.format("%.2f/%.2f", getValue(), getMaxValue());
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @param maxValue the maxValue to set
     */
    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(Supplier<Double> supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the supplier
     */
    public Supplier<Double> getSupplier() {
        return supplier;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @return the maxValue
     */
    public double getMaxValue() {
        return maxValue;
    }

    /**
     * @return the bg
     */
    public BoxGraphic getBackground() {
        return bg;
    }

    /**
     * @return the bar
     */
    public BoxGraphic getBar() {
        return bar;
    }

    /**
     * @return the foregroundColor
     */
    public Color getForegroundColor() {
        return foregroundColor;
    }

    /**
     * @return the backgroundColor
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * @param foregroundColor the foregroundColor to set
     */
    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    /**
     * @param backgroundColor the backgroundColor to set
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
}