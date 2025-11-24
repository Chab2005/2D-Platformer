package MarioBros;

import Doctrina.Canvas;
import Doctrina.CollidableRepository;
import Doctrina.StaticEntity;

import java.awt.*;

public class Brick extends StaticEntity {

    private final Image sprite;

    public Brick(int x, int y,Image sprite) {
        this.sprite = sprite;
        setDimension(16, 16);
        moveTo(x, y);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawImage(this);
    }
}
