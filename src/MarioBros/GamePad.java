package MarioBros;

import Doctrina.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_ESCAPE;
    private int fireKey = KeyEvent.VK_L;
    private int jumpKey = KeyEvent.VK_SPACE;
    private int leftKey = KeyEvent.VK_A;
    private int rightKey = KeyEvent.VK_D;

    public GamePad() {
        bindKey(quitKey);
        bindKey(fireKey);
        bindKey(jumpKey);
        bindKey(leftKey);
        bindKey(rightKey);
    }

    public boolean isQuitPressed() {
        return isKeyPressed(quitKey);
    }

    public boolean isFirePressed() {
        return isKeyPressed(fireKey);
    }

    public boolean isJumpPressed() {return isKeyPressed(jumpKey);}

    public boolean screenFormat(boolean screen) {return !screen;}

    //public boolean isLeftPressed() {return isKeyPressed(leftKey);}
    //public boolean isRightPressed() {return isKeyPressed(rightKey);}
}
