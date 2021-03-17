package entities;

import entities.creatures.Player;
import game.Handler;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager{

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Iterator i;
    private Comparator<Entity> renderSort = (a,b) -> Float.compare(a.getY() + a.getHeight() , b.getY() + b.getHeight());
    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        addEntity(player);
    }

    public void tick(){
        i=entities.iterator();
        while(i.hasNext()){
            Entity e = (Entity) i.next();
            e.tick();

            if(!e.isActive())
                i.remove();
        }

        entities.sort(renderSort);
    }

    public void render(GraphicsContext g){
        for(Entity e: entities){
            e.render(g);
        }
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    //Getters & Setters
    public Player getPlayer() {
        return player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

}
