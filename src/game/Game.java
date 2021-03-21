package game;

import gfx.Assets;
import gfx.GameCamera;
import input.KeyManager;
import input.MouseManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;
import settings.Settings;
import sounds.SoundManager;
import states.GameState;
import states.MenuState;
import states.PauseState;
import states.State;

import java.io.File;

public class Game extends Application {

    private Canvas canvas;
    private GraphicsContext g;
    private StackPane root;
    private Scene scene;

    private int width = Settings.STAGE_WIDTH, height = Settings.STAGE_HEIGHT;

    //States
    public State gameState;
    public State menuState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    //Camera
    private GameCamera gameCamera;
    //Handler
    private Handler handler;

    //Sound
    private SoundManager soundManager;

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

        keyManager = new KeyManager(scene);
        keyManager.addListener();
        mouseManager = new MouseManager(scene);
        mouseManager.addListener();

        //Sounds
        soundManager = new SoundManager(handler);

        menuState = new MenuState(handler);
        State.setState(menuState);

        gameCamera = new GameCamera(handler,0,0);


        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                tick();
                render(g);
            }
        };
        gameLoop.start();
    }

    public void tick(){
        if(State.getState() != null)
            State.getState().tick();
    }

    public void render(GraphicsContext g){
        //refresh screen
        g.clearRect(0, 0, width, height);

        //start drawing
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

    public MouseManager getMouseManager(){
        return mouseManager;
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

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
