package gfx;

import javafx.scene.image.Image;

public class Assets {

    private static final int width = 32, height = 32;
    public static Image tree1, tree12, dirt, grass, stone, skeleton, player;
//    public static Image[] btn_start;
    public static Image background, gameover, pause;
    public static Image start, exit;
    public static Image[] slime_up, slime_down, slime_left, slime_right;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/sheet.png"));
        tree1 = ImageLoader.loadImage("res/textures/tree.png");
        tree12 = ImageLoader.loadImage("res/textures/tree/Tree(12).png");
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        stone = sheet.crop(width * 3, 0, width, height);

        skeleton = ImageLoader.loadImage("res/textures/skeleton.png");
        player = ImageLoader.loadImage("res/textures/minotaur.png");


        start = ImageLoader.loadImage("res/textures/start_button.png");
        exit = ImageLoader.loadImage("res/textures/exit_button.png");
        background = ImageLoader.loadImage("res/textures/background.jpg");
        gameover = ImageLoader.loadImage("res/textures/gameover.jpg");
        pause = ImageLoader.loadImage("res/textures/pause.jpg");

        slime_up = new Image[4];
        for(int i = 0; i < 4; i++)
            slime_up[i] = ImageLoader.loadImage("res/textures/slime/SlimeUp_" + i + ".png");

        slime_down = new Image[4];
        for(int i = 0; i < 4; i++)
            slime_down[i] = ImageLoader.loadImage("res/textures/slime/SlimeDown_" + i + ".png");

        slime_left = new Image[4];
        for(int i = 0; i < 4; i++)
            slime_left[i] = ImageLoader.loadImage("res/textures/slime/SlimeLeft_" + i + ".png");
        slime_right = new Image[4];
        for(int i = 0; i < 4; i++)
            slime_right[i] = ImageLoader.loadImage("res/textures/slime/SlimeRight_" + i + ".png");


    }
}
