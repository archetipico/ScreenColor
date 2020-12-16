import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        try {
            Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            Robot robot = new Robot();

            JFrame frame = new JFrame("ScreenColor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setSize(300, 300);
            frame.setVisible(true);

            Color before = new Color(255, 255, 255);

            while (true) {
                BufferedImage capture = robot.createScreenCapture(screen);
                ScreenColor screenColor = new ScreenColor(capture);
                Color now = screenColor.getColor();

                ArrayList<Color> gradient = screenColor.getGradient(before);
                before = now;

                for (Color color : gradient) {
                    frame.getContentPane().setBackground(color);
                }

                TimeUnit.SECONDS.sleep(1);
            }
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
