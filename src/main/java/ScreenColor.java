import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenColor {

    private final BufferedImage capture;
    private final int w;
    private final int h;
    private final int area;
    private final long[] rgb;

    public ScreenColor(BufferedImage capture) {
        this.capture = capture;
        this.w = capture.getWidth();
        this.h = capture.getHeight();
        this.area = this.w * this.h;
        this.rgb = new long[3];
    }

    public Color getColor() {
        getTotalRGB();

        int red = (int) (this.rgb[0] / this.area);
        int green = (int) (this.rgb[1] / this.area);
        int blue = (int) (this.rgb[2] / this.area);
        return new Color(red, green, blue);
    }

    private void getTotalRGB() {
        for (int i = 0; i < this.w; i++) {
            for (int j = 0; j < this.h; j++) {
                Color pixel = new Color(capture.getRGB(i, j));
                this.rgb[0] += pixel.getRed();
                this.rgb[1] += pixel.getGreen();
                this.rgb[2] += pixel.getBlue();
            }
        }
    }
}
