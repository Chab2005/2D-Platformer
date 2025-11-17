package AmazingFootprint;

import Doctrina.Canvas;
import Doctrina.ControllableEntity;
import Doctrina.MovableEntity;

import java.awt.*;

public class Player extends ControllableEntity {

    public Player(GamePad gamePad) {
        super(gamePad);
        moveTo(200, 200);
        setSpeed(3);
        setDimension(20, 60);
    }

    public void update() {
        moveWithController();
    }

    public Footprint layFootprint() {
        return new Footprint(x, y);
    }

    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.WHITE);
    }
}
