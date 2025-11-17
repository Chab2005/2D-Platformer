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

    private static final String SPRITE_PATH = "images/player.png";
    private static final int ANIMATION_SPEED = 8;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image[] upFrames;
    private Image[] downFrames;
    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;
    private BufferedImage spriteSheet;
    private StaticEntity entity;


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
        currentAnimationFrame = 1; // Idle
    }

    public void drawFrame(Direction currentDirection, Canvas canvas) {
        if (currentDirection == Direction.RIGHT) {
            canvas.drawImage(rightFrames[currentAnimationFrame], entity.getX(), entity.getY());
        } else if (currentDirection == Direction.LEFT) {
            canvas.drawImage(leftFrames[currentAnimationFrame], entity.getX(), entity.getY());
        } else if (currentDirection == Direction.UP) {
            canvas.drawImage(upFrames[currentAnimationFrame], entity.getX(), entity.getY());
        } else if (currentDirection == Direction.DOWN) {
            canvas.drawImage(downFrames[currentAnimationFrame], entity.getX(), entity.getY());
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
        downFrames = new Image[3];
        downFrames[0] = spriteSheet.getSubimage(0, 128, entity.getWidth(),entity.getHeight());
        downFrames[1] = spriteSheet.getSubimage(32, 128, entity.getWidth(),entity.getHeight());
        downFrames[2] = spriteSheet.getSubimage(64, 128, entity.getWidth(),entity.getHeight());

        leftFrames = new Image[3];
        leftFrames[0] = spriteSheet.getSubimage(0, 160, entity.getWidth(),entity.getHeight());
        leftFrames[1] = spriteSheet.getSubimage(32, 160, entity.getWidth(),entity.getHeight());
        leftFrames[2] = spriteSheet.getSubimage(64, 160, entity.getWidth(),entity.getHeight());

        rightFrames = new Image[3];
        rightFrames[0] = spriteSheet.getSubimage(0, 192, entity.getWidth(),entity.getHeight());
        rightFrames[1] = spriteSheet.getSubimage(32, 192, entity.getWidth(),entity.getHeight());
        rightFrames[2] = spriteSheet.getSubimage(64, 192, entity.getWidth(),entity.getHeight());

        upFrames = new Image[3];
        upFrames[0] = spriteSheet.getSubimage(0, 224, entity.getWidth(),entity.getHeight());
        upFrames[1] = spriteSheet.getSubimage(32, 224,entity.getWidth(),entity.getHeight());
        upFrames[2] = spriteSheet.getSubimage(64, 224, entity.getWidth(),entity.getHeight());
    }
}
