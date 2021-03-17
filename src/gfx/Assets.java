package gfx;

import javafx.scene.image.Image;

public class Assets {

    private static final int width = 32, height = 32;
    public static Image tree1, tree12, dirt, grass, stone, skeleton, player;
    public static Image[] btn_start;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/sheet.png"));
        tree1 = ImageLoader.loadImage("res/textures/tree.png");
        tree12 = ImageLoader.loadImage("res/textures/tree/Tree(12).png");
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        stone = sheet.crop(width * 3, 0, width, height);

        skeleton = ImageLoader.loadImage("res/textures/skeleton.png");
        player = ImageLoader.loadImage("res/textures/minotaur.png");

        btn_start = new Image[2];
        btn_start[0] = sheet.crop(width*6, height*4, width*2, height);
        btn_start[1] = sheet.crop(width*6, height*5, width*2, height);

    }
}
