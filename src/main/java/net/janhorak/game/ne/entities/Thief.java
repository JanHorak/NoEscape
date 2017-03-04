package net.janhorak.game.ne.entities;

import javafx.scene.image.Image;
import net.janhorak.game.ne.engine.GameSprite;

/**
 *
 * @author Jan
 */
public class Thief extends GameSprite {
    
    private static final String IMAGE_URL = "/images/Thief-icon.png";
    
    public Thief() {
        super.setImage(new Image(IMAGE_URL));
    }
    
    
}
