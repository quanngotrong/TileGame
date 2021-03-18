package gfx;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Assets {

    private static final int width = 32, height = 32;
    public static Image tree1, tree12, dirt, grass, stone, skeleton, player;
//    public static Image[] btn_start;
    public static Image background;
    public static Image start, exit;
    public static Image[] slime_up, slime_down, slime_left, slime_right;
    public static MediaPlayer mainSound, hurtSound, foot_step;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("res/textures/sheet.png"));
        tree1 = ImageLoader.loadImage("res/textures/tree.png");
        tree12 = ImageLoader.loadImage("res/textures/tree/Tree(12).png");
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        stone = sheet.crop(width * 3, 0, width, height);

        skeleton = ImageLoader.loadImage("res/textures/skeleton.png");
        player = ImageLoader.loadImage("res/textures/minotaur.png");

//        btn_start = new Image[2];
//        btn_start[0] = sheet.crop(width*6, height*4, width*2, height);
//        btn_start[1] = sheet.crop(width*6, height*5, width*2, height);

        start = ImageLoader.loadImage("res/textures/start_button.png");
        exit = ImageLoader.loadImage("res/textures/exit_button.png");
        background = ImageLoader.loadImage("res/textures/background.jpg");

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

        mainSound = new MediaPlayer(new Media(new File("res/sounds/main_theme.mp3").toURI().toString()));
        hurtSound = new MediaPlayer(new Media(new File("res/sounds/hurt.wav").toURI().toString()));
        foot_step = new MediaPlayer(new Media(new File("res/sounds/foot_step.wav").toURI().toString()));

    }
}
