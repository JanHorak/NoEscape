package net.janhorak.game.ne.engine.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.scene.input.KeyEvent;
import org.apache.log4j.Logger;

/**
 *
 * @author Jan
 */
public class ControlManager {

    private boolean pauseMode;

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
        if (code.equals(BUTTON_PAUSE)) {
            if (!userInput.contains(BUTTON_PAUSE)){
                this.pauseMode = true;
                this.userInput.clear();
                this.userInput.add(code);
            } else {
                this.pauseMode = false; 
                this.userInput.clear();
            }
        } else if (!this.userInput.contains(code)) {
            this.userInput.add(code);
        }
    }

    public void evalInputReleased(KeyEvent e) {
        String code = e.getCode().toString();
        if (!code.equals(BUTTON_PAUSE)) {
            this.userInput.remove(code);
        }
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

    public boolean isPauseMode() {
        return pauseMode;
    }
}
