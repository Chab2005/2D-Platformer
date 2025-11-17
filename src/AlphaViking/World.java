package AlphaViking;

import Doctrina.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class World {

    private static final String MAP_PATH = "images/demo.png";
    private Image background;

    public void load() {
        try {
            background = ImageIO.read(
                    this.getClass().getClassLoader().getResourceAsStream(MAP_PATH)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawImage(background, 0, -64);
    }
}
