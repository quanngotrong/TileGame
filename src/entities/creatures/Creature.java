package entities.creatures;

import entities.Entity;
import game.Handler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import tiles.Tile;

public abstract class Creature extends Entity {
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, Pane pane, Image image, float x, float y, int width, int height){
        super(handler, pane, image, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
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
            int tx = (int) (x + xMove + bounds.getX() + bounds.getWidth()) / Tile.TILE_WIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.getY()) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.getY() + bounds.getHeight()) / Tile.TILE_HEIGHT)){
                x += xMove;
            } else {
                x = (int) (tx * Tile.TILE_WIDTH - bounds.getX() - bounds.getWidth() - 1);
            }
        } else if (xMove < 0){ //Moving left
            int tx = (int) (x + xMove + bounds.getX()) / Tile.TILE_WIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.getY()) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.getY() + bounds.getHeight()) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = (int) (tx* Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.getX());
            }
        }
    }

    public void moveY(){
        if(yMove < 0){ //Moving up
            int ty = (int) (y + yMove + bounds.getY()) / Tile.TILE_HEIGHT;

            if(!collisionWithTile((int) (x + bounds.getX()) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.getX() + bounds.getWidth()) / Tile.TILE_WIDTH, ty)){
                y += yMove;
            } else {
                y = (int) (ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.getY());
            }
        } else if (yMove > 0){ //Moving down
            int ty = (int) (y + yMove + bounds.getY() + bounds.getHeight()) / Tile.TILE_HEIGHT;

            if(!collisionWithTile((int) (x + bounds.getX()) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.getX() + bounds.getWidth()) / Tile.TILE_WIDTH, ty)){
                y += yMove;
            } else {
                y= (int) (ty * Tile.TILE_HEIGHT - bounds.getY() - bounds.getHeight() - 1);
            }
        }
    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }


    //Getters & Setters

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

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
