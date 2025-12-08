package MarioBros;

import Doctrina.*;
import Doctrina.Canvas;

import java.awt.*;

public class Player extends ControllableEntity {

    private Animation animation;
    private Collision collision;
    private final int SPEED = 6;
    private PlayerState currentState;

    public Player(MovementController controller) {
        super(controller);
        currentState = new StateIdle();
        setDimension(32, 32);
        moveTo(150,150);
        setSpeed(6);
        collision = new Collision(this);
        this.animation = new Animation(this);
        animation.load();
    }

    @Override
    public void update() {
        super.update();
        moveWithController();


        //changeState(currentState);
        currentState.update(this);
        if (isMoving()) {
            setSpeed(collision.getAllowedSpeed(getDirection()));
            animation.entityAnimation();

        } else {
            setSpeed(SPEED);
            animation.idle();
        }




    }

    public void changeState(PlayerState newState) {
        currentState.exit(this);
        currentState = newState;
        currentState.enter(this);
    }

    @Override
    public void draw(Canvas canvas) {
        animation.drawFrame(getDirection() , canvas);
    }

    public boolean canMoveDown() {
        return collision.getAllowedSpeed(Direction.DOWN) > 0;
    }
}
