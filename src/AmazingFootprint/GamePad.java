package AmazingFootprint;

import Doctrina.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_ESCAPE;

    public GamePad() {
        bindKey(quitKey);
    }

    public boolean isQuitPressed() {
        return isKeyPressed(quitKey);
    }
}
