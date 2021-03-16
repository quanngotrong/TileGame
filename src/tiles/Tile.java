package tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile rockTile = new RockTile(2);

    public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;


    protected Image image;
    protected final int id;

    public Tile(Image image, int id){
        this.image = image;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(GraphicsContext g, int x, int y){
        g.drawImage(image, x, y, TILE_WIDTH, TILE_WIDTH);
    }

    public boolean isSolid(){
        return false;
    }

    public int getId(){
        return id;
    }

}
