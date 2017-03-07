package net.janhorak.game.ne.engine.loops;

import com.sun.istack.internal.logging.Logger;
import javafx.animation.AnimationTimer;
import net.janhorak.game.ne.engine.GameState;
import net.janhorak.game.ne.engine.Renderer;
import net.janhorak.game.ne.engine.services.ControlManager;

/**
 *
 * @author Jan
 */
public class ManagementLoop extends AnimationTimer {

    private static final Logger LOGGER = Logger.getLogger(ManagementLoop.class);

    private ApplicationLoop applicationLoop;
    private Renderer renderer;
    public static GameState gameState;
    private ControlManager controlManager;
    private boolean freshGame = true;

    public ManagementLoop(GameState state, ControlManager controlManager,
            Renderer renderer, double lastNanoTime) {
        gameState = state;
        this.renderer = renderer;
        this.controlManager = controlManager;
        applicationLoop = new ApplicationLoop(controlManager, renderer, lastNanoTime);
    }

    @Override
    public void handle(long now) {
        if (gameState.isInState(GameState.MENU)) {
            if (freshGame) {
                String mainm = "Welcome, press G for gaming :)";
                renderer.renderText(mainm, 90, 250);
                if (controlManager.isPressed("G")) {
                    tryToChangeState(GameState.GAME);
                    applicationLoop.start();
                    applicationLoop.startGame();
                    freshGame = false;
                }
            } else {
                applicationLoop.stopGame();
                String mainm = "Mainmenu";
                renderer.renderText(mainm, 90, 250);
            }
        } else if (gameState.isInState(GameState.GAME)) {
            if (!applicationLoop.isPlaying()) {
                applicationLoop.startGame();
            }
        } else if (gameState.isInState(GameState.PAUSE)) {
            String pauseString = "Pause";
            renderer.renderText(pauseString, 250, 250);
            if (applicationLoop.isPlaying()) {
                applicationLoop.stopGame();
            }
        }

    }

    public static void tryToChangeState(GameState nextState) {
        if (gameState.isChangeAllowed(nextState)) {
            LOGGER.info("Gamestate changed from " + gameState + " to " + nextState);
            gameState = nextState;

        }
    }

}
