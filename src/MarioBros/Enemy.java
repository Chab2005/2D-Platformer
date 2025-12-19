package MarioBros;

import Doctrina.*;

public abstract class Enemy extends MovableEntity {

    protected Animation<PlayerStates> animation;
    private Collision collision;




    public Enemy(){

        setDimension(32,32);
        moveTo(100,100);

        setSpeed(3);
        setDirection(Direction.RIGHT);
        collision = new Collision(this);
        this.animation = new Animation<>(this);
        animation.load("images/entity/goomba.png");
        animation.setEntityFrames(3);
    }

    @Override
    public void update() {
        baseEnemyAI();
        animation.entityAnimation();
    }

    @Override
    public void draw(Canvas canvas) {
        animation.drawFrameEntity(canvas);
    }

    protected void baseEnemyAI() {
        downMovement();
        rightMovement();
        leftMovement();
    }


    private void leftMovement() {
        if (getDirection() == Direction.LEFT ) {
            if (collision.canEntityMoveLeft()) {
                move(Direction.LEFT);
            } else {
                setDirection(Direction.RIGHT);
            }
        }
    }

    private void rightMovement() {
        if (getDirection() == Direction.RIGHT ) {
            if (collision.canEntityMoveRight()) {
                move(Direction.RIGHT);
            } else  {
                setDirection(Direction.LEFT);
            }
        }
    }

    private void downMovement() {
        if (collision.getAllowedSpeedDown() > 0 ) {
            move(Direction.DOWN);
        } else if (getDirection() == Direction.DOWN && !collision.canEntityMoveDown()) {
            setDirection(Direction.RIGHT);
        }
    }

}
