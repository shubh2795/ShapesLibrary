package shapes;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class RectangleTest {

    @Test
    public void testValidConstruction() throws ShapeException {
        Point point1 = new Point(0,0);
        Point point2 = new Point(0,3);

        Line line1 = new Line(point1, point2);
        assertSame(point1, line1.getPoint1());
        assertSame(point2, line1.getPoint2());

        Point point3 = new Point(4,3);

        Line line2 = new Line(point2, point3);
        assertSame(point2, line2.getPoint1());
        assertSame(point3, line2.getPoint2());

        Point point4 = new Point(4,0);

        Line line3 = new Line(point3, point4);
        assertSame(point3, line3.getPoint1());
        assertSame(point4, line3.getPoint2());

        Line line4 = new Line(point4, point1);
        assertSame(point4, line4.getPoint1());
        assertSame(point1, line4.getPoint2());

        Rectangle myRectangle = new Rectangle(line1,line2,line3,line4);

        myRectangle = new Rectangle(0,0,0,3,4,3,4,0);
        assertEquals(0, line1.getPoint1().getX(), 0);
        assertEquals(0, line1.getPoint1().getY(), 0);
        assertEquals(0, line1.getPoint2().getX(), 0);
        assertEquals(3, line1.getPoint2().getY(), 0);
        assertEquals(0, line2.getPoint1().getX(), 0);
        assertEquals(3, line2.getPoint1().getY(), 0);
        assertEquals(4, line2.getPoint2().getX(), 0);
        assertEquals(3, line2.getPoint2().getY(), 0);
        assertEquals(4, line3.getPoint1().getX(), 0);
        assertEquals(3, line3.getPoint1().getY(), 0);
        assertEquals(4, line3.getPoint2().getX(), 0);
        assertEquals(0, line3.getPoint2().getY(), 0);
        assertEquals(4, line4.getPoint1().getX(), 0);
        assertEquals(0, line4.getPoint1().getY(), 0);
        assertEquals(0, line4.getPoint2().getX(), 0);
        assertEquals(0, line4.getPoint2().getY(), 0);
    }
    @Test
    public void testInvalidConstruction() throws Exception {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(0, 3);
        Point point3 = new Point(4, 3);
        Point point4 = new Point(4, 0);
        Line line1 = new Line(point1, point2);
        Line line2 = new Line(point2, point3);
        Line line3 = new Line(point3, point4);
        Line line4 = new Line(point4, point1);
        try {
            new Rectangle(line1, line2, line3, null);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(line1, line2, null, line4);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(line1, null, line3, line4);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(null, line2, line3, line4);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(Double.POSITIVE_INFINITY, 0, 0, 3, 4, 3, 4, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }
        try {
            new Rectangle(0, Double.POSITIVE_INFINITY, 0, 3, 4, 3, 4, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }
        try {
            new Rectangle(0, 0, Double.POSITIVE_INFINITY, 3, 4, 3, 4, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }
        try {
            new Rectangle(0, 0, 0, Double.POSITIVE_INFINITY, 4, 3, 4, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }
        try {
            new Rectangle(0, 0, 0, 3, Double.POSITIVE_INFINITY, 3, 4, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }
        try {
            new Rectangle(0, 0, 0, 3, 4, Double.POSITIVE_INFINITY, 4, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }
        try {
            new Rectangle(0, 0, 0, 3, 4, 3, Double.POSITIVE_INFINITY, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }
        try {
            new Rectangle(0, 0, 0, 3, 4, 3, 4, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }
        try {
            new Rectangle(0, 0);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid side", e.getMessage());
        }
        try {
            new Rectangle(1, 3, 3, 5, 5, 3, 3, 2);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Adjacent lines should be perpendicular to each other", e.getMessage());
        }
        try{
            Point point5 = new Point(1, 3);
            Point point6 = new Point(3, 5);
            Point point7 = new Point(5, 3);
            Point point8 = new Point(3, 2);
            Line line5 = new Line(point5, point6);
            Line line6 = new Line(point6, point7);
            Line line7 = new Line(point7, point8);
            Line line8 = new Line(point8, point5);
            new Rectangle(line5, line6, line7, line8);
            fail("Expected exception not thrown");
        }catch(ShapeException e)
        {
            assertEquals("Adjacent lines should be perpendicular to each other", e.getMessage());
        }
    }

    @Test
    public void computeArea() throws ShapeException{
        Rectangle rectangle = new Rectangle(20, 30);
        assertEquals(600, rectangle.computeArea(), 0);

        Rectangle rectangle1 = new Rectangle(40, 10);
        assertEquals(400, rectangle1.computeArea(), 0);
    }

    // TODO: Test these
    @Test
    public void getPoint1() throws ShapeException{
        Rectangle rectangle = new Rectangle(1, 1,1,0,3,5,8,0);
    }

    @Test
    public void getPoint2() throws ShapeException{
        Rectangle rectangle = new Rectangle(1, 1,1,0,3,5,8,0);
    }

    @Test
    public void getPoint3()throws ShapeException {
        Rectangle rectangle = new Rectangle(1, 1,1,0,3,5,8,0);
    }

    @Test
    public void getPoint4()throws ShapeException {
        Rectangle rectangle = new Rectangle(1, 1,1,0,3,5,8,0);
    }

    @Test
    public void getBreadth() throws ShapeException {
        Rectangle rectangle = new Rectangle(20, 40);
        assertEquals(40, rectangle.getBreadth(), 0);

        Rectangle rectangle1 = new Rectangle(40, 10);
        assertEquals(10, rectangle1.getBreadth(), 0);
    }

    @Test
    public void getLength() throws ShapeException {
        Rectangle rectangle = new Rectangle(20, 40);
        assertEquals(20, rectangle.getLength(), 0);

        Rectangle rectangle1 = new Rectangle(40, 10);
        assertEquals(40, rectangle1.getLength(), 0);

    }

    @Test
    public void move() throws ShapeException {
        Rectangle rectangle = new Rectangle(1, 1,1,0,3,5,8,0);
        rectangle.move(2,4);
        //Point 1
        assertEquals(1, rectangle.getLine1().getPoint1().getX(), 0);
        assertEquals(1, rectangle.getLine1().getPoint1().getY(), 0);
        assertEquals(1, rectangle.getLine2().getPoint1().getX(), 0);
        assertEquals(0, rectangle.getLine2().getPoint1().getY(), 0);
        assertEquals(3, rectangle.getLine3().getPoint1().getX(), 0);
        assertEquals(5, rectangle.getLine3().getPoint1().getY(), 0);
        assertEquals(1, rectangle.getLine4().getPoint1().getX(), 0);
        assertEquals(1, rectangle.getLine4().getPoint1().getY(), 0);
        //Point 2
        assertEquals(1, rectangle.getLine1().getPoint2().getX(), 0);
        assertEquals(0, rectangle.getLine1().getPoint2().getY(), 0);
        assertEquals(3, rectangle.getLine2().getPoint2().getX(), 0);
        assertEquals(5, rectangle.getLine2().getPoint2().getY(), 0);
        assertEquals(8, rectangle.getLine3().getPoint2().getX(), 0);
        assertEquals(0, rectangle.getLine3().getPoint2().getY(), 0);
        assertEquals(8, rectangle.getLine4().getPoint2().getX(), 0);
        assertEquals(0, rectangle.getLine4().getPoint2().getY(), 0);
    }

    // TODO: Test these
    @Test
    public void getLine1() throws ShapeException {
        Rectangle rectangle = new Rectangle(1, 1,1,0,3,5,8,0);
    }

    @Test
    public void getLine2() throws ShapeException{
        Rectangle rectangle = new Rectangle(1, 1,1,0,3,5,8,0);
    }

    @Test
    public void getLine3() throws ShapeException {
        Rectangle rectangle = new Rectangle(1, 1,1,0,3,5,8,0);
    }

    @Test
    public void getLine4()throws ShapeException{
        Rectangle rectangle = new Rectangle(1, 1,1,0,3,5,8,0);
    }
//Todo: test scale
    @Test
    public void scale() {

    }

    @Test
    public void render() {
        try {
            Rectangle myRectangle = new Rectangle(25,30,25,85,75,85,75,30);
            BufferedImage bImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 200, 300);
            graphics.setColor(Color.GREEN);
            myRectangle.render(graphics);
            assertTrue(ImageIO.write(bImg, "jpg", new File("Rectangle.jpg")));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}