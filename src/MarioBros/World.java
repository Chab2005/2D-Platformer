package MarioBros;

import Doctrina.Canvas;
import Doctrina.CollidableRepository;
import Doctrina.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class World extends StaticEntity {

    private static String mapPath = "images/tilesheet/tilesheet.png";
    private Image background;
    private CollidableRepository collidableRepository;

    public World() {
        collidableRepository = collidableRepository.getInstance();
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
