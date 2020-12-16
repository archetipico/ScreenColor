import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelColor {

    private final BufferedImage capture;

    public PixelColor(BufferedImage capture) {
        this.capture = capture;
    }

    public Color getColor() {
        int w = capture.getWidth();
        int h = capture.getHeight();
        int ratio = w * h;

        long r = 0;
        long g = 0;
        long b = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color pixel = new Color(capture.getRGB(i, j));
                r += pixel.getRed();
                g += pixel.getGreen();
                b += pixel.getBlue();
            }
        }

        int red = (int) (r / ratio);
        int green = (int) (g / ratio);
        int blue = (int) (b / ratio);
        return new Color(red, green, blue);
    }
}
