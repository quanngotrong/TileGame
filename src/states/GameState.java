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

import java.util.Set;


public class GameState extends State{


    private World world;

    //Scores


    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);

        stateSound = Sound.main;
        handler.getSoundManager().addSound(stateSound);
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
            for(MediaPlayer mediaP : handler.getSoundManager().getSoundList()){
                mediaP.pause();
            }

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

        Settings.SCORES = 0;

        //Sounds off
        handler.getSoundManager().soundOff();

        //Set victory state
        handler.getMouseManager().setUiManager(null);
        State.setState(new VictoryState(handler));
    }
    @Override
    public void render(GraphicsContext g) {

        world.render(g);
        g.setFill(Color.LAVENDER);
        g.fillRect(Settings.STAGE_WIDTH - 200, 0, 200, 30);
        g.setFill(Color.BLACK);
        g.fillText("Điểm số: " + Settings.SCORES + " || Đạn: "
                + handler.getWorld().getEntityManager().getPlayer().getAmmo(), Settings.STAGE_WIDTH - 190, 20);
    }

}
