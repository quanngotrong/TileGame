package entities.creatures;

import entities.Entity;
import game.Handler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import settings.Settings;
import sounds.Sound;

public class Bullet extends Creature{

    private boolean isFired = false;
    private int xLong = 0, yLong = 0;

    public Bullet(Handler handler, Image image, double x, double y, int damage, int direction) {
        super(handler, image, x, y, 20, 21, damage);
        this.direction = direction;

        setSpeed(50);

    }

    @Override
    public void move() {
        x += xMove/2;
        xLong += xMove/2;

        y += yMove/2;
        yLong += yMove/2;
    }

    @Override
    public void tick() {
        fire();
        move();
        checkHit();
    }

    public void fire(){
        if(!isFired){
            if(direction == 1){ //up
                yMove = -speed;
            }

            if(direction == 2){ //down
                yMove = speed;
            }

            if(direction == 3){ //left
                xMove = -speed;
            }

            if(direction == 4){ //right
                xMove = speed;
            }
            isFired = true;
        }
    }

    public void checkHit(){
        //Enemy hit
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(handler.getWorld().getEntityManager().getPlayer()))
                continue;
            if(e.getCollisionBounds(0, 0).intersects(getCollisionBounds(0,0).getBoundsInLocal())){
                e.takeDamage(damage);
                die();
                if(!Settings.IS_MUTE){
                    if(Sound.punch.getStatus() == MediaPlayer.Status.PLAYING)
                        Sound.punch.stop();
                    Sound.punch.play();
                }
            }
        }

        //Tile hit

        //Bullet move
        if(Math.abs(xLong) > 200 || Math.abs(yLong) > 200)
            die();
    }

    @Override
    public void render(GraphicsContext g) {
        g.drawImage(image, (int)(x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()));
    }

    @Override
    public void die() {
        active = false;
        System.out.println("Chết :D");
    }
}
