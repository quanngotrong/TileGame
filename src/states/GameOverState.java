package states;

import game.Handler;
import gfx.Assets;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import settings.Settings;
import sounds.Sound;
import ui.UIImageButton;
import ui.UIManager;

public class GameOverState extends State{

    private UIManager uiManager;

    public GameOverState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        stateSound = new MediaPlayer(Sound.gameover);
        stateSound.play();

        uiManager.addObject(new UIImageButton(250, 370,200, 100, Assets.start,
                () -> {
                    handler.getMouseManager().setUiManager(null);
                    State.setState(handler.getGame().gameState);
                    stateSound.dispose();
                }));

        uiManager.addObject(new UIImageButton(530, 370,200, 100, Assets.exit, () -> Platform.exit()));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(Assets.gameover, 0, 0, Settings.STAGE_WIDTH, Settings.STAGE_HEIGHT);
        uiManager.render(g);
    }
}
