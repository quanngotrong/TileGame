package states;

import game.Handler;
import javafx.scene.canvas.GraphicsContext;
import worlds.World;

public class GameState extends State{


    private World world;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();

    }
    @Override
    public void render(GraphicsContext g) {
        world.render(g);
    }
}
