package entities;

import entities.creatures.Enemy;
import entities.creatures.Player;
import game.Handler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected Handler handler;
    protected Image image;
    protected ImageView imageView;
    protected SnapshotParameters params;

    protected Rectangle bounds;
    public Circle zone;

    public Entity(Handler handler, Image image, float x, float y, int width, int height){
        this.handler = handler;
        this.image = image;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        bounds = new Rectangle(0, 0, width, height);
        zone = new Circle(0, 0, 100);
    }

    public abstract void tick();

    public abstract void render(GraphicsContext g);

    public boolean checkEntityCollision(float xOffset, float yOffset){
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset).getBoundsInLocal())){
                return true;
            }
        }
        return false;
    }


    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + bounds.getX() + xOffset),
                        (int) (y + bounds.getY() + yOffset), bounds.getWidth(), bounds.getHeight());
    }

    //Getters & Setters

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
