package MarioBros;

import Doctrina.Canvas;
import Doctrina.CollidableRepository;
import Doctrina.RenderingRepository;
import Doctrina.StaticEntity;

import java.awt.*;

public class Brick extends StaticEntity {

    private Image sprite;

    public Brick(int x, int y) {
        setDimension(16, 16);
        moveTo(x, y);
        CollidableRepository.getInstance().registerEntity(this);
    }

    public Brick(int x, int y,Image sprite) {
        this.sprite = sprite;
        setDimension(16, 16);
        moveTo(x, y);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawImage(this.sprite, x, y);
    }
}
