package worlds;

import entities.EntityManager;
import entities.creatures.Enemy;
import entities.creatures.Player;
import entities.creatures.Skeleton;
import entities.creatures.Slime;
import entities.statics.Tree;
import game.Handler;
import gfx.Assets;
import javafx.scene.canvas.GraphicsContext;
import settings.Settings;
import tiles.Tile;
import utils.Utils;



public class World {

    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;


    //Entities
    private EntityManager entityManager;

    private Handler handler;

    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, Assets.skeleton, 100, 100, 20));
        for(int i = 0; i < 6; i++){
            entityManager.addEntity(new Tree(handler, Assets.tree1, 175 + 100*i, 50));
            entityManager.addEntity(new Tree(handler, Assets.tree12, 175 + 100*i, 130));
        }

        for(int i = 0; i < 4; i++){
            entityManager.addEntity(new Tree(handler, Assets.tree12, 290 + 100*i, 800));
        }

        for(int i = 0; i < 10; i++){
            entityManager.addEntity(new Slime(handler, Assets.skeleton,
                    Math.random()*(700 - 100 + 1) + 100, Math.random()*(500 - 300 + 1) + 300, 15));
            entityManager.addEntity(new Skeleton(handler, Assets.skeleton,
                    Math.random()*(700 - 100 + 1) + 100, Math.random()*(500 - 300 + 1) + 300, 10));

        }


        loadWorld(path);

    }


    public void tick() {
        entityManager.tick();
    }

    public void render(GraphicsContext g){
        int xStart = (int) (Math.max(0, handler.getGameCamera().getxOffset() / Settings.TILE_WIDTH));
        int xEnd = (int) (Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Settings.TILE_WIDTH + 1));
        int yStart = (int) (Math.max(0, handler.getGameCamera().getyOffset() / Settings.TILE_HEIGHT));
        int yEnd = (int) (Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Settings.TILE_HEIGHT + 1));

        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                getTile(x, y).render(g, (int) (x* Settings.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                                        (int) (y* Settings.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        entityManager.render(g);
    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height){
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.dirtTile;
        return t;
    }

    public void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];

        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i++){
                tiles[i][j] = Utils.parseInt(tokens[i + j * width + 4]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
