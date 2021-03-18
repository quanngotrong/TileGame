package entities.creatures;

import game.Handler;
import gfx.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class Skeleton extends Enemy{

    int count = 9;
    int columns = 9;
    int offsetX = 0;
    int offsetY = 128;
    int width = 64;
    int height = 64;

    SpriteAnimation animation;
    Image enemy;

    public Skeleton(Handler handler,  Image image, float x, float y, int damage){
        super(handler, image, x, y, damage);

        setWidth(64);
        setWidth(64);
        setSpeed(2);

        imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(imageView, Duration.millis(1000),count,columns,offsetX,offsetY,width,height);

        bounds.setX(24);
        bounds.setY(38);
        bounds.setWidth(16);
        bounds.setHeight(24);

    }

    @Override
    public void tick() {
       super.tick();
       setAnimation();
    }

    private void setAnimation(){
        if(checkPlayerZone()){
            if(direction == 1){ //up
                animation.setOffsetY(0);
            }
            if(direction == 2){ //down
                animation.setOffsetY(128);

            }
            if(direction == 3){ //right
                animation.setOffsetY(192);

            }
            if(direction == 4){ //left
                animation.setOffsetY(64);
            }
        }
    }


    @Override
    public void render(GraphicsContext g) {
        if (xMove != 0 || yMove != 0)
            animation.play();
        else animation.stop();

        enemy = imageView.snapshot(params, null);
        g.drawImage(enemy, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()));

        //draw health bar
        g.setFill(Color.BLACK);
        g.strokeRect((int) (x - handler.getGameCamera().getxOffset()) + 11,
                (int) (y - handler.getGameCamera().getyOffset()), 40, 5);
        g.setFill(Color.GREEN);
        g.fillRect((int) (x - handler.getGameCamera().getxOffset()) + 11,
                (int) (y - handler.getGameCamera().getyOffset()), 40 * ((float) (health) / (float) maxHealth), 4);

    }
}
