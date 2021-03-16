package entities.creatures;

import entities.Entity;
import game.Handler;
import gfx.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import settings.Settings;

public class Enemy extends Creature{

    int count = 9;
    int columns = 9;
    int offsetX = 0;
    int offsetY = 128;
    int width = 64;
    int height = 64;

    SpriteAnimation animation;

    public Enemy(Handler handler, Pane pane, Image image, float x, float y){
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
        getMove();
        move();
    }

    private boolean checkPlayerZone() {
        boolean collisionDetected = false;
        Shape intersect = Shape.intersect(this.zone, handler.getWorld().getEntityManager().getPlayer().zone);
        if (intersect.getBoundsInLocal().getWidth() != -1) {
            collisionDetected = true;
        }
        return collisionDetected;
    }


    public void getMove(){
        xMove = 0;
        yMove = 0;

        if(checkPlayerZone()){
            if(y > handler.getWorld().getEntityManager().getPlayer().getY() + 1){ //up
                yMove = -speed + 2;
                animation.setOffsetY(0);
            }
            if(y < handler.getWorld().getEntityManager().getPlayer().getY() - 1){ //down
                yMove = speed - 2;
                animation.setOffsetY(128);
            }
            if(x < handler.getWorld().getEntityManager().getPlayer().getX() - 1){ //right
                xMove = speed - 2;
                animation.setOffsetY(192);
            }
            if(x > handler.getWorld().getEntityManager().getPlayer().getX() + 1){ //left
                xMove = -speed + 2;
                animation.setOffsetY(64);
            }
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
