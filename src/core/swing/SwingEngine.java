package core.swing;

import javax.swing.Timer;

import core.AbstractGameEngine;
import core.AbstractLogic;
import core.EngineState;

/**
 * SwingEngine
 */
public class SwingEngine extends AbstractGameEngine {

    /**
     * @param logic
     */
    public SwingEngine(AbstractLogic<?> logic) {
        super(logic);
    }

    /**
     * 
     */
    private Timer timer;

    /**
     * The interval the timer should update on
     */
    private int timerDelay = 10;

    @Override
    protected void onStateChange(EngineState newState) {
        if (newState.isRunning()) timer.start();
        else timer.stop();
    }

    @Override
    public void setup() {
        timer = new Timer(timerDelay, e -> loop());
    }

    @Override
    public void loop() {
        onEngineUpdate(timerDelay);
    }

    /**
     * @return the timer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * @return the timerDelay
     */
    public int getTimerDelay() {
        return timerDelay;
    }
    
}