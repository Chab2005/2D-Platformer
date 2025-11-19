package MarioBros;

import Doctrina.Canvas;
import Doctrina.Direction;
import Doctrina.MovableEntity;
import Doctrina.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Animation {

    private static final String SPRITE_PATH = "images/player-sheet.png";
    private static final int ANIMATION_SPEED = 8;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private int currentAnimationFrame = 0;
    private int nextFrame = ANIMATION_SPEED;
    private BufferedImage spriteSheet;
    private StaticEntity entity;
    private Direction lastDirection;


    public Animation(StaticEntity entity) {
        this.entity = entity;
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
            canvas.drawImage(rightFrames[currentAnimationFrame], entity.getX(), entity.getY());
            lastDirection = currentDirection;
        } else if (currentDirection == Direction.LEFT) {
            canvas.drawImage(leftFrames[currentAnimationFrame], entity.getX(), entity.getY());
            lastDirection = currentDirection;
        }  else if (currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
            if (lastDirection == Direction.RIGHT) {
                canvas.drawImage(spriteSheet.getSubimage(32,0,entity.getWidth(),entity.getHeight()), entity.getX(), entity.getY());
            }
            if (lastDirection == Direction.LEFT) {
                canvas.drawImage(spriteSheet.getSubimage(0,0,entity.getWidth(),entity.getHeight()), entity.getX(), entity.getY());
            }
        }
    }

    public void load() {
        loadSpriteSheet();
        loadAnimationFrames();
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader()
                    .getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAnimationFrames() {

        leftFrames = new Image[3];
        leftFrames[0] = spriteSheet.getSubimage(0, 18, entity.getWidth(),entity.getHeight());
        leftFrames[1] = spriteSheet.getSubimage(0, 36, entity.getWidth(),entity.getHeight());
        leftFrames[2] = spriteSheet.getSubimage(0, 18+36, entity.getWidth(),entity.getHeight());

        rightFrames = new Image[3];
        rightFrames[0] = spriteSheet.getSubimage(32, 18, entity.getWidth(),entity.getHeight());
        rightFrames[1] = spriteSheet.getSubimage(32, 36, entity.getWidth(),entity.getHeight());
        rightFrames[2] = spriteSheet.getSubimage(32, 18+36, entity.getWidth(),entity.getHeight());

    }
}
