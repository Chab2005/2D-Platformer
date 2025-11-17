package MarioBros;

import Doctrina.Canvas;
import Doctrina.Game;
import Doctrina.RenderingEngine;

public class MarioBrosGame extends Game {

    private Player player;
    private GamePad gamePad;
    private World world;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.moveTo(200, 200);
        world = new World();
        world.load();
        RenderingEngine.getInstance().getScreen().fullscreen();
    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        player.update();
    }

    @Override
    public void draw(Canvas canvas) {
        world.draw(canvas);
        player.draw(canvas);
    }
}
