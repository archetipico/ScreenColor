import static org.junit.Assert.*;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ScreenColorTests {

    @Test
    public void testGetColor() {
        try {
            BufferedImage pic0 = ImageIO.read(new File("src/test/images/antelope-canyon-640x480.jpg"));
            BufferedImage pic1 = ImageIO.read(new File("src/test/images/french-desert-1599x1066.jpg"));
            BufferedImage pic2 = ImageIO.read(new File("src/test/images/lotus-flower-1279x852.jpg"));
            BufferedImage pic3 = ImageIO.read(new File("src/test/images/oranges-2500x1667.jpg"));
            BufferedImage pic4 = ImageIO.read(new File("src/test/images/scsi-hd-1920x1440.jpg"));

            Color color0 = new ScreenColor(pic0).getColor();
            Color color1 = new ScreenColor(pic1).getColor();
            Color color2 = new ScreenColor(pic2).getColor();
            Color color3 = new ScreenColor(pic3).getColor();
            Color color4 = new ScreenColor(pic4).getColor();

            assertEquals(color0, new Color(75, 40, 22));
            assertEquals(color1, new Color(133, 138, 130));
            assertEquals(color2, new Color(196, 99, 133));
            assertEquals(color3, new Color(200, 106, 17));
            assertEquals(color4, new Color(90, 88, 79));
        } catch (IOException e) {
            fail("Image not found");
        }
    }

    @Test
    public void testGetGradient() {
        try {
            BufferedImage pic = ImageIO.read(new File("src/test/images/lotus-flower-1279x852.jpg"));

            Color testColor = new Color(255, 255, 255);
            ArrayList<Color> gradient = new ScreenColor(pic).getGradient(testColor);

            assertTrue(gradient.contains(testColor));
        } catch (IOException e) {
            fail("Image not found");
        }
    }
}
