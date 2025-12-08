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
    private BufferedImage currentFrame;

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
        // has to look like that at the end
        // canvas.drawImage(currentFrame,entity);

        if (currentDirection == Direction.RIGHT) {
            canvas.drawImage(rightFrames[currentAnimationFrame], entity);
            lastDirection = currentDirection;

        } else if (currentDirection == Direction.LEFT) {
            canvas.drawImage(leftFrames[currentAnimationFrame], entity);
            lastDirection = currentDirection;

        }  else if (currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
            if (lastDirection == Direction.RIGHT) {
                canvas.drawImage( spriteSheet.getSubimage(64,0,entity.getWidth(),entity.getHeight()) , entity);
            } else  {
                canvas.drawImage(getFrame(),entity);
            }
        }
    }

    public void setFrame(PlayerStates state) {
        currentFrame = spriteSheet.getSubimage(state.getX() + shift() ,state.getY(),entity.getWidth(),entity.getHeight());
    }

    private int shift() {
        if (lastDirection == Direction.RIGHT) {
            return 64;
        }
        return 0;
    }

    public void load() {
        spriteSheet = spriteLoader.loadSpriteSheet();
        loadAnimationFrames();
    }


    private void loadAnimationFrames() {

        leftFrames = new Image[4];
        leftFrames[0] = getFrame(0,36);
        leftFrames[1] = getFrame(0,105);
        leftFrames[2] = getFrame(0,142);
        leftFrames[3] = getFrame(0,72);

        rightFrames = new Image[4];
        rightFrames[0] = getFrame(64,36);
        rightFrames[1] = getFrame(64,108);
        rightFrames[2] = getFrame(64,142);
        rightFrames[3] = getFrame(64,72);

    }

    private BufferedImage getFrame() {
        return spriteSheet.getSubimage(0,0,entity.getWidth(),entity.getHeight());
    }

    private BufferedImage getFrame(int x, int y) {
        return spriteSheet.getSubimage(x,y,entity.getWidth(),entity.getHeight());
    }
}
