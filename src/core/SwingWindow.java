package core;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.File;

import javax.swing.JFrame;

import core.io.resources.ImageResource;
import core.swing.SwingRenderer;
import core.util.FrameUtil;

/**
 * A concrete subtype of {@link Window} that uses the {@link SwingRenderer}.
 */
public class SwingWindow extends Window<SwingRenderer> {

    /**
     * The {@link GamePanel} is the <i>canvas</i> on which the graphics are drawn.
     * <p>This panel is added as a child component to the frame.
     */
    private GamePanel panel;

    /**
     * The game this window belongs to.
     */
    private Game game;

    /**
     * The frame is the root component of the window. A JFrame is actual window that has the title bar and buttons and such.
     */
    private JFrame frame;

    /**
     * The title of the window.
     */
    private String title;

    /**
     * The dimensions of the window.
     */
    final private Dimension size;

    /**
     * Create a window.
     * 
     * @param title the title
     * @param size the size
     */
    public SwingWindow(String title, Dimension size) {
        this.title = title;
        this.size = size;
    }

    @Override
    public void initialize(Game game) {

        this.game = game;

        frame = new JFrame(title);

        GamePanel panel = new GamePanel(this);
        initializePanel(panel);

        frame.setSize(size);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(getPanel());

        frame.validate();
    }

    @Override
    public void show() {
        frame.setVisible(true);
        panel.setVisible(true);
    }

    @Override
    public void run() {
    }

    @Override
    public void render() {
        // Calling repaint on the panel will call the paint(g) method below with a graphics object that will be used to render things
        getPanel().repaint();
    }

    @Override
    public void maximize() {
        FrameUtil.maximize(frame);
    }

    @Override
    public void fullscreen() {
        FrameUtil.setFullscreen(frame);
    }


    @Override
    public Dimension getSize() {
        return getFrame().getSize();
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        getFrame().setTitle(title);
    }

    @Override
    public void setIcon(File icon) {
        ImageResource img = ImageResource.create(icon);
        img.loadImage();
        getFrame().setIconImage(img.getBufferedImage());
    }

    /**
     * Paint graphics. This method is called by {@link #render(SwingRenderer)}.
     * 
     * @param g the graphics component
     */
    public void paint(Graphics2D g) {

        SwingRenderer renderer = getRenderer();
        renderer.setGraphics(g);
        renderer.startRender();
    }

    /**
     * @param panel the panel to set
     */
    public void setPanel(GamePanel panel) {
        this.panel = panel;
    }

    /**
     * 
     * @param panel
     */
    public void initializePanel(GamePanel panel) {
        setPanel(panel);
        getPanel().initialize(getGame());
        this.keyInput = panel.getKeyInput();
        this.mouseInput = panel.getMouseInput();
    }

    /**
     * @return the panel
     */
    public GamePanel getPanel() {
        return panel;
    }

    /**
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

}