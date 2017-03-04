package net.janhorak.game.ne;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import net.janhorak.game.ne.engine.GameEnvironment;
import net.janhorak.game.ne.engine.GameSprite;
import net.janhorak.game.ne.engine.Renderer;
import net.janhorak.game.ne.basic.Score;
import net.janhorak.game.ne.engine.LocationService;
import net.janhorak.game.ne.entities.MoneyBag;
import net.janhorak.game.ne.entities.Thief;

public class MainApp extends Application {

    private final Score GAME_SCORE = new Score(0, 1);
    private Renderer renderer;
    double lastNanoTime = System.nanoTime();

    @Override
    public void start(Stage theStage) throws Exception {
        theStage.setTitle(GameEnvironment.TITLE);

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(GameEnvironment.WIDTH, GameEnvironment.HEIGHT);
        renderer = new Renderer(canvas);
        root.getChildren().add(canvas);

        Thief thief = new Thief();
        LocationService.setRandomPosition(thief);
        Set<GameSprite> moneybagList = new HashSet<>();

        for (int i = 0; i < 15; i++) {
            MoneyBag moneybag = new MoneyBag();
            LocationService.setRandomPosition(moneybag);
            moneybagList.add(moneybag);
        }

        List<String> input = new ArrayList<>();

        theScene.setOnKeyPressed((KeyEvent e) -> {
            String code = e.getCode().toString();
            if (!input.contains(code)) {
                input.add(code);
            } else if (input.contains("P")){
                input.remove(code);
            }
        });

        theScene.setOnKeyReleased((KeyEvent e) -> {
            String code = e.getCode().toString();
            if (!code.equals("P")){
                input.remove(code);
            }
        });

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;
                renderer.cleanSpriteList();

                // game logic
                if (input.contains("LEFT")) {
                    thief.addVelocity(-50, 0);
                }
                if (input.contains("RIGHT")) {
                    thief.addVelocity(50, 0);
                }
                if (input.contains("UP")) {
                    thief.addVelocity(0, -50);
                }
                if (input.contains("DOWN")) {
                    thief.addVelocity(0, 50);
                }

                thief.update(elapsedTime);
                renderer.updateSpriteList(thief);

                // collision detection
                Iterator<GameSprite> moneybagIter = moneybagList.iterator();
                while (moneybagIter.hasNext()) {
                    GameSprite moneybag = moneybagIter.next();
                    if (thief.intersects(moneybag)) {
                        moneybagIter.remove();
                        GAME_SCORE.addPoints();
                    }
                }
                renderer.updateSpriteList(moneybagList);

                // ----  Renderphase -----
                renderer.render();
                String pointsText = "Cash: $" + GAME_SCORE.get();
                renderer.renderText(pointsText, 100, 30);
            }
        };

        AnimationTimer gameMainLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (input.contains("P")) {
                    gameLoop.stop();
                    String pointsText = "Pause";
                    renderer.renderText(pointsText, 0, 50);
                    
                    lastNanoTime = System.nanoTime();
                } else {
                    gameLoop.start();
                }
            }
        };
        gameMainLoop.start();

        theStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
