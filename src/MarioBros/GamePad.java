package MarioBros;

import Doctrina.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_ESCAPE;
    private int jumpKey = KeyEvent.VK_SPACE;
    private int leftKey = KeyEvent.VK_A;
    private int rightKey = KeyEvent.VK_D;
    private int screenKey = KeyEvent.VK_F;
    private int pauseKey = KeyEvent.VK_P;

    public GamePad() {
        bindKey(quitKey);
        bindKey(jumpKey);
        bindKey(leftKey);
        bindKey(rightKey);
        bindKey(screenKey);
        bindKey(pauseKey);
    }


    public boolean isPausedPressed() {return isKeyPressed(pauseKey);}

    public boolean isQuitPressed() {return isKeyPressed(quitKey);}


    public boolean isJumpPressed() {return isKeyPressed(jumpKey);}

    public boolean isScreenPressed() {return isKeyPressed(screenKey);}

}
