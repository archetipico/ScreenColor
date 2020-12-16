import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ScreenColor {

    private final BufferedImage capture;
    private final int w;
    private final int h;
    private final int area;
    private final long[] totalRGB;
    private final int[] avgRGB;
    private Color color;

    public ScreenColor(BufferedImage capture) {
        this.capture = capture;
        this.w = capture.getWidth();
        this.h = capture.getHeight();
        this.area = this.w * this.h;
        this.totalRGB = new long[3];
        this.avgRGB = new int[3];
        this.color = new Color(0, 0, 0);
    }

    public Color getColor() {
        getTotalRGB();

        this.avgRGB[0] = (int) (this.totalRGB[0] / this.area);
        this.avgRGB[1] = (int) (this.totalRGB[1] / this.area);
        this.avgRGB[2] = (int) (this.totalRGB[2] / this.area);
        this.color = new Color(this.avgRGB[0], this.avgRGB[1], this.avgRGB[2]);

        return this.color;
    }

    public ArrayList<Color> getGradient(Color before) {
        ArrayList<Color> gradient = new ArrayList<>();
        gradient.add(before);

        int redBefore = before.getRed();
        int greenBefore = before.getGreen();
        int blueBefore = before.getBlue();

        int redSteps = Math.abs(redBefore - this.avgRGB[0]) / 10;
        int greenSteps = Math.abs(greenBefore - this.avgRGB[1]) / 10;
        int blueSteps = Math.abs(blueBefore - this.avgRGB[2]) / 10;

        int tempR, tempG, tempB;
        for (int i = 0; i < 25; i++) {
            tempR = redBefore > this.avgRGB[0] ? redBefore - redSteps : redBefore + redSteps;
            tempG = greenBefore > this.avgRGB[1] ? greenBefore - greenSteps : greenBefore + greenSteps;
            tempB = blueBefore > this.avgRGB[2] ? blueBefore - blueSteps : blueBefore + blueSteps;
            gradient.add(new Color(tempR, tempG, tempB));
        }

        gradient.add(this.color);

        return gradient;
    }

    private void getTotalRGB() {
        for (int i = 0; i < this.w; i++) {
            for (int j = 0; j < this.h; j++) {
                Color pixel = new Color(capture.getRGB(i, j));
                this.totalRGB[0] += pixel.getRed();
                this.totalRGB[1] += pixel.getGreen();
                this.totalRGB[2] += pixel.getBlue();
            }
        }
    }
}
