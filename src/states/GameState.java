package states;

import game.Handler;
import javafx.scene.layout.Pane;
import worlds.World;

public class GameState extends State{

    private Pane pane;

    private World world;

    public GameState(Handler handler, Pane pane){
        super(handler);
        this.pane = pane;
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);

    }

    @Override
    public void tick() {
        world.tick();

    }
    @Override
    public void render() {
        world.render(handler.getWorldBase().getGraphicsContext2D());

    }
}
