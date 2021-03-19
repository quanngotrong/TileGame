package states;

import entities.Entity;
import entities.creatures.Enemy;
import game.Handler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import settings.Settings;
import sounds.Sound;
import worlds.World;


public class GameState extends State{


    private World world;


    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);

        stateSound = new MediaPlayer(Sound.main);
        stateSound.setCycleCount(MediaPlayer.INDEFINITE);
    }

    @Override
    public void tick() {
        stateSound.play();
        world.tick();
        checkPause();
        checkWin();
    }

    public void checkPause(){
        if(handler.getKeyManager().isPause()){

            //Sounds off
            stateSound.pause();
            handler.getWorld().getEntityManager().getPlayer().getFootStep().stop();

            //Set pause state
            State.setState(new PauseState(handler));
        }
    }

    public void checkWin(){
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e instanceof Enemy){
                return;
            }
        }

        //Sounds off
        stateSound.pause();
        handler.getWorld().getEntityManager().getPlayer().getFootStep().stop();

        //Set victory state
        State.setState(new VictoryState(handler));
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
