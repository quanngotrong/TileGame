package entities.creatures;

import game.Handler;
import gfx.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Player extends Creature{

    int count = 9;
    int columns = 9;
    int offsetX = 0;
    int offsetY = 128;
    int width = 64;
    int height = 64;

    SpriteAnimation animation;

    public Player(Handler handler, Pane pane, Image image, float x, float y){
        super(handler, pane, image, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
        imageView.setFitWidth(DEFAULT_CREATURE_WIDTH);
        imageView.setFitHeight(DEFAULT_CREATURE_HEIGHT);
        imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(imageView, Duration.millis(1000),count,columns,offsetX,offsetY,width,height);

        bounds.setX(24);
        bounds.setY(38);
        bounds.setWidth(16);
        bounds.setHeight(24);
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
        if(handler.getKeyManager().isMoveUp() || handler.getKeyManager().isMoveDown()
                || handler.getKeyManager().isMoveRight() || handler.getKeyManager().isMoveLeft())
            animation.play();
        else animation.stop();

        imageView.relocate((int)(x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
    }
}
