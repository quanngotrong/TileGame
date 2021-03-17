package entities.creatures;

import game.Handler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import settings.Settings;

public class Enemy extends Creature{


    //Attack Timer
    private long lastAttackTimer, attackCoolDown = 1000, attackTimer = attackCoolDown;

    public Enemy(Handler handler,  Image image, float x, float y, int damage){
        super(handler, image, x, y, Settings.DEFAULT_CREATURE_WIDTH, Settings.DEFAULT_CREATURE_HEIGHT, damage);
    }

    @Override
    public void tick() {
        //Movements
        getMove();
        move();

        //Attack
        checkAttacks();
    }

    private void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCoolDown){
            return;
        }

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.setWidth(arSize);
        ar.setHeight(arSize);

        if(direction == 1){
            ar.setX(cb.getX() + cb.getWidth()/2 - arSize/2);
            ar.setY(cb.getY() - arSize);
        } else if(direction == 2){
            ar.setX(cb.getX() + cb.getWidth()/2 - arSize/2);
            ar.setY(cb.getY() + cb.getHeight());
        } else if(direction == 3){
            ar.setX(cb.getX() - arSize);
            ar.setY(cb.getY() + cb.getHeight()/2 - arSize/2);
        } else if(direction == 4){
            ar.setX(cb.getX() + cb.getWidth());
            ar.setY(cb.getY() + cb.getHeight()/2 - arSize/2);
        } else {
            return;
        }

        attackTimer = 0;

        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(ar.getBoundsInLocal())){
            handler.getWorld().getEntityManager().getPlayer().takeDamage(damage);
            return;
        }

    }

    protected boolean checkPlayerZone() {
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
                direction = 1;
                yMove = -speed;
            }
            if(y < handler.getWorld().getEntityManager().getPlayer().getY() - 1){ //down
                direction = 2;
                yMove = speed;
            }
            if(x < handler.getWorld().getEntityManager().getPlayer().getX() - 1){ //right
                direction = 3;
                xMove = speed;
            }
            if(x > handler.getWorld().getEntityManager().getPlayer().getX() + 1){ //left
                direction = 4;
                xMove = -speed;
            } else {
                return;
            }
        }
    }


    @Override
    public void render(GraphicsContext g) {
        zone.relocate((int)(x + zone.getCenterX() - handler.getGameCamera().getxOffset()), (int) (y + zone.getCenterY() - handler.getGameCamera().getyOffset()));


    }

    @Override
    public void die() {
        System.out.println("xin lũiiii mà :(");
    }
}
