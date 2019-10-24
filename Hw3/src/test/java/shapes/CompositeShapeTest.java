package shapes;

import org.junit.Test;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CompositeShapeTest {


    @Test
    public void testValidConstruction() throws ShapeException{
        String compositeshape1="\nShapesEnum.ShapeType.POINT,1,1,\nShapesEnum.ShapeType.LINE,1,1,4,5";
        String compositeshape="\nShapesEnum.ShapeType.POINT,1,1,\nShapesEnum.ShapeType.LINE,1,1,4,5"+compositeshape1;
        String[] objectDetails = new String[]{"\n"+AllShapesEnum.allShapes.CompositeShape,
                "\n"+AllShapesEnum.allShapes.Point,"1","1",
                "\n"+AllShapesEnum.allShapes.Line,"1","1","4","5",
                "\n"+AllShapesEnum.allShapes.Circle,"0","0","5",
                "\n"+AllShapesEnum.allShapes.Triangle,"0","0","3","0","3","4",
                "\n"+AllShapesEnum.allShapes.Rectangle,"0","0","4","0","4","4","0","4",
                "\n"+AllShapesEnum.allShapes.EmbeddedImage,"20","20","10","10","images\\panda.jpg",
                "\n"+AllShapesEnum.allShapes.CompositeShape,compositeshape};
        CompositeShape compositeShape=new CompositeShape(objectDetails);

    }

    @Test
    public void addShape() throws ShapeException{

        Point point = new Point(1,2);
        Line line = new Line(2, 2, 4, 4);
        Circle circle = new Circle(3, 3, 2);
        Triangle triangle = new Triangle(1, 2, 3, 6, 4, 1);
        Rectangle rectangle = new Rectangle(0, 0, 0, 3, 4, 3, 4, 0);
        EmbeddedImage embeddedImage = new EmbeddedImage(600, 600, 10, 10, "images\\panda.jpg");
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
        Common.saveShapeToTextFile(compositeShape, "textFiles\\compositeShape.txt");
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
    public void computeArea() throws ShapeException{
        Circle shape1 = new Circle(2, 2, 5);
        Rectangle shape2 = new Rectangle(10, 10, 10, 20, 20, 20, 20, 10);
        CompositeShape compositeShape = new CompositeShape();
        compositeShape.addShape(shape1);
        compositeShape.addShape(shape2);
        double area = compositeShape.computeArea();
        assertEquals(178.53981633974485, area, 0);
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
    public void scale() throws ShapeException {
        Circle circle = new Circle(2, 2, 5);
        Point point=new Point(10,20);
        Line line = new Line(30,30,70,70);
        CompositeShape compositeShape=new CompositeShape();
        compositeShape.addShape(point);
        compositeShape.addShape(line);
        compositeShape.addShape(circle);
        compositeShape.scale(2);
        assertEquals(10,point.getX(),0);
        assertEquals(20,point.getY(),0);
        assertEquals(30,line.getPoint1().getX(),0);
        assertEquals(30,line.getPoint1().getY(),0);
        assertEquals(110,line.getPoint2().getX(),0);
        assertEquals(110,line.getPoint2().getY(),0);
        assertEquals(10,circle.getRadius(),0);
    }

    @Test
    public  void testStringToTextFile(){
        CompositeShape compositeShape=new CompositeShape();
        compositeShape.stringToTextFile();
    }


    @Test
    public void render() throws ShapeException{

            CompositeShape composite = new CompositeShape();
            composite.addShape(new Rectangle(20, 20, 20, 40, 60, 40, 60, 20));
            composite.addShape(new Triangle(10, 20, 30, 60, 40, 10));
            composite.addShape(new EmbeddedImage(18, 16, 10, 10, "images\\panda.jpg"));
        try {
            BufferedImage bImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 100, 100);
            graphics.setColor(Color.BLACK);
            composite.render(graphics);
            ImageIO.write(bImg, "jpg", new File("images\\Composite.jpg"));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}