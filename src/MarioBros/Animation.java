package MarioBros;

import Doctrina.*;
import Doctrina.Canvas;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation <E extends Enum<E> & State> {


    private static final int ANIMATION_SPEED = 8;

    private int currentAnimationFrame = 0;
    private int nextFrame = ANIMATION_SPEED;
    private BufferedImage spriteSheet;
    private StaticEntity entity;
    private State lastState;
    private Direction lastDirection;
    private SpriteLoader spriteLoader;
    private ArrayList<BufferedImage> currentFrame;

    public Animation(StaticEntity entity) {
        this.entity = entity;
        this.spriteLoader = new SpriteLoader();
        currentFrame = new ArrayList<>();
    }

    public void entityAnimation() {
        --nextFrame;
        if (nextFrame == 0) {
            ++currentAnimationFrame;
            if (currentAnimationFrame >= currentFrame.size()) {
                currentAnimationFrame = 0;
            }
            nextFrame = ANIMATION_SPEED;
        }
    }

    public void idle() {
        currentAnimationFrame = 0; // Idle
    }

    public void drawFramePlayer( Direction currentDirection,Canvas canvas, E currentState) {
        updateDirection(currentDirection);
        updateFrameState(currentState, currentDirection);
        canvas.drawImage(currentFrame.get(currentAnimationFrame),entity);

    }

    public void drawFramePlayer(Canvas canvas) {
        canvas.drawImage(currentFrame.get(currentAnimationFrame),entity);
    }

    public void getFrames(E currentState,Direction currentDirection) {
        resetFrame();
        setFrames(currentState, currentDirection);
    }

    private void setFrames(E currentState,Direction currentDirection) {
        int frames = currentState.getFrameAmmount();

        for (int i = 0 ; i < frames; i++ ) {
            currentFrame.add(
                    getFrame(
                            (currentState.getX()+shift(currentDirection)) ,
                            currentState.getY() + (i*36)
                    )
            );
        }

    }

    private void resetFrame() {
        currentFrame.removeAll(currentFrame);
    }

    private int shift(Direction currentDirection) {
        if (lastDirection == Direction.RIGHT || currentDirection == Direction.RIGHT) {
            return 64;
        }
        return 0;
    }

    public void load() {
        spriteSheet = spriteLoader.loadSpriteSheet();
    }

    private BufferedImage getFrame() {
        return spriteSheet.getSubimage(0,0,entity.getWidth(),entity.getHeight());
    }

    private BufferedImage getFrame(int x, int y) {
        return spriteSheet.getSubimage(x,y,entity.getWidth(),entity.getHeight());
    }


    private void updateFrameState(E currentState,Direction currentDirection) {
        if (currentState != lastState) {
            lastState = currentState;
            getFrames(currentState,currentDirection);
            //setFrames(currentState,currentDirection);
            currentAnimationFrame = 0;
        }
    }

    private void updateDirection(Direction direction) {
        if (direction != lastDirection) {
            lastDirection = direction;
        }
    }
}
