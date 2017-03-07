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
    
    private boolean playing;
    
    public ApplicationLoop(ControlManager controlManager, Renderer renderer, double nanoTime) {
        this.playing = false;
        this.controlManager = controlManager;
        this.renderer = renderer;
        this.nanoTime = nanoTime;
        this.gameLoop = new GameLoop(renderer, controlManager);
    }

    @Override
    public void handle(long now) {
        if (playing) {
            gameLoop.start();
        } else {
            gameLoop.updateGameTime(now);
            gameLoop.stop();
        }
    }
    
    public void stopGame(){
        playing = false;
    }
    
    public void startGame(){
        playing = true;
    }

    public boolean isPlaying() {
        return playing;
    }
}
