import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
        try {
            Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage capture = new Robot().createScreenCapture(screen);
            ScreenColor screenCol = new ScreenColor(capture);
            Color color = screenCol.getColor();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
