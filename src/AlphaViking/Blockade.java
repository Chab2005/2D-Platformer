package AlphaViking;

import Doctrina.Canvas;
import Doctrina.CollidableRepository;
import Doctrina.StaticEntity;

import java.awt.*;

public class Blockade extends StaticEntity {

    public Blockade() {
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Canvas canvas) {
        if (Config.DEBUG) {
            canvas.drawRectangle(this, new Color(255, 0, 0, 100));
        }
    }
}
