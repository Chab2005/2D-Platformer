package AmazingFootprint;

import Doctrina.Canvas;
import Doctrina.Game;

import java.awt.*;
import java.util.ArrayList;

public class AmazingFootprintGame extends Game {

    private Player playerOne;
    private Player playerTwo;
    private GamePad gamePadOne;
    private GamePad gamePadTwo;
    private ArrayList<Footprint> footprints;

    @Override
    public void initialize() {
        gamePadOne = new GamePad();
        gamePadTwo = new GamePad();
        gamePadTwo.useWasdKeys();

        playerOne = new Player(gamePadOne);
        playerTwo = new Player(gamePadTwo);
        footprints = new ArrayList<>();
    }

    @Override
    public void update() {
        if (gamePadOne.isQuitPressed()) {
            stop();
        }
        playerOne.update();
        playerTwo.update();
        if (gamePadOne.isMoving()) {
            footprints.add(playerOne.layFootprint());
        }
        if (gamePadTwo.isMoving()) {
            footprints.add(playerTwo.layFootprint());
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(0, 0, 800, 600, Color.BLUE);
        drawFootprints(canvas);
        playerOne.draw(canvas);
        playerTwo.draw(canvas);
    }

    private void drawFootprints(Canvas canvas) {
        for (Footprint footprint : footprints) {
            footprint.draw(canvas);
        }
    }
}
