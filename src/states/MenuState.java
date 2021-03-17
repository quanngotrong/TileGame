package states;

import game.Handler;
import gfx.Assets;
import javafx.scene.canvas.GraphicsContext;
import ui.UIImageButton;
import ui.UIManager;

public class MenuState extends State{

    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(230, 150,128, 64, Assets.btn_start,
                () -> {
//                    handler.getMouseManager().setUiManager(null);
                    State.setState(handler.getGame().gameState);
                }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(GraphicsContext g) {
        uiManager.render(g);
    }
}
