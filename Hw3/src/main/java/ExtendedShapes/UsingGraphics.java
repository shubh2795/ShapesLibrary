package ExtendedShapes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

public class UsingGraphics {
    public static void readAndWriteGraphics() {
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 200, 200);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(50, 50, 100, 100);

        try {
            ImageIO.write(bufferedImage, "png", new File("drawing.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
