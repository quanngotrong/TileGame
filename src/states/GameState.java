package states;

import game.Handler;
import gfx.Assets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import settings.Settings;
import worlds.World;


public class GameState extends State{


    private World world;


    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
        Assets.mainSound.setCycleCount(MediaPlayer.INDEFINITE);

    }

    @Override
    public void tick() {
        world.tick();
        Assets.mainSound.play();
    }
    @Override
    public void render(GraphicsContext g) {

        world.render(g);
        g.setFill(Color.LAVENDER);
        g.fillRect(Settings.STAGE_WIDTH - 200, 0, 200, 30);
        g.setFill(Color.BLACK);
        g.fillText("Thời gian hoặc điểm số gì đó", Settings.STAGE_WIDTH - 190, 20);

    }
}
