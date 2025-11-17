package MarioBros;

import Doctrina.Canvas;
import Doctrina.ControllableEntity;
import Doctrina.MovementController;

import java.awt.*;

public class Player extends ControllableEntity {

    private Animation animation;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
        animation = new Animation(this);
        animation.load();
    }

    @Override
    public void update() {
        super.update();
        moveWithController();
        if (hasMoved()) {
            animation.entityAnimation();
        } else {
            animation.idle();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        animation.drawFrame(getDirection() , canvas);
    }


}
