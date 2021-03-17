package ui;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class UIImageButton extends UIObject{

    private Image[] images;
    private ClickListener clicker;

    public UIImageButton(float x, float y, int width, int height, Image[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(GraphicsContext g) {
        if(hovering)
            g.drawImage(images[1], (int) x, (int) y, width, height);
        else
            g.drawImage(images[0], (int) x, (int) y, width, height);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
