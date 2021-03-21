package states;

import game.Handler;
import gfx.Assets;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import settings.Settings;

import sounds.Sound;
import ui.UIImageButton;
import ui.UIManager;

public class MenuState extends State{

    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        stateSound = Sound.uchiha;
        handler.getSoundManager().addSound(stateSound);

        stateSound.play();

        uiManager.addObject(new UIImageButton(300, 130,200, 100, Assets.start,
                () -> {
                    handler.getMouseManager().setUiManager(null);
                    handler.getGame().gameState = new GameState(handler);
                    State.setState(handler.getGame().gameState);
                    stateSound.dispose();
                }));

        uiManager.addObject(new UIImageButton(300, 260,200, 100, Assets.exit, Platform::exit));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(Assets.background, 0, 0, Settings.STAGE_WIDTH, Settings.STAGE_HEIGHT);
        uiManager.render(g);
    }
}
