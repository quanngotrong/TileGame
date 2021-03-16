package states;

import game.Game;
import game.Handler;
import javafx.scene.layout.Pane;

public class MenuState extends State{


    private Pane pane;

    public MenuState(Handler handler, Pane pane){
        super(handler);
        this.pane = pane;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render() {

    }
}
