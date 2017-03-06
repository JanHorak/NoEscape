package net.janhorak.game.ne.engine.loops;

import javafx.animation.AnimationTimer;
import net.janhorak.game.ne.engine.services.ControlManager;
import net.janhorak.game.ne.engine.Renderer;

/**
 *
 * @author Jan
 */
public class ApplicationLoop extends AnimationTimer {

    private GameLoop gameLoop;
    private ControlManager controlManager;
    private Renderer renderer;
    private double nanoTime;

    public ApplicationLoop(ControlManager controlManager, Renderer renderer, double nanoTime) {
        this.controlManager = controlManager;
        this.renderer = renderer;
        this.nanoTime = nanoTime;
        this.gameLoop = new GameLoop(renderer, controlManager, nanoTime);
    }

    @Override
    public void handle(long now) {
        if (controlManager.getUserInput().contains("P")) {
            gameLoop.stop();
            String pointsText = "Pause";
            renderer.renderText(pointsText, 0, 50);

            nanoTime = System.nanoTime();
            gameLoop.updateGameTime(nanoTime);
        } else {
            gameLoop.start();
        }
    }

}
