package MarioBros;

import Doctrina.Canvas;
import Doctrina.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class World extends StaticEntity {

    private static String mapPath = "images/level-1-1.png";
    private Image background;

    public World() {
        x = 0;
        y = 0;
    }



    public void load() {
        try {
            background = ImageIO.read(
                    this.getClass().getClassLoader().getResourceAsStream(mapPath)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawImage(background,x,y);
    }
}
