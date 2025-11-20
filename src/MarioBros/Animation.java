package MarioBros;

import Doctrina.*;
import Doctrina.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Animation {


    private static final int ANIMATION_SPEED = 8;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private int currentAnimationFrame = 0;
    private int nextFrame = ANIMATION_SPEED;
    private BufferedImage spriteSheet;
    private StaticEntity entity;
    private Direction lastDirection;
    private SpriteLoader spriteLoader;

    public Animation(StaticEntity entity) {
        this.entity = entity;
        this.spriteLoader = new SpriteLoader();
    }

    public void entityAnimation() {
        --nextFrame;
        if (nextFrame == 0) {
            ++currentAnimationFrame;
            if (currentAnimationFrame >= leftFrames.length) {
                currentAnimationFrame = 0;
            }
            nextFrame = ANIMATION_SPEED;
        }
    }

    public void idle() {
        currentAnimationFrame = 0; // Idle
    }

    public void drawFrame(Direction currentDirection, Canvas canvas) {

        if (currentDirection == Direction.RIGHT) {
            canvas.drawImage(rightFrames[currentAnimationFrame], entity);
            lastDirection = currentDirection;
        } else if (currentDirection == Direction.LEFT) {
            canvas.drawImage(leftFrames[currentAnimationFrame], entity);
            lastDirection = currentDirection;
        }  else if (currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
            if (lastDirection == Direction.RIGHT) {
                canvas.drawImage( spriteSheet.getSubimage(32,0,entity.getWidth(),entity.getHeight()) , entity);
            }
            if (lastDirection == Direction.LEFT) {
                canvas.drawImage(getFrame(),entity);
            }
        }
    }

    public void load() {
        spriteSheet = spriteLoader.loadSpriteSheet();
        loadAnimationFrames();
    }


    private void loadAnimationFrames() {

        leftFrames = new Image[4];
        leftFrames[0] = getFrame(0,18);
        leftFrames[1] = getFrame(0,54);
        leftFrames[2] = getFrame(0,71);
        leftFrames[3] = getFrame(0,36);

        rightFrames = new Image[4];
        rightFrames[0] = getFrame(32,18);
        rightFrames[1] = getFrame(32,54);
        rightFrames[2] = getFrame(32,71);
        rightFrames[3] = getFrame(32,36);

    }

    private BufferedImage getFrame() {
        return spriteSheet.getSubimage(0,0,entity.getWidth(),entity.getHeight());
    }

    private BufferedImage getFrame(int x, int y) {
        return spriteSheet.getSubimage(x,y,entity.getWidth(),entity.getHeight());
    }
}
