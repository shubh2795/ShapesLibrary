package shapes;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class EmbeddedImageTest {

    @Test
    public void toStringTest() {

    }

    @Test
    public void testComputeArea() throws ShapeException {
        EmbeddedImage myEmbeddedPicture = new EmbeddedImage(10, 20, 25, 25, "images\\panda.jpg");
        assertEquals(200,myEmbeddedPicture.computeArea(),0);
    }

    @Test
    public void testMove() throws ShapeException {
        EmbeddedImage myEmbeddedPicture = new EmbeddedImage(10, 20, 25, 25, "images\\panda.jpg");
        myEmbeddedPicture.move(4,5);
        assertEquals(29,myEmbeddedPicture.getImageLocation().getX(),0);
        assertEquals(30,myEmbeddedPicture.getImageLocation().getY(),0);
    }

    @Test
    public void scale() {
    }

    @Test
    public void render() {
        try {
            double length = 600;
            double width = 480;
            String source = "images\\panda.jpg";
            EmbeddedImage myEmbeddedPicture = new EmbeddedImage(length, width, 25, 25, source);
            File inputFile = ShapeFlyweightFactory.objectManager(source);
            BufferedImage inputImage = null;
            inputImage = ImageIO.read(inputFile);
            // creates output image
            BufferedImage outputImage = new BufferedImage(500, 600, inputImage.getType());

            // scales the input image to the output image
            Graphics2D graphics = outputImage.createGraphics();
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, 500, 600);
            graphics.setColor(Color.RED);

            myEmbeddedPicture.render(graphics);
            assertTrue(ImageIO.write(outputImage, "jpg", new File("images\\embeddedImage.jpg")));
            assertEquals("images\\panda.jpg",myEmbeddedPicture.getSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}