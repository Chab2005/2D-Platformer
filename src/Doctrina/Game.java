package Doctrina;

public abstract class Game {

    private RenderingEngine renderingEngine;
    private boolean playing = true;

    public abstract void initialize();
    public abstract void update();
    public abstract void draw(Canvas canvas);

    public final void start() {
        renderingEngine = RenderingEngine.getInstance();
        renderingEngine.start();
        initialize();
        run();
        conclude();
    }

    public void conclude() {

    }

    public void stop() {
        playing = false;
    }

    private void run() {
        GameTime time = new GameTime();
        while (playing) {
            update();
            draw(renderingEngine.buildCanvas());
            renderingEngine.drawOnScreen();
            time.synchronize();
        }
        renderingEngine.stop();
    }
}
