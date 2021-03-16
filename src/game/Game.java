package game;

import gfx.Assets;
import gfx.GameCamera;
import input.KeyManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import states.GameState;
import states.MenuState;
import states.State;

public class Game extends Application {

    Pane gameStatePane;
    Pane menuStatePane;
    Canvas worldBase;

    Scene scene;
    private int width = 600, height = 600;

    private ImageView imageView;

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
        worldBase = new Canvas(width, height);
        gameStatePane = new Pane();
        menuStatePane = new Pane();

        Group root = new Group();
        root.getChildren().addAll(worldBase, gameStatePane);

        scene = new Scene(root, width, height);

        stage.setScene(scene);
        stage.setTitle("Tile Game");
        stage.show();

        handler = new Handler(this);

        gameState = new GameState(handler, gameStatePane);
        menuState = new MenuState(handler, menuStatePane);
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
            State.getState().render();
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

    public Canvas getWorldBase() {
        return worldBase;
    }

    public Pane getGameStatePane(){
        return gameStatePane;
    }

    public Game getGame(){
        return this;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
