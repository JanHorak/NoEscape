package net.janhorak.game.ne;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import net.janhorak.game.ne.engine.loops.ApplicationLoop;
import net.janhorak.game.ne.engine.GameEnvironment;
import net.janhorak.game.ne.engine.Renderer;
import net.janhorak.game.ne.engine.services.ControlManager;

public class MainApp extends Application {

    private Renderer renderer;
    private ApplicationLoop applicationLoop;
    double lastNanoTime = System.nanoTime();
    private ControlManager controlManager = new ControlManager();

    @Override
    public void start(Stage theStage) throws Exception {
        theStage.setTitle(GameEnvironment.TITLE);

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(GameEnvironment.WIDTH, GameEnvironment.HEIGHT);
        renderer = new Renderer(canvas);
        root.getChildren().add(canvas);
        
        applicationLoop = new ApplicationLoop(controlManager, renderer, lastNanoTime);
        
        theScene.setOnKeyPressed((KeyEvent e) -> {
            controlManager.evalInputPressed(e);
        });

        theScene.setOnKeyReleased((KeyEvent e) -> {
            controlManager.evalInputReleased(e);
        });
        
        applicationLoop.start();

        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
