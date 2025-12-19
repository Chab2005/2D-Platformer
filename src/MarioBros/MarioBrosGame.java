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

    @Override
    public void initialize() {
        instance = RenderingRepository.getInstance();
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
        if (gamePad.isQuitPressed()) {
            stop();
        }

        if (gamePad.isScreenPressed() && isfullscreen) {

            RenderingEngine.getInstance().getScreen().screenToggle();
        }


        isfullscreen = !gamePad.isScreenPressed();

        camera.follow();



        player.update();
        goomba.update();
    }

    @Override
    public void draw(Canvas canvas) {
        backGround.draw(canvas);
        instance.drawRepository(canvas);
    }
}
