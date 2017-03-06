package net.janhorak.game.ne.engine;

import net.janhorak.game.ne.entities.GameSprite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.janhorak.game.ne.basic.Score;

/**
 *
 * @author Jan
 */
public class GameLogic {
    
    private static Score score = new Score(0, 1);
    
    private Map<String, GameAction> behaviour = new HashMap<>();
    private List<GameSprite> gameObjects = new ArrayList<>();
    
    public void evaluateBehavior(GameSprite sprite){
        sprite.executeBehavior();
    }
    
    public void registerGameObject(GameSprite sprite){
        this.gameObjects.add(sprite);
    }

    public static Score getScore() {
        return score;
    }
    
}
