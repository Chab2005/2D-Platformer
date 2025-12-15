package MarioBros;

import Doctrina.*;
import Doctrina.Canvas;

public class Player extends ControllableEntity {

    private Animation<PlayerStates> animation;
    private Collision collision;
    private final int JUMP_FORCE = 32*5;
    private int jumpHeight;
    private final int SPEED = 6;
    private EntityState currentState;
    private PlayerStates playerState;
    private GamePad gamePad;

    public Player(GamePad controller) {
        super(controller);
        gamePad = controller;

        currentState = new StateFalling();
        playerState = PlayerStates.FALLING;
        setDimension(32, 32);
        moveTo(150,150);

        setSpeed(6);
        collision = new Collision(this);
        this.animation = new Animation<>(this);
        animation.load();
    }

    @Override
    public void update() {
        super.update();
        moveWithController();

        animation.entityAnimation();
        /*
        if (isJumping()) {
            currentState = new StateJump();
        }
        if (canMoveDown() && !isJumping() && !isMoving()) {
            currentState = new StateFalling();
        }
        */


        currentState.update(this);
        updateState(currentState);

    }

    public void updateState(EntityState newState) {
        currentState.exit(this);
        currentState = newState;
        currentState.enter(this);

    }

    @Override
    public void draw(Canvas canvas) {
        animation.drawFramePlayer(getDirection(),canvas, playerState);
    }

    public boolean canMoveDown() {

        return collision.getAllowedSpeed(Direction.DOWN) > 0;
    }

    public boolean canMoveUp() {
        return collision.getAllowedSpeed(Direction.UP) > 0;
    }

    public void setAnimation(PlayerStates state) {
        playerState = state;
        animation.getFrames(state,getDirection());
    }

    public boolean isJumping() {
        calculateJumpHeight();
        return gamePad.isJumpPressed();
    }

    private boolean canJump() {
        return gamePad.isJumpPressed() && playerState == PlayerStates.IDLE ;
    }

    public boolean playerJumpFinished() {
        return y < jumpHeight;
    }

    public void jumpEffect() {
        y -= SPEED*2;
    }

    private void calculateJumpHeight() {
        jumpHeight = y - JUMP_FORCE;
    }

    public boolean canPlayerMoveDown() {
        return collision.getAllowedSpeedDown() <= 0;
    }

    public boolean isGrounded() {

        System.out.println(collision.getAllowedSpeedDown());
        return collision.getAllowedSpeedDown() <= 0;
    }


}
