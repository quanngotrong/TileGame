package sounds;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Sound {

    public static Media main = SoundLoader.loadSound("res/sounds/main_theme.mp3");
    public static Media footstep = SoundLoader.loadSound("res/sounds/foot_step.wav");
    public static Media hurt = SoundLoader.loadSound("res/sounds/ouch.mp3");
    public static Media uchiha = SoundLoader.loadSound("res/sounds/uchiha_theme.mp3");
    public static Media gameover = SoundLoader.loadSound("res/sounds/gameover.mp3");
}
