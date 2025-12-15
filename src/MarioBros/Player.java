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

    public boolean playerJumpFinished() {
        return y < jumpHeight;
    }


    private void calculateJumpHeight() {
        jumpHeight = y - JUMP_FORCE;
    }



    public boolean isGrounded() {
        return collision.getAllowedSpeedDown() <= 0;
    }

    public State getCurrentState() {
        return playerState;
    }


}
