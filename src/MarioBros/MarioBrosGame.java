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

    private boolean started;
    private boolean isfullscreen;
    private boolean paused;
    private boolean pauseWasPressed;

    @Override
    public void initialize() {
        started = false;
        instance = RenderingRepository.getInstance();
        paused = true;
        world = new World();

        world.load();
        pauseWasPressed = false;
        //SoundEffect.MUSIC_1_1.play();
        gamePad = new GamePad();
        player = new Player(gamePad);
        //goomba = new Goomba();



        camera = new Camera(player);

        instance.registerEntities(player);
        //instance.registerEntities(goomba);

        RenderingEngine.getInstance().getScreen().fullscreen();

        backGround = new BackGround();
        isfullscreen = true;
    }

    @Override
    public void update() {

        pauseMechanic();
        screenSizeMechanic();
        mainUpdateLoop();
        if (gamePad.isQuitPressed()) {
            stop();
        }
    }

    private void mainUpdateLoop() {
        if (paused) {
            camera.follow();
            instance.update();
            unloadJumpedEnemies();


        }
    }




    private void screenSizeMechanic() {
        if (gamePad.isScreenPressed() && isfullscreen) {
            RenderingEngine.getInstance().getScreen().screenToggle();
        }
        isfullscreen = !gamePad.isScreenPressed();
    }



    @Override
    public void draw(Canvas canvas) {

        backGround.draw(canvas);
        instance.drawRepository(canvas);
        if (!paused) {
            canvas.drawString("PAUSED",375,250,Color.WHITE);
        }
    }

    private void pauseMechanic() {

        boolean pausePressed = gamePad.isPausedPressed();
        if (pausePressed && !pauseWasPressed) {
            paused = !paused;
            SoundEffect.PAUSE.playOnce();

        }
        pauseWasPressed = pausePressed;
    }

    private boolean idEntityNotDefault(int id) {
        return id != -1;
    }

    private void unloadJumpedEnemies() {
        if (isEntityValid()) {
            moveEntityToShadowRealm();
            removeFromRepositories();
            player.setLastStaticEntity();
        }
    }

    private boolean isEntityValid() {
        return idEntityNotDefault(player.getLastStaticEntity()) && instance.getStaticEntities().get(player.getLastStaticEntity()) instanceof Enemy;
    }

    private void removeFromRepositories() {
        CollidableRepository.getInstance().unregisterEntity(
                instance.getStaticEntities().get(player.getLastStaticEntity())
        );
        instance.unregisterEntities( player.getLastStaticEntity());
    }

    private void moveEntityToShadowRealm() {
        instance.getStaticEntities().get(player.getLastStaticEntity()).moveTo(10000,10000);
    }
}
