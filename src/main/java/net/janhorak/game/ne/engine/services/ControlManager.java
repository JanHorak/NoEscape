package net.janhorak.game.ne.engine.services;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Jan
 */
public class ControlManager {

    public static String MOVE_LEFT = "LEFT";
    public static String MOVE_RIGHT = "RIGHT";
    public static String MOVE_UP = "UP";
    public static String MOVE_DOWN = "DOWN";

    public static String BUTTON_PAUSE = "P";
    

    private List<String> userInput = new ArrayList<>();

    public List<String> getUserInput() {
        return userInput;
    }

    public void setUserInput(List<String> userInput) {
        this.userInput = userInput;
    }

    public void evalInputPressed(KeyEvent e) {
        String code = e.getCode().toString();
        if (!this.userInput.contains(code)) {
            this.userInput.add(code);
        } else if (this.userInput.contains(BUTTON_PAUSE)) {
            this.userInput.remove(code);
        }
    }

    public void evalInputReleased(KeyEvent e) {
        String code = e.getCode().toString();
        if (!code.equals(BUTTON_PAUSE)) {
            this.userInput.remove(code);
        }
    }
}
