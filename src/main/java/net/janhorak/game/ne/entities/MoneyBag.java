package net.janhorak.game.ne.entities;

import net.janhorak.game.ne.engine.GameSprite;
import javafx.scene.image.Image;

/**
 *
 * @author Jan
 */
public class MoneyBag extends GameSprite {

    private static final String IMAGE_URL = "/images/money-bag-icon.png";
    
    public MoneyBag() {
        super.setImage(new Image(IMAGE_URL));
    }
}
