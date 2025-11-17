package UltraTank;

import Doctrina.Canvas;
import Doctrina.CollidableRepository;
import Doctrina.Game;
import Doctrina.StaticEntity;

import java.util.ArrayList;

public class UltraTankGame extends Game {

    private GamePad gamePad;
    private Tank tank;
    private ArrayList<Missile> missiles;
    private ArrayList<Brick> bricks;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        tank = new Tank(gamePad);
        missiles = new ArrayList<>();
        bricks = new ArrayList<>();
        bricks.add(new Brick(500, 100));
        bricks.add(new Brick(500, 116));
        bricks.add(new Brick(500, 132));
        bricks.add(new Brick(484, 148));
        bricks.add(new Brick(500, 164));
    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        if (gamePad.isFirePressed()) {
            Missile missile = tank.fire();
            if (missile != null) {
                missiles.add(missile);
            }
        }
        tank.update();
        ArrayList<StaticEntity> killedElements = new ArrayList<>();

        for (Missile missile : missiles) {
            missile.update();
            for (Brick brick : bricks) {
                if (missile.hitBoxIntersectsWith(brick)) {
                    killedElements.add(brick);
                    killedElements.add(missile);
                }
            }
        }

        for (StaticEntity entity : killedElements) {
            if (entity instanceof Brick) {
                bricks.remove(entity);
            }
            if (entity instanceof Missile) {
                missiles.remove(entity);
            }
        }
        CollidableRepository.getInstance().unregisterEntities(killedElements);
    }

    @Override
    public void draw(Canvas canvas) {
        tank.draw(canvas);
        for (Missile missile : missiles) {
            missile.draw(canvas);
        }
        for (Brick brick : bricks) {
            brick.draw(canvas);
        }
    }
}
