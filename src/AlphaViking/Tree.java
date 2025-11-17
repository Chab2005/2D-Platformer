package AlphaViking;

import Doctrina.Canvas;
import Doctrina.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Tree extends StaticEntity {

    private static final String SPRITE_PATH = "images/tree.png";
    private Image image;
    private Blockade blockade;

    public Tree(int x, int y) {
        moveTo(x, y);
        blockade = new Blockade();
        blockade.setDimension(30, 16);
        blockadeFromTop();
        load();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawImage(image, x, y);
        blockade.draw(canvas);
    }

    public void blockadeFromTop() {
        blockade.moveTo(x + 16, y + 64);
    }

    public void blockageFromBottom() {
        blockade.moveTo(x + 16, y + 48);
    }

    private void load() {
        try {
            image = ImageIO.read(this.getClass().getClassLoader()
                    .getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
