package core;

/**
 * IEngine TODO
 */
public interface IEngine {

    public void setup();

    public void start();

    public void stop();

    public void loop();

    public EngineState getState();

    public void onEngineUpdate(double delta);
    
}