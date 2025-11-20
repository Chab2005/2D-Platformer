package MarioBros;

import Doctrina.Canvas;
import Doctrina.ControllableEntity;
import Doctrina.Direction;
import Doctrina.MovementController;

import java.awt.*;

public class Player extends ControllableEntity {

    private Animation animation;

    public Player(MovementController controller) {
        super(controller);
        setDimension(16, 16);
        moveTo(150,150);
        setSpeed(3);
        this.animation = new Animation(this);
        animation.load();
    }

    @Override
    public void update() {
        super.update();
        moveWithController();
        if (isMoving()) {
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
