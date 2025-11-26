package MarioBros;

import Doctrina.Canvas;
import Doctrina.CollidableRepository;
import Doctrina.StaticEntity;

import java.awt.*;

public class Brick extends StaticEntity {

    private Image spr;

    public Brick(int x, int y) {
        setDimension(16, 16);
        moveTo(x, y);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawImage(this.spr, 0, 0);
    }
}
