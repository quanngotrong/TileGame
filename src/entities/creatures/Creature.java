package entities.creatures;

import entities.Entity;
import game.Handler;
import javafx.scene.image.Image;
import settings.Settings;

public abstract class Creature extends Entity {

    protected float speed;
    protected float xMove, yMove;
    protected int direction = 0; //1-up, 2-down, 3-left, 4-right
    protected int damage;

    public Creature(Handler handler, Image image, float x, float y, int width, int height, int damage){
        super(handler, image, x, y, width, height);
        this.damage = damage;
        speed = Settings.DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move(){
        if(!checkEntityCollision(xMove, 0f))
            moveX();
        if(!checkEntityCollision(0f, yMove))
            moveY();
    }

    public void moveX(){
        if(xMove > 0){ //Moving right
            int tx = (int) (x + xMove + bounds.getX() + bounds.getWidth()) / Settings.TILE_WIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.getY()) / Settings.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.getY() + bounds.getHeight()) / Settings.TILE_HEIGHT)){
                x += xMove/2;
            } else {
                x = (int) (tx * Settings.TILE_WIDTH - bounds.getX() - bounds.getWidth() - 1);
            }
        } else if (xMove < 0){ //Moving left
            int tx = (int) (x + xMove + bounds.getX()) / Settings.TILE_WIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.getY()) / Settings.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.getY() + bounds.getHeight()) / Settings.TILE_HEIGHT)) {
                x += xMove/2;
            } else {
                x = (int) (tx* Settings.TILE_WIDTH + Settings.TILE_WIDTH - bounds.getX());
            }
        }
    }

    public void moveY(){
        if(yMove < 0){ //Moving up
            int ty = (int) (y + yMove + bounds.getY()) / Settings.TILE_HEIGHT;

            if(!collisionWithTile((int) (x + bounds.getX()) / Settings.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.getX() + bounds.getWidth()) / Settings.TILE_WIDTH, ty)){
                y += yMove/2;
            } else {
                y = (int) (ty * Settings.TILE_HEIGHT + Settings.TILE_HEIGHT - bounds.getY());
            }
        } else if (yMove > 0){ //Moving down
            int ty = (int) (y + yMove + bounds.getY() + bounds.getHeight()) / Settings.TILE_HEIGHT;

            if(!collisionWithTile((int) (x + bounds.getX()) / Settings.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.getX() + bounds.getWidth()) / Settings.TILE_WIDTH, ty)){
                y += yMove/2;
            } else {
                y= (int) (ty * Settings.TILE_HEIGHT - bounds.getY() - bounds.getHeight() - 1);
            }
        }
    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }


    //Getters & Setters

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }


}
