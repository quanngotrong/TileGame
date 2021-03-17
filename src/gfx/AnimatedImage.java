package gfx;

import javafx.scene.image.Image;

/*
Nếu Character Assets được lưu dưới dạng nhiều file ảnh, gộp lại thành Array Image[]
thì dùng class này.
Ví dụ: ufo instanceof AnimatedImage, có array imageArray, thì set
ufo.frames = imageArray; ufo.durations = 0.1;

Để vẽ ra Canvas thì
double t = (l - System.nanoTime()) / 1000000000.0; (l trong AnimationTimer() class Game)
render() g.drawImage(ufo.getFrame(t), x, y);
 */

public class AnimatedImage {

    public Image[] frames;
    public double duration;
    public Image getFrame(double time) {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }
}
