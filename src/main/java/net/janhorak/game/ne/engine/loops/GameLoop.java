package net.janhorak.game.ne.engine.loops;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javafx.animation.AnimationTimer;
import net.janhorak.game.ne.engine.services.ControlManager;
import net.janhorak.game.ne.engine.GameLogic;
import net.janhorak.game.ne.entities.GameSprite;
import net.janhorak.game.ne.engine.services.LocationService;
import net.janhorak.game.ne.engine.Renderer;
import net.janhorak.game.ne.entities.MoneyBag;
import net.janhorak.game.ne.entities.Thief;

/**
 *
 * @author Jan
 */
public class GameLoop extends AnimationTimer {

    private Renderer renderer;
    private ControlManager controlManager;
    private double lastNanoTime;
    GameLogic logic;

    Thief player = new Thief();

    Set<GameSprite> moneybagList = new HashSet<>();

    public GameLoop(Renderer renderer, ControlManager controlManager) {
        this.renderer = renderer;
        this.controlManager = controlManager;
        this.logic = new GameLogic();

        player.setControlManager(controlManager);
        LocationService.setRandomPosition(player);

        for (int i = 0; i < 15; i++) {
            MoneyBag moneybag = new MoneyBag();
            LocationService.setRandomPosition(moneybag);
            moneybagList.add(moneybag);
        }
    }

    @Override
    public void handle(long currentNanoTime) {
        // calculate time since last update.
        double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
        lastNanoTime = currentNanoTime;
        renderer.cleanSpriteList();

        logic.evaluateBehavior(player);
        player.update(elapsedTime);
        renderer.addToRenderer(player);

        // collision detection
        Iterator<GameSprite> moneybagIter = moneybagList.iterator();
        while (moneybagIter.hasNext()) {
            GameSprite moneybag = moneybagIter.next();
            if (player.intersects(moneybag)) {
                moneybagIter.remove();
                GameLogic.getScore().addPoints();
            }
        }
        renderer.addToRenderer(moneybagList);

        // ----  Renderphase -----
        renderer.render();
        String pointsText = "Cash: $" + GameLogic.getScore().get();
        renderer.renderText(pointsText, 100, 30);
    }

    public void updateGameTime(double newGameTime) {
        this.lastNanoTime = newGameTime;
    }
}
