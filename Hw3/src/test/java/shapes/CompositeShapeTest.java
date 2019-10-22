package shapes;

import org.junit.Test;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CompositeShapeTest {

    @Test
    public void addShape() throws ShapeException{

        Point point = new Point(1,2);
        Line line = new Line(2, 2, 4, 4);
        Circle circle = new Circle(3, 3, 2);
        Triangle triangle = new Triangle(1, 2, 3, 6, 4, 1);
        Rectangle rectangle = new Rectangle(0, 0, 0, 3, 4, 3, 4, 0);
        EmbeddedImage embeddedImage = new EmbeddedImage(600, 600, 10, 10, "forrest-gump.jpg");
        CompositeShape compositeShape1 = new CompositeShape();
        compositeShape1.addShape(rectangle);

        CompositeShape compositeShape2 = new CompositeShape();
        compositeShape2.addShape(circle);
        compositeShape2.addShape(triangle);

        CompositeShape compositeShape3 = new CompositeShape();
        compositeShape3.addShape(line);
        compositeShape3.addShape(circle);


        CompositeShape compositeShape = new CompositeShape();
        compositeShape.addShape(point);
        compositeShape.addShape(line);
        compositeShape.addShape(circle);
        compositeShape.addShape(triangle);
        compositeShape.addShape(rectangle);
        compositeShape.addShape(compositeShape1);
        compositeShape.addShape(compositeShape2);

        compositeShape.addShape(compositeShape3);
        compositeShape.addShape(embeddedImage);
        compositeShape.removeShape(point);
        compositeShape.saveShape("compositeShape.txt", compositeShape);


    }

    @Test
    public void removeShape() {

    }

    @Test
    public void removeAllShapes() throws ShapeException{
        Circle circle = new Circle(3, 3, 2);
        Rectangle rectangle = new Rectangle(10, 10, 10, 20, 20, 20, 20, 10);
        CompositeShape compositeShape = new CompositeShape();
        compositeShape.addShape(circle);
        compositeShape.addShape(rectangle);
        compositeShape.removeAllShapes();
    }

    @Test
    public void createFile() {
    }

    @Test
    public void computeArea() {

    }

    @Test
    public void move() throws ShapeException {
        Circle shape1 = new Circle(2, 2, 5);
        Rectangle shape2 = new Rectangle(10, 10, 10, 20, 20, 20, 20, 10);
        CompositeShape compositeShape = new CompositeShape();
        compositeShape.addShape(shape1);
        compositeShape.addShape(shape2);
        compositeShape.move(5,5);
    }

    @Test
    public void scale() {
    }

    @Test
    public void render() throws ShapeException{

            CompositeShape composite = new CompositeShape();
            composite.addShape(new Rectangle(20, 20, 20, 40, 60, 40, 60, 20));
            composite.addShape(new Triangle(10, 20, 30, 60, 40, 10));
            composite.addShape(new EmbeddedImage(18, 16, 10, 10, "forrest-gump.jpg"));
        try {
            BufferedImage bImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 100, 100);
            graphics.setColor(Color.BLACK);
            composite.render(graphics);
            ImageIO.write(bImg, "jpg", new File("Composite.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}