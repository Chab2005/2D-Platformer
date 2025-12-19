package MarioBros;

import Doctrina.*;
import Doctrina.Canvas;
import Doctrina.RenderingRepository;

import java.awt.*;
import java.io.IOException;


public class MarioBrosGame extends Game {


    private RenderingRepository instance;
    private Player player;
    private Goomba goomba;
    private GamePad gamePad;
    private World world;
    private Camera camera;
    private BackGround backGround;


    private boolean isfullscreen;
    private boolean paused;
    private boolean pauseWasPressed;

    @Override
    public void initialize() {
        instance = RenderingRepository.getInstance();
        paused = true;
        pauseWasPressed = false;
        SoundEffect.MUSIC_1_1.play();
        gamePad = new GamePad();
        player = new Player(gamePad);
        goomba = new Goomba();


        world = new World();
        world.load();
        camera = new Camera(player);

        instance.registerEntities(player);
        instance.registerEntities(goomba);

        RenderingEngine.getInstance().getScreen().fullscreen();

        backGround = new BackGround();
        isfullscreen = true;
    }

    @Override
    public void update() {
        pauseMechanic();
        screenSizeMechanic();

        if (paused) {
            if (gamePad.isQuitPressed()) {
                stop();
            }
            camera.follow();
            RenderingRepository.getInstance().update();
        }



    }

    private void screenSizeMechanic() {
        if (gamePad.isScreenPressed() && isfullscreen) {
            RenderingEngine.getInstance().getScreen().screenToggle();
        }
        isfullscreen = !gamePad.isScreenPressed();
    }

    private void pauseMechanic() {
        boolean pausePressed = gamePad.isPausedPressed();
        if (pausePressed && !pauseWasPressed) {
            paused = !paused;
        }
        pauseWasPressed = pausePressed;
    }

    @Override
    public void draw(Canvas canvas) {
        backGround.draw(canvas);
        instance.drawRepository(canvas);
    }
}
