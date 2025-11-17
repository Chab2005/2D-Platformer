package Doctrina;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Controller implements KeyListener {

    private HashMap<Integer, Boolean> pressedKeys;

    public Controller() {
        pressedKeys = new HashMap<>();
        RenderingEngine.getInstance().addKeyListener(this);
    }

    public void bindKey(int keyCode) {
        pressedKeys.put(keyCode, false);
    }

    public void clearKeys() {
        pressedKeys.clear();
    }

    public void removeKey(int keyCode) {
        pressedKeys.remove(keyCode);
    }

    public boolean isKeyPressed(int keyCode) {
        return pressedKeys.containsKey(keyCode)
                && pressedKeys.get(keyCode);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (pressedKeys.containsKey(keyCode)) {
            pressedKeys.put(keyCode, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (pressedKeys.containsKey(keyCode)) {
            pressedKeys.put(keyCode, false);
        }
    }
}
