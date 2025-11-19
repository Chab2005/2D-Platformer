package MarioBros;

import Doctrina.Canvas;
import Doctrina.Game;
import Doctrina.RenderingEngine;

public class MarioBrosGame extends Game {

    private Player player;
    private Enemy enemy;
    private GamePad gamePad;
    private World world;
    private boolean fullscreen;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        enemy = new Enemy();
        enemy.moveTo(300, 300);
        world = new World();
        world.load();

        RenderingEngine.getInstance().getScreen().fullscreen();
        fullscreen = false;
    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }

        if (gamePad.isScreenPressed() && !fullscreen) {
            RenderingEngine.getInstance().getScreen().screenToggle();
        }
        fullscreen = gamePad.isScreenPressed();


        player.update();
    }

    @Override
    public void draw(Canvas canvas) {

        world.draw(canvas);
        player.draw(canvas);
    }
}
