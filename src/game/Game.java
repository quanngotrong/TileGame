package game;

import gfx.Assets;
import gfx.GameCamera;
import input.KeyManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import settings.Settings;
import states.GameState;
import states.MenuState;
import states.State;

public class Game extends Application {

    Canvas canvas;
    GraphicsContext g;
    StackPane root;
    Scene scene;

    private int width = Settings.STAGE_WIDTH, height = Settings.STAGE_HEIGHT;

    //States
    private State gameState;
    private State menuState;
    //Input
    private KeyManager keyManager;
    //Camera
    private GameCamera gameCamera;
    //Handler
    private Handler handler;

    public void init(){
        Assets.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        canvas = new Canvas(width, height);
        root = new StackPane(canvas);

        g = canvas.getGraphicsContext2D();

        scene = new Scene(root, width, height);

        stage.setScene(scene);
        stage.setTitle("Tile Game");
        stage.setResizable(false);
        stage.show();

        handler = new Handler(this);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(gameState);

        keyManager = new KeyManager(scene);
        keyManager.addListener();

        gameCamera = new GameCamera(handler,0,0);


        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                tick();
                render();
            }
        };
        gameLoop.start();
    }

    public void tick(){
        if(State.getState() != null)
            State.getState().tick();
    }

    public void render(){
        if(State.getState() != null)
            State.getState().render(g);
    }


    public Scene getScene() {
        return scene;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public GraphicsContext getGraphicsContext(){
        return g;
    }

    public Game getGame(){
        return this;
    }

    public StackPane getPane(){
        return root;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
