package net.janhorak.game.ne.entities;

import javafx.scene.image.Image;
import net.janhorak.game.ne.engine.services.ControlManager;
import net.janhorak.game.ne.engine.behavior.Controllable;

/**
 *
 * @author Jan
 */
public class Thief extends GameSprite implements Controllable {

    private static final String IMAGE_URL = "/images/Thief-icon.png";
    private ControlManager cm;

    public Thief() {
        super.setImage(new Image(IMAGE_URL));
    }

    @Override
    public void setControlManager(ControlManager mgr) {
        this.cm = mgr;
    }

    @Override
    public void executeBehavior() {
        if (cm.isPressed(ControlManager.MOVE_LEFT)) {
            this.addVelocity(-50, 0);
        }
        if (cm.isPressed(ControlManager.MOVE_RIGHT)) {
            this.addVelocity(50, 0);
        }
        if (cm.isPressed(ControlManager.MOVE_UP)) {
            this.addVelocity(0, -50);
        }
        if (cm.isPressed(ControlManager.MOVE_DOWN)) {
            this.addVelocity(0, 50);
        }
    }

}
