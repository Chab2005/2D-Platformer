package MarioBros;

import Doctrina.Canvas;
import Doctrina.Direction;
import Doctrina.MovableEntity;

public class Enemy extends MovableEntity {

    private Animation animation;

    public Enemy(){
        setDimension(16,16);
        moveTo(100,100);
        setSpeed(3);
        animation = new Animation(this);
        animation.load();
    }

    @Override
    public void update() {
        animation.idle();
    }

    @Override
    public void draw(Canvas canvas) {
        animation.drawFrame(Direction.RIGHT,canvas);
    }
}
