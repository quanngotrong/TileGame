package entities.creatures;

import game.Handler;
import gfx.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import settings.Settings;

public class Player extends Creature{

    int count = 9;
    int columns = 9;
    int offsetX = 0;
    int offsetY = 128;
    int width = 64;
    int height = 64;

    SpriteAnimation animation;

    public Player(Handler handler, Pane pane, Image image, float x, float y){
        super(handler, pane, image, x, y, Settings.DEFAULT_CREATURE_WIDTH, Settings.DEFAULT_CREATURE_HEIGHT);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(imageView, Duration.millis(1000),count,columns,offsetX,offsetY,width,height);

        bounds.setX(24);
        bounds.setY(38);
        bounds.setWidth(16);
        bounds.setHeight(24);

        zone.setCenterX(-70);
        zone.setCenterY(-53);
    }

    @Override
    public void tick() {
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    public void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().isMoveUp()){
            yMove = -speed;
            animation.setOffsetY(0);
        }
        if(handler.getKeyManager().isMoveDown()){
            yMove = speed;
            animation.setOffsetY(128);
        }
        if(handler.getKeyManager().isMoveLeft()){
            xMove = -speed;
            animation.setOffsetY(64);
        }
        if(handler.getKeyManager().isMoveRight()){
            xMove = speed;
            animation.setOffsetY(192);
        }
    }

    @Override
    public void render() {
        if(xMove != 0 || yMove != 0)
            animation.play();
        else animation.stop();

        imageView.relocate((int)(x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
        zone.relocate((int)(x + zone.getCenterX() - handler.getGameCamera().getxOffset()), (int) (y + zone.getCenterY() - handler.getGameCamera().getyOffset()));
    }
}
