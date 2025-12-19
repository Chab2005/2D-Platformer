package Doctrina;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteLoader {
    private static final String SPRITE_PATH = "images/Player_SpriteSheet.png";



    public BufferedImage loadSpriteSheet() {
        try {
             return ImageIO.read(this.getClass().getClassLoader()
                    .getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage loadSpriteSheet(String path) {
        try {
            return ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
