package shapes;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void testValidConstruction() throws ShapeException {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(3, 6);
        Line line1 = new Line(point1, point2);
        assertSame(point1, line1.getPoint1());
        assertSame(point2, line1.getPoint2());
        Point point3 = new Point(4, 1);
        Line line2 = new Line(point2, point3);
        assertSame(point2, line2.getPoint1());
        assertSame(point3, line2.getPoint2());
        Line line3 = new Line(point3, point1);
        assertSame(point3, line3.getPoint1());
        assertSame(point1, line3.getPoint2());
        Triangle triangle = new Triangle(line1, line2, line3);
        triangle = new Triangle(1, 2, 3, 6, 4, 1);
        assertEquals(1, line1.getPoint1().getX(), 0);
        assertEquals(2, line1.getPoint1().getY(), 0);
        assertEquals(3, line1.getPoint2().getX(), 0);
        assertEquals(6, line1.getPoint2().getY(), 0);
        assertEquals(3, line2.getPoint1().getX(), 0);
        assertEquals(6, line2.getPoint1().getY(), 0);
        assertEquals(4, line2.getPoint2().getX(), 0);
        assertEquals(1, line2.getPoint2().getY(), 0);
        assertEquals(4, line3.getPoint1().getX(), 0);
        assertEquals(1, line3.getPoint1().getY(), 0);
        assertEquals(1, line3.getPoint2().getX(), 0);
        assertEquals(2, line3.getPoint2().getY(), 0);
    }

    @Test
    public void testInvalidConstruction() throws ShapeException {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 4);
        Point point3 = new Point(3, 4);
        Line line1 = new Line(point1, point2);
        Line line2 = new Line(point2, point3);
        Line line3 = new Line(point3, point1);
        try {
            new Triangle(line1, line2, null);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Triangle(line1, null, line3);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Triangle(null, line2, line3);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Triangle(Double.POSITIVE_INFINITY, 2, 3,6,4,1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }
        try {
            new Triangle(1, Double.POSITIVE_INFINITY, 3,6,4,1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }
        try {
            new Triangle(1, 2, Double.POSITIVE_INFINITY,6,4,1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }
        try {
            new Triangle(1, 2, 3,Double.POSITIVE_INFINITY,4,1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }
        try {
            new Triangle(1, 2, 3,4,Double.POSITIVE_INFINITY,1);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }
        try {
            new Triangle(1, 2, 3,4,4,Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }
        try {
            new Triangle(-3,2,5);
            fail("Expected exception not thrown for when the last parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid side", e.getMessage());
        }
        try {
            new Triangle(1,1,2,2,3,3);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Edges of triangle must not be collinear", e.getMessage());
        }
        try {
            Point point4 = new Point(1, 1);
            Point point5 = new Point(2, 2);
            Point point6 = new Point(3, 3);
            Line line4 = new Line(point4, point5);
            Line line5 = new Line(point5, point6);
            Line line6 = new Line(point6, point4);
            new Triangle(line4, line5, line6);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Edges of triangle must not be collinear", e.getMessage());
        }
    }


    @Test
    public void getPerimeter() throws ShapeException {
        Triangle triangle = new Triangle(3,4,5);
        assertEquals(12, triangle.getPerimeter(),0);
    }

    @Test
    public void getVertices() {
    }

    @Test
    public void move() throws ShapeException{

        Triangle triangle = new Triangle(3, 2, 5, 6, 9, 1);
        triangle.move(1.5,2);
        assertEquals(3, triangle.getLine1().getPoint1().getX(), 0);
        assertEquals(2, triangle.getLine1().getPoint1().getY(), 0);
        assertEquals(5, triangle.getLine1().getPoint2().getX(), 0);
        assertEquals(6, triangle.getLine1().getPoint2().getY(), 0);
        assertEquals(5, triangle.getLine2().getPoint1().getX(), 0);
        assertEquals(6, triangle.getLine2().getPoint1().getY(), 0);
        assertEquals(9, triangle.getLine2().getPoint2().getX(), 0);
        assertEquals(1, triangle.getLine2().getPoint2().getY(), 0);
        assertEquals(3, triangle.getLine3().getPoint1().getX(), 0);
        assertEquals(2, triangle.getLine3().getPoint1().getY(), 0);
        assertEquals(9, triangle.getLine3().getPoint2().getX(), 0);
        assertEquals(1, triangle.getLine3().getPoint2().getY(), 0);
    }

    @Test
    public void getLine1() {
    }

    @Test
    public void getLine2() {
    }

    @Test
    public void getLine3() {
    }

    @Test
    public void computeArea() throws ShapeException{
        Triangle myTriangle = new Triangle(8,10,6);
        assertEquals(24, myTriangle.computeArea(),0);
    }

    @Test
    public void scale() {
    }

    @Test
    public void render() {
        try {
            Triangle myTriangle = new Triangle(25, 30, 40, 65, 60, 20);
            BufferedImage bImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 100, 100);
            graphics.setColor(Color.BLACK);
            myTriangle.render(graphics);
            assertTrue(ImageIO.write(bImg, "jpg", new File("Triangle.jpg")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}