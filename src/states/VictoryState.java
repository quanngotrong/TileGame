package states;

import game.Handler;
import gfx.Assets;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import settings.Settings;

import sounds.Sound;
import ui.UIImageButton;
import ui.UIManager;


public class VictoryState extends State{

    private UIManager uiManager;

    public VictoryState (Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(150, 430,200, 100, Assets.start,
                () -> {
                    handler.getMouseManager().setUiManager(null);
                    handler.getGame().gameState = new GameState(handler);
                    State.setState(handler.getGame().gameState);
                }));

        uiManager.addObject(new UIImageButton(450, 430,200, 100, Assets.exit, () -> Platform.exit()));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(Assets.victory, 0, 0, Settings.STAGE_WIDTH, Settings.STAGE_HEIGHT);
        uiManager.render(g);
    }
}
