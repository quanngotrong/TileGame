package game;

import gfx.GameCamera;
import input.KeyManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import worlds.World;

public class Handler {

    private Game game;
    private World world;

    public Handler(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public Canvas getWorldBase(){
        return game.getWorldBase();
    }

    public Pane getGameStatePane(){
        return game.getGameStatePane();
    }

    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }
}
