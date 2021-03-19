package states;

import game.Handler;
import gfx.Assets;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import settings.Settings;

import ui.UIImageButton;
import ui.UIManager;

public class PauseState extends State{

    private UIManager uiManager;

    public PauseState (Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(300, 130,200, 100, Assets.start,
                () -> {
                    handler.getMouseManager().setUiManager(null);
                    State.setState(handler.getGame().gameState);
                }));


        uiManager.addObject(new UIImageButton(300, 260,200, 100, Assets.exit, Platform::exit));

        uiManager.addObject(new UIImageButton(300, 330,200, 200, Assets.mute_unmute,
                () -> {
                    if(!handler.getGame().isMute) {
                        handler.getSoundManager().muteAll();
                        handler.getGame().isMute = true;
                    } else {
                        handler.getSoundManager().unMuteAll();
                        handler.getGame().isMute = false;
                    }
                }));

    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(Assets.pause, 0, 0, Settings.STAGE_WIDTH, Settings.STAGE_HEIGHT);
        uiManager.render(g);
    }
}
