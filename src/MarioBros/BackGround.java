package MarioBros;

import Doctrina.Canvas;
import Doctrina.StaticEntity;

import java.awt.*;

public class BackGround extends StaticEntity {
    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(0,0,Integer.MAX_VALUE,Integer.MAX_VALUE, Color.decode("#63adff") );
    }
}
