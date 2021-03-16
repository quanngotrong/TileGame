package entities.statics;

import game.Handler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import settings.Settings;

public class Tree extends StaticEntity{

    public Tree(Handler handler, Pane pane, Image image, float x, float y){
        super(handler, pane, image, x, y, Settings.TILE_WIDTH, (int) (Settings.TILE_HEIGHT*2*0.7));

        bounds.setX(36);
        bounds.setY(65);
        bounds.setWidth(10);
        bounds.setHeight(25);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render() {
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
        imageView.relocate((int)(x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));

    }
}
