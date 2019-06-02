package core.ui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

import core.MouseInputHelper;
import core.math.Vector2D;
import core.swing.SwingRenderer;

/**
 * TextFieldComponent
 */
public class TextFieldComponent extends TextComponent {

    boolean isActive = false;

    boolean clearOnSubmit = true;

    String placeholder, value = "";

    Consumer<String> consumer;

    Color bg, activeColor;

    public TextFieldComponent(String placeholder, Consumer<String> consumer, double width, Color fg, Color bg) {
        super(placeholder, fg, bg);
        this.placeholder = placeholder;
        this.consumer = consumer;
        this.bg = bg;
        this.activeColor = bg;
        getBox().setWidth(width);
        setFitToText(false, true);
        setSupplier(this::getDisplayText);
    }

    public TextFieldComponent(String placeholder, Consumer<String> consumer, double width, Color fg, Color bg, Color active) {
        this(placeholder, consumer, width, fg, bg);
        this.activeColor = active;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isActive = mouseHover() ? !isActive : false;
        System.out.println("isActive: " + isActive);
    }

    @Override
    public void render(SwingRenderer renderer, Vector2D pos) {
        getBackground().setColor(isActive ? activeColor : bg);
        super.render(renderer, pos);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_BACK_SPACE) {
            if (value.length() > 0)
            value = value.substring(0, value.length() - 1);
        }
        else if (code == KeyEvent.VK_ENTER) {
            submit();
        }
        else if (isActive) {
            value += e.getKeyChar();
        }
        refresh();
    }

    public void submit() {
        consumer.accept(value);
        if (clearOnSubmit) {
            value = "";
        }
        refresh();
    }

    String getDisplayText() {
        return isActive ? value : placeholder;
    }
    
}