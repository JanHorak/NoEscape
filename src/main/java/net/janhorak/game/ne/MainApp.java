package net.janhorak.game.ne;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import net.janhorak.game.ne.engine.GameEnvironment;
import net.janhorak.game.ne.engine.GameState;
import net.janhorak.game.ne.engine.Renderer;
import net.janhorak.game.ne.engine.loops.ManagementLoop;
import net.janhorak.game.ne.engine.services.ControlManager;
import org.apache.log4j.BasicConfigurator;

public class MainApp extends Application {

    private Renderer renderer;
    private ManagementLoop mgnLoop;
    double lastNanoTime = System.nanoTime();
    private ControlManager controlManager;
    private GameState state = GameState.STARTUP;
    
    
    @Override
    public void start(Stage theStage) throws Exception {
        controlManager = new ControlManager();
        theStage.setTitle(GameEnvironment.TITLE);

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(GameEnvironment.WIDTH, GameEnvironment.HEIGHT);
        renderer = new Renderer(canvas);
        root.getChildren().add(canvas);
        
        theScene.setOnKeyPressed((KeyEvent e) -> {
            controlManager.evalInputPressed(e);
        });
        
        theScene.setOnKeyReleased((KeyEvent e) -> {
            controlManager.evalInputReleased(e);
        });
        
        mgnLoop = new ManagementLoop(state, controlManager, renderer, lastNanoTime);
        mgnLoop.start();
        ManagementLoop.tryToChangeState(GameState.MENU);
        theStage.show();
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        launch(args);
    }

}
