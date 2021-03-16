package gfx;

import javafx.scene.image.Image;

public class Assets {

    private static final int width = 32, height = 32;
    public static Image tree1, tree12, dirt, grass, stone, player;
    public static Image[] player_up, player_down, player_left, player_right;
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/sheet.png"));
        tree1 = ImageLoader.loadImage("res/textures/tree.png");
        tree12 = ImageLoader.loadImage("res/textures/tree/Tree(12).png");
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        stone = sheet.crop(width * 3, 0, width, height);

        player = ImageLoader.loadImage("res/textures/skeleton.png");
//        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("res/textures/skeleton.png"));
//        player_up = new Image[10];
//        for(int i = 0; i < 9; i++){
//            player_up[i] = playerSheet.crop(0, 0, 64, 64);
//        }
    }
}
