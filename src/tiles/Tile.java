package tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import settings.Settings;


public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile rockTile = new RockTile(2);


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
        g.drawImage(image, x, y, Settings.TILE_WIDTH, Settings.TILE_HEIGHT);
    }

    public boolean isSolid(){
        return false;
    }

    public int getId(){
        return id;
    }

}
