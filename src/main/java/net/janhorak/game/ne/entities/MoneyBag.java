package net.janhorak.game.ne.entities;

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

    @Override
    public void executeBehavior() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
