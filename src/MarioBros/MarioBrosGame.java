package MarioBros;

import Doctrina.*;
import Doctrina.Canvas;
import Doctrina.RenderingRepository;

import java.awt.*;



public class MarioBrosGame extends Game {


    private RenderingRepository instance;
    private Player player;
    private GamePad gamePad;
    private World world;
    private Camera camera;
    private BackGround backGround;



    private boolean winState;
    private boolean isfullscreen;
    private boolean paused;
    private boolean pauseWasPressed;

    @Override
    public void initialize() {

        gamePad = new GamePad();
        winState = false;
        paused = true;
        pauseWasPressed = false;
        instance = RenderingRepository.getInstance();

        SoundEffect.MUSIC_1_1.play();
        initGame();
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

    @Override
    public void draw(Canvas canvas) {
        backGround.draw(canvas);
        instance.drawRepository(canvas);
        if (!paused) {
            canvas.drawString("PAUSED",375,250,Color.WHITE);
        }
        if (winState) {

            canvas.drawString("YOU WON",375,250,Color.WHITE);
        }
    }

    private void mainUpdateLoop() {
        if (paused && !winState) {
            camera.follow();
            instance.update();
            unloadJumpedEnemies();
            hasPlayerWon();
            player.setLastStaticEntity();
        }
    }

    private void hasPlayerWon() {

        if (isFlagValid()) {
            winState = true;
            SoundEffect.MUSIC_1_1.stop();
            SoundEffect.WIN.playOnce();
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
            SoundEffect.PAUSE.playOnce();
        }
        pauseWasPressed = pausePressed;
    }

    private boolean idEntityNotDefault(int id) {
        return id != -1;
    }

    private void unloadJumpedEnemies() {
        if (isEnemyValid()) {
            moveEntityToShadowRealm();
            removeFromRepositories();
        }
    }

    private boolean isFlagValid() {
        return isIdEntityValid() && instance.getStaticEntities().get(player.getLastStaticEntity()) instanceof FlagPole;
    }

    private boolean isEnemyValid() {
        return isIdEntityValid() && instance.getStaticEntities().get(player.getLastStaticEntity()) instanceof Enemy;
    }

    private boolean isIdEntityValid() {
        return idEntityNotDefault(player.getLastStaticEntity());
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

    private void initGame() {
        initWorld();
        initPlayer();
        initGameScreen();
    }

    private void initGameScreen() {
        RenderingEngine.getInstance().getScreen().fullscreen();
        backGround = new BackGround();
        isfullscreen = true;
    }

    private void initWorld() {
        world = new World();
        world.load();
    }

    private void initPlayer() {
        player = new Player(gamePad);
        instance.registerEntities(player);
        camera = new Camera(player);
    }
}
