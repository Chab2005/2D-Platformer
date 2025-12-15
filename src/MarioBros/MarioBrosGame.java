package MarioBros;

import Doctrina.*;
import Doctrina.Canvas;
import Doctrina.RenderingRepository;

import java.awt.*;
import java.io.IOException;


public class MarioBrosGame extends Game {

    private Sound sound;
    private RenderingRepository instance;
    private Player player;
    private Enemy enemy;
    private GamePad gamePad;
    private World world;
    private Camera camera;
    private BackGround backGround;

    private boolean isfullscreen;

    @Override
    public void initialize() {
        instance = RenderingRepository.getInstance();
        sound = new Sound();
        //sound.playSound();
        gamePad = new GamePad();
        player = new Player(gamePad);

        enemy = new Enemy(camera);
        //enemy.moveTo(300, 300);

        world = new World();
        world.load();
        camera = new Camera(player);
        //instance.registerEntities(enemy);
        instance.registerEntities(player);

        RenderingEngine.getInstance().getScreen().fullscreen();

        backGround = new BackGround();
        isfullscreen = true;
    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }

        if (gamePad.isScreenPressed() && isfullscreen) {
            RenderingEngine.getInstance().getScreen().screenToggle();
        }
        isfullscreen = !gamePad.isScreenPressed();

        //enemy.update();
        if (player.isMoving() || player.isMovingSides()) {
            camera.follow();
        }

        player.update();

    }

    @Override
    public void draw(Canvas canvas) {
        backGround.draw(canvas);
        instance.drawRepository(canvas);
    }
}
