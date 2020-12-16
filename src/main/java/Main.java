import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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

            while (true) {
                BufferedImage capture = robot.createScreenCapture(screen);
                Color screenColor = new ScreenColor(capture).getColor();
                frame.getContentPane().setBackground(screenColor);

                TimeUnit.SECONDS.sleep(5);
            }
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
