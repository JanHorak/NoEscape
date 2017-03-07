package net.janhorak.game.ne.engine.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.scene.input.KeyEvent;
import net.janhorak.game.ne.engine.GameState;
import net.janhorak.game.ne.engine.loops.ManagementLoop;
import org.apache.log4j.Logger;

/**
 *
 * @author Jan
 */
public class ControlManager {

    private static final String KEY_FILE = "/cfg/controls.properties";
    private static final Logger LOGGER = Logger.getLogger(ControlManager.class);

    public ControlManager() {
        initialize();
        LOGGER.info("Gamecontrols loaded...");
    }

    public static String MOVE_LEFT = "LEFT";
    public static String MOVE_RIGHT = "RIGHT";
    public static String MOVE_UP = "UP";
    public static String MOVE_DOWN = "DOWN";

    public static String BUTTON_PAUSE = "PAUSE";

    private List<String> userInput = new ArrayList<>();

    public List<String> getUserInput() {
        return userInput;
    }

    public void setUserInput(List<String> userInput) {
        this.userInput = userInput;
    }

    public void evalInputPressed(KeyEvent e) {
        String code = e.getCode().toString();
        if (ManagementLoop.gameState.isInState(GameState.GAME)
                && code.equals(BUTTON_PAUSE)) {
            ManagementLoop.tryToChangeState(GameState.PAUSE);

        } else if (ManagementLoop.gameState.isInState(GameState.PAUSE)
                && code.equals(BUTTON_PAUSE)) {
            ManagementLoop.tryToChangeState(GameState.GAME);
        } else {
            this.userInput.add(code);
        }
    }

    public void evalInputReleased(KeyEvent e) {
        String code = e.getCode().toString();
        this.userInput.remove(code);
    }

    public boolean isPressed(String code) {
        return this.userInput.contains(code);
    }

    private void initialize() {
        Properties prop = new Properties();
        InputStream in = getClass().getResourceAsStream(KEY_FILE);
        try {
            prop.load(in);
            in.close();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }

        MOVE_UP = prop.getProperty(MOVE_UP);
        MOVE_DOWN = prop.getProperty(MOVE_DOWN);
        MOVE_LEFT = prop.getProperty(MOVE_LEFT);
        MOVE_RIGHT = prop.getProperty(MOVE_RIGHT);
        BUTTON_PAUSE = prop.getProperty(BUTTON_PAUSE);

    }

}
