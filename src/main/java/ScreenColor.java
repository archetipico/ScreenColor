import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ScreenColor {

    private final BufferedImage capture;
    private final int w;
    private final int h;
    private final int area;
    private final long[] rgb;
    private Color color;

    public ScreenColor(BufferedImage capture) {
        this.capture = capture;
        this.w = capture.getWidth();
        this.h = capture.getHeight();
        this.area = this.w * this.h;
        this.rgb = new long[3];
        this.color = new Color(0, 0, 0);
    }

    public Color getColor() {
        getTotalRGB();

        int red = (int) (this.rgb[0] / this.area);
        int green = (int) (this.rgb[1] / this.area);
        int blue = (int) (this.rgb[2] / this.area);
        this.color = new Color(red, green, blue);
        return this.color;
    }

    public ArrayList<Color> getGradient(Color from) {
        ArrayList<Color> gradient = new ArrayList<>();
        gradient.add(from);

        int redDistance = Math.abs(from.getRed() - this.color.getRed());
        int greenDistance = Math.abs(from.getGreen() - this.color.getGreen());
        int blueDistance = Math.abs(from.getBlue() - this.color.getBlue());

        int redSteps = redDistance / 255;
        int greenSteps = greenDistance / 255;
        int blueSteps = blueDistance / 255;

        int r = 0, g = 0, b = 0;
        for (int i = 0; i < 255; i += 5) {
            r = from.getRed() > this.color.getRed() ? from.getRed() - redSteps : from.getRed() + redSteps;
            g = from.getGreen() > this.color.getGreen() ? from.getGreen() - greenSteps : from.getGreen() + greenSteps;
            b = from.getBlue() > this.color.getBlue() ? from.getBlue() - blueSteps : from.getBlue() + blueSteps;
            gradient.add(new Color(r, g, b));
        }

        gradient.add(this.color);

        return gradient;
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
