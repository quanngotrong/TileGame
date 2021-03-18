package entities.creatures;

import entities.Entity;
import game.Handler;
import gfx.Assets;
import gfx.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import settings.Settings;
import states.GameState;
import states.MenuState;
import states.State;

public class Player extends Creature{

    int count = 9;
    int columns = 9;
    int offsetX = 0;
    int offsetY = 128;
    int width = 64;
    int height = 64;

    SpriteAnimation animation;
    Image player;


    //Attack Timer
    private long lastAttackTimer, attackCoolDown = 500, attackTimer = attackCoolDown;

    public Player(Handler handler, Image image, float x, float y, int damage){
        super(handler, image, x, y, Settings.DEFAULT_CREATURE_WIDTH, Settings.DEFAULT_CREATURE_HEIGHT, damage);

        setSpeed(5);

        arSize = 25;

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
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        //Attack
        checkAttacks();
        stepSound();

    }

    private void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCoolDown){
            return;
        }

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        ar.setWidth(arSize);
        ar.setHeight(arSize);

        if(handler.getKeyManager().isSpace() && direction == 1){
            ar.setX(cb.getX() + cb.getWidth()/2 - arSize/2);
            ar.setY(cb.getY() - arSize);
        } else if(handler.getKeyManager().isSpace() && direction == 2){
            ar.setX(cb.getX() + cb.getWidth()/2 - arSize/2);
            ar.setY(cb.getY() + cb.getHeight());

        } else if(handler.getKeyManager().isSpace() && direction == 3){
            ar.setX(cb.getX() - arSize);
            ar.setY(cb.getY() + cb.getHeight()/2 - arSize/2);

        } else if(handler.getKeyManager().isSpace() && direction == 4){
            ar.setX(cb.getX() + cb.getWidth());
            ar.setY(cb.getY() + cb.getHeight()/2 - arSize/2);

        } else {
            return;
        }

        handler.getGraphicsContext().fillRect(ar.getX(), ar.getY(), arSize, arSize);

        attackTimer = 0;

        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0, 0).intersects(ar.getBoundsInLocal())){
                e.takeDamage(damage);
            }
        }
    }

    public void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().isMoveUp()){
            direction = 1;
            yMove = -speed;
            animation.setOffsetY(0);

        }
        if(handler.getKeyManager().isMoveDown()){
            direction = 2;
            yMove = speed;
            animation.setOffsetY(128);

        }
        if(handler.getKeyManager().isMoveLeft()){
            direction = 3;
            xMove = -speed;
            animation.setOffsetY(64);

        }
        if(handler.getKeyManager().isMoveRight()){
            direction = 4;
            xMove = speed;
            animation.setOffsetY(192);

        }
    }

    public void stepSound(){
        if(handler.getKeyManager().isMoveUp() || handler.getKeyManager().isMoveDown()
            ||handler.getKeyManager().isMoveLeft() || handler.getKeyManager().isMoveRight()){
            Assets.foot_step.setCycleCount(MediaPlayer.INDEFINITE);
            Assets.foot_step.play();
        } else {
            Assets.foot_step.stop();
        }
    }

    @Override
    public void die() {
        System.out.println("Ngu vcl :D");
        handler.getGame().gameState = new GameState(handler);
        State.setState(new MenuState(handler));
    }

    @Override
    public void render(GraphicsContext g) {
        //draw player
        if(xMove != 0 || yMove != 0)
            animation.play();
        else animation.stop();
        player = imageView.snapshot(params, null);
        g.drawImage(player, (int)(x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()));

        //draw health bar
        g.setFill(Color.BLACK);
        g.strokeRect((int)(x - handler.getGameCamera().getxOffset()) + 11,
                (int) (y - handler.getGameCamera().getyOffset()),40,5);
        g.setFill(Color.GREEN);
        g.fillRect((int)(x - handler.getGameCamera().getxOffset()) + 11,
                (int) (y - handler.getGameCamera().getyOffset()), 40 * ((float) (health) /(float) maxHealth), 4);
    }
}
