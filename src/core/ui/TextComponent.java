package core.ui;

import java.awt.Color;
import java.awt.Font;
import java.util.function.Supplier;

import core.graphic.BoxGraphic;
import core.graphic.TextGraphic;
import core.math.Vector2D;
import core.swing.SwingComponent;
import core.swing.SwingRenderer;

/**
 * A {@link Component} that displays text. A TextComponent can also render a background under the text.
 */
public class TextComponent extends SwingComponent {

    /**
     * The graphic for the text.
     */
    private TextGraphic text;

    /**
     * The graphic for the background.
     */
    private BoxGraphic background;

    /**
     * A supplier to get the text from on refresh.
     */
    private Supplier<String> supplier;

    /**
     * If the supplier should be used on refresh.
     */
    private boolean useSupplier = false;

    /**
     * Padding for the background around the text.
     */
    private Vector2D padding;

    /**
     * If the background of the text should be rendered.
     */
    private boolean showBackground = false;

    /**
     * If the background and size of the component should be resized automatically.
     */
    private boolean fitToTextX = true, fitToTextY = true;

    /**
     * If the text inside the component should be centered.
     */
    private boolean centerText = true;

    /**
     * @param text the text to set
     */
    public TextComponent(String text) {
        this(() -> text);
    }

    /**
     * @param text the text to set
     * @param textColor the color of the text
     */
    public TextComponent(String text, Color textColor) {
        this(text);
        this.text.setColor(textColor);
    }

    /**
     * @param text the text to set
     * @param textColor the color of the text
     * @param backgroundColor the color of the background
     */
    public TextComponent(String text, Color textColor, Color backgroundColor) {
        this(text, textColor);
        setBackground(backgroundColor);
    }

    /**
     * @param supplier supplier that should be used to set the text of the component on {@link #refresh()}
     */
    public TextComponent(Supplier<String> supplier) {
        super();
        setSupplier(supplier);
        this.text = new TextGraphic(supplier.get());
        background = new BoxGraphic(getBox());
        padding = Vector2D.create(0, 0);
    }

    /**
     * @param supplier supplier that should be used to set the text of the component on {@link #refresh()}
     * @param textColor the color of the text
     * @param backgroundColor the color of the background
     */
    public TextComponent(Supplier<String> supplier, Color textColor, Color backgroundColor) {
        this(supplier);
        setColor(textColor);
        setBackground(backgroundColor);
    }
    
    @Override
    public void render(SwingRenderer renderer, Vector2D pos) {
        if (showBackground) {
            background.render(renderer, pos);
        }

        if (shouldCenterText()) {
            pos.set(getTextCenter(text, pos));
        }
        text.render(renderer, pos);
    }

    /**
     * 
     * @param g
     * @param renderPos
     * @return
     */
    public Vector2D getTextCenter(TextGraphic g, Vector2D renderPos) {
        Vector2D size = getBox().getSize().copy();
        Vector2D textSize = text.getTextSize().copy();
        return renderPos.add(size.div(2)).sub(textSize.div(2));
    }

    @Override
    public void refresh() {
        if (supplier != null && useSupplier) setText(getSupplier().get());
    }

    @Override
    public void update() {
        super.update();
    }

    /**
     * Set the text to be displayed.
     * 
     * @param text the text to set
     */
    public void setText(String text) {
        if (this.text == null) return;

        this.text.setText(text);

        if (fitToTextX || fitToTextY) {
            resize();
        }
    }

    /**
     * Resize the bounding box of the component to fit the text.
     */
    public void resize() {

        // recalculate the text size before doing the other things that depend on the size of the text
        text.getTextSize().set(text.getTextSize(getRenderer().getGraphics()));

        Vector2D size = text.getTextSize().copy();
        Vector2D bsize = getBox().getSize();
        if (fitToTextX) bsize.setX(size.getX());
        if (fitToTextY) bsize.setY(size.getY());
        bsize.add(getPadding());
    }

    /**
     * Set the color of the text.
     * 
     * @param color the color
     */
    public void setColor(Color color) {
        text.setColor(color);
    }

    /**
     * Set the font.
     * 
     * @param font the font to use
     */
    public void setFont(Font font) {
        text.setFont(font);
    }

    /**
     * Set the border of the background.
     * 
     * @param thickness the thickness is pixels of the border
     * @param color the color of the border
     */
    public void setBorder(int thickness, Color color) {
        getBackground().setBorder(thickness, color);
    }

    /**
     * @return
     */
    public String getText() {
        return text.getText();
    }

    /**
     * @return the supplier
     */
    public Supplier<String> getSupplier() {
        return supplier;
    }

    /**
     * @param useSupplier if the supplier should be used
     */
    public void setUseSupplier(boolean useSupplier) {
        this.useSupplier = useSupplier;
    }

    /**
     * A supplier that should be used to set the text
     * of the component on {@link #refresh()}.
     * 
     * @param supplier the supplier
     */
    public void setSupplier(Supplier<String> supplier) {
        setUseSupplier(true);
        this.supplier = supplier;
    }

    /**
     * @param x
     * @param y
     */
    public void setFitToText(boolean x, boolean y) {
        this.fitToTextX = x;
        this.fitToTextY = y;
    }

    /**
     * @param showBackground the showBackground to set
     */
    public void showBackground(boolean showBackground) {
        this.showBackground = showBackground;
    }

    /**
     * @param fontSize
     */
    public void setFontSize(float fontSize) {
        setFont(getFont().deriveFont(fontSize));
    }

    /**
     * @param color
     */
    public void setBackground(Color color) {
        showBackground(true);
        background.setColor(color);
    }

    /**
     * @param graphic the background graphic to use
     */
    public void setBackgroundGraphic(BoxGraphic graphic) {
        showBackground(true);
        this.background = graphic;
    }

    /**
     * @param padding the padding to set
     */
    public void setPadding(Vector2D padding) {
        this.padding.set(padding);
    }

    /**
     * @param padding the padding to set
     */
    public void setPadding(double x, double y) {
        padding.set(x, y);
    }

    /**
     * @return if the text should be centered inside the component
     */
    public boolean shouldCenterText() {
        return centerText;
    }

    /**
     * @param centerText if the text should be centered inside the component
     */
    public void setCenterText(boolean centerText) {
        this.centerText = centerText;
    }

    /**
     * @return
     */
    public Font getFont() {
        return text.getFont();
    }

    /**
     * @return the padding
     */
    public Vector2D getPadding() {
        return padding;
    }

    /**
     * @return the background
     */
    public BoxGraphic getBackground() {
        return background;
    }

}