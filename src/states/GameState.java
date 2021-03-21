package states;

import entities.Entity;
import entities.creatures.Enemy;
import entities.creatures.Player;
import game.Handler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import settings.Settings;
import sounds.Sound;
import worlds.World;


public class GameState extends State{


    private World world;

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
        if(Settings.SCORES < 100){
            return;
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

        //DRAW SCORES
        g.setFill(Color.LAVENDER);
        g.fillRect(Settings.STAGE_WIDTH - 200, 0, 200, 30);
        g.setFill(Color.BLACK);
        g.fillText("Điểm số: " + Settings.SCORES, Settings.STAGE_WIDTH - 190, 20);

        //DRAW HEALTH BAR
        double percent = (double) handler.getWorld().getEntityManager().getPlayer().getHealth() /
                (double) handler.getWorld().getEntityManager().getPlayer().getMaxHealth();
        g.setFill(Color.BURLYWOOD);
        g.fillRoundRect(200, 553, 400,7, 15, 15);
        g.setFill(Color.RED);
        g.fillRoundRect(200, 553, percent * 400,7, 15, 15);
        g.setFont(Font.font("Verdana", FontWeight.BOLD, 7));
        g.setFill(Color.WHITE);
        g.fillText(handler.getWorld().getEntityManager().getPlayer().getHealth() + " / "
                + handler.getWorld().getEntityManager().getPlayer().getMaxHealth(), 380, 559);

        //DRAW SPELL
        g.setFill(Color.BLACK);
        g.strokeOval(700, 520,40,40);
        g.setFill(Color.web("#e2fbff"));
        g.fillRoundRect(711, 555, 20,10, 10,10);
        g.setFill(Color.BLACK);
        g.fillText("Q", 718,562);
        g.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        int coolDown;
        if(Player.spellCoolDown - Player.spellTimer < 0){
            coolDown = 0;
        } else {
            coolDown = (int) Math.ceil((double) (Player.spellCoolDown - Player.spellTimer)/1000);
        }
        g.fillText(coolDown + "s", 707,546);
    }

}
