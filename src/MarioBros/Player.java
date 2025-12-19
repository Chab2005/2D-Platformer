package MarioBros;

import Doctrina.*;
import Doctrina.Canvas;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Player extends ControllableEntity {

    private Animation<PlayerStates> animation;
    private Collision collision;
    private EntityCollision entityCollision;

    private final int JUMP_FORCE = 32*5;
    private final int SPEED = 6;
    private final int STOMPING_SCORE = 100;
    private int jumpHeight;


    private PlayerState currentState;
    private PlayerStates playerState;
    private GamePad gamePad;
    private boolean isAlive;

    private int score;

    public Player(GamePad controller) {
        super(controller);
        gamePad = controller;
        score = 0;
        isAlive = true;
        entityCollision = new EntityCollision();
        currentState = new StateFalling();
        playerState = PlayerStates.FALLING;
        setDimension(32, 32);
        moveTo(150,450);

        setSpeed(6);
        collision = new Collision(this);
        this.animation = new Animation<>(this);
        animation.load();
    }

    @Override
    public void update() {
        if (isAlive) {
            super.update();
            moveWithController();
            lockMovement();
            animation.entityAnimation();

            playerCollisionUpdate();

        }
        stateUpdate();

    }

    @Override
    public void draw(Canvas canvas) {
        animation.drawFramePlayer(getDirection(),canvas, playerState);
        if (!isAlive) {
            canvas.drawString("DEAD",375,250, Color.WHITE);
        }
        canvas.drawString("Score: "+score,700,20, Color.WHITE);
    }

    private void playerCollisionUpdate() {
        if (playerJumpOnEnemy()) {
            calculateJumpHeight();
            currentState = new StateJump();
            stompSound();
        } else {
            updateHp();
        }
    }

    private void stompSound() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            SoundEffect.STOMP.playOnce();
            score+=STOMPING_SCORE;
        } else  {
            SoundEffect.MURLOC.playOnce();
            score+=STOMPING_SCORE*10;
        }

    }

    public int getLastStaticEntity() {
        return entityCollision.getLastStaticEntity();
    }

    public void setLastStaticEntity() {
        entityCollision.setLastStaticEntity();
    }

    private boolean playerJumpOnEnemy() {

        return isPlayerRunningIntoEnemy() && collision.getAllowedSpeedDown() > 0;
    }

    private boolean isPlayerRunningIntoEnemy() {
        return entityCollision.isPlayerInCollision(this);
    }

    private void updateHp() {
        if (isPlayerInVoid() || isPlayerRunningIntoEnemy()) {
            isAlive = false;
            SoundEffect.DIE.playOnce();
            currentState = new StateDead();
        }
    }




    public void updateState(PlayerState newState) {
        currentState.exit(this);
        currentState = newState;
        currentState.enter(this);
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

    public void jumpEffect() {
        y  -= SPEED;
    }

    public void moveDown() {
        y += collision.getAllowedSpeedDown();
    }

    public void deadFall() {
        y += SPEED/3;
    }

    private void lockMovement() {
        lockMovementDown();
        lockMovementUp();
    }

    private void lockMovementUp() {
        if (isMoving() && getDirection() == Direction.UP) {
            y+=getSpeed();
        }
    }

    private void lockMovementDown() {
        if (isMoving() && getDirection() == Direction.DOWN) {
            y-=getSpeed();
        }
    }

    private void stateUpdate() {
        currentState.update(this);
        updateState(currentState);
    }

    private boolean isPlayerInVoid() {
        return y >= 600;
    }
}
