package UltraTank;

import Doctrina.Canvas;
import Doctrina.CollidableRepository;
import Doctrina.Direction;
import Doctrina.MovableEntity;

import java.awt.*;

public class Missile extends MovableEntity {

    public Missile(Tank tank) {
        setSpeed(5);
        setDirection(tank.getDirection());
        initialize(tank);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void update() {
        super.update();
        move();
        if (x >= 820) {
            x = -20;
        }
        if (y >= 620) {
            y = -20;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.YELLOW);
    }

    private void initialize(Tank tank) {
        if (tank.getDirection() == Direction.RIGHT) {
            moveTo(tank.getX() + tank.getWidth() + 1,
                    tank.getY() + 15 - 1);
            setDimension(8, 4);
        } else if (tank.getDirection() == Direction.LEFT) {
            moveTo(tank.getX() - 9, tank.getY() + 15 - 1);
            setDimension(8, 4);
        } else if (tank.getDirection() == Direction.DOWN) {
            moveTo(tank.getX() + 15 - 1,
                    tank.getY() + tank.getHeight() + 1);
            setDimension(4, 8);
        } else if (tank.getDirection() == Direction.UP) {
            moveTo(tank.getX() + 15 - 1, tank.getY() - 9);
            setDimension(4, 8);
        }
    }
}
