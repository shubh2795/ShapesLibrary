package shapes;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class LineTest {

    @Test
    public void testValidConstruction() throws Exception {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 10);

        Line myLine = new Line(p1, p2);
        assertEquals(1, myLine.getPoint1().getX(), 0);
        assertEquals(2, myLine.getPoint1().getY(), 0);
        assertEquals(4, myLine.getPoint2().getX(), 0);
        assertEquals(10, myLine.getPoint2().getY(), 0);

        myLine = new Line(p1, p1);
        assertEquals(1, myLine.getPoint1().getX(), 0);
        assertEquals(2, myLine.getPoint1().getY(), 0);
        assertEquals(1, myLine.getPoint2().getX(), 0);
        assertEquals(2, myLine.getPoint2().getY(), 0);

        p1 = new Point(1.4,2.5);
        p2 = new Point(4.6, 10.7);
        myLine = new Line(p1, p2);
        assertEquals(1.4, myLine.getPoint1().getX(), 0);
        assertEquals(2.5, myLine.getPoint1().getY(), 0);
        assertEquals(4.6, myLine.getPoint2().getX(), 0);
        assertEquals(10.7, myLine.getPoint2().getY(), 0);

        myLine = new Line(1, 3.33, 4.444, 5.5555);
        assertEquals(1, myLine.getPoint1().getX(), 0);
        assertEquals(3.33, myLine.getPoint1().getY(), 0);
        assertEquals(4.444, myLine.getPoint2().getX(), 0);
        assertEquals(5.5555, myLine.getPoint2().getY(), 0);
    }

    @Test
    public void testInvalidConstruction() throws ShapeException {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 10);

        try {
            new Line(p1, null);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid Point", e.getMessage());
        }

        try {
            new Line(null, p2);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid Point", e.getMessage());
        }



    }


    @Test
    public void testMove() throws ShapeException {

        Line myLine = new Line(1, 2, 4, 10);

        myLine.move(3, 4);
        assertEquals(4, myLine.getPoint1().getX(), 0);
        assertEquals(6, myLine.getPoint1().getY(), 0);
        assertEquals(7, myLine.getPoint2().getX(), 0);
        assertEquals(14, myLine.getPoint2().getY(), 0);

        myLine.move(.4321, .7654);
        assertEquals(4.4321, myLine.getPoint1().getX(), 0);
        assertEquals(6.7654, myLine.getPoint1().getY(), 0);
        assertEquals(7.4321, myLine.getPoint2().getX(), 0);
        assertEquals(14.7654, myLine.getPoint2().getY(), 0);

        myLine.move(-0.4321, -0.7654);
        assertEquals(4, myLine.getPoint1().getX(), 0);
        assertEquals(6, myLine.getPoint1().getY(), 0);
        assertEquals(7, myLine.getPoint2().getX(), 0);
        assertEquals(14, myLine.getPoint2().getY(), 0);
    }

    @Test
    public void testComputeLength() throws ShapeException {

        Line myLine = new Line(1, 2, 4, 10);
        assertEquals(8.544, myLine.computeLength(), 0.001);

        myLine = new Line(1, 2, 1, 2);
        assertEquals(Math.sqrt(0), myLine.computeLength(), 0);

        myLine = new Line(3, -2, -4, 10);
        assertEquals(13.892, myLine.computeLength(), 0.001);
    }

    @Test
    public void testComputeSlope() throws ShapeException {

        Line myLine = new Line(2, 2, 4, 10);
        assertEquals(4, myLine.computeSlope(), 0.1);

        myLine = new Line(2, 2, 4, 10);
        assertEquals(4, myLine.computeSlope(), 0.1);

        myLine = new Line(2, 2, 2, 4);
        assertEquals(Double.POSITIVE_INFINITY, myLine.computeSlope(), 0.1);

        myLine = new Line(2, 2, 4, 2);
        assertEquals(0, myLine.computeSlope(), 0.1);

        myLine = new Line(4, 2, 2, 2);
        assertEquals(0, myLine.computeSlope(), 0.1);

        myLine = new Line(2, 2, 2, 2);
        assertEquals(Double.NaN, myLine.computeSlope(), 0.1);
    }

    @Test
    public void testStrongEncapsulation() throws ShapeException {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 10);

        Line myLine = new Line(p1, p2);
        assertNotSame(p1, myLine.getPoint1());
        assertNotSame(p2, myLine.getPoint2());

        p1.move(10, 20);
        assertEquals(1, myLine.getPoint1().getX(), 0);
        assertEquals(2, myLine.getPoint1().getY(), 0);
        assertEquals(4, myLine.getPoint2().getX(), 0);
        assertEquals(10, myLine.getPoint2().getY(), 0);

        p1.move(20, 30);
        assertEquals(1, myLine.getPoint1().getX(), 0);
        assertEquals(2, myLine.getPoint1().getY(), 0);
        assertEquals(4, myLine.getPoint2().getX(), 0);
        assertEquals(10, myLine.getPoint2().getY(), 0);

    }

    @Test
    public void testRender() throws ShapeException{
        try {
            Line myLine = new Line(50,10,50,90);
            BufferedImage bImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bImg.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 100, 100);
            graphics.setColor(Color.BLUE);
            myLine.render(graphics);

            assertTrue(ImageIO.write(bImg, "jpg", new File("images\\Line.jpg")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testComputeArea() throws ShapeException {
        Line myLine = new Line(50,10,50,90);
        assertEquals(0,myLine.computeArea(),0);
    }

    @Test
    public void scale() throws ShapeException{
        Point point1 = new Point(10, 10);
        Point point2 = new Point(40,40);
        Line line=new Line(point1,point2);
        line.scale(5);
        assertEquals(point1.getX(),line.getPoint1().getX(),0);
        assertEquals(point1.getY(),line.getPoint1().getY(),0);
        assertEquals(160,line.getPoint2().getX(),0);
        assertEquals(160,line.getPoint2().getY(),0);
    }

    @Test
    public void testStringToTextFile() throws ShapeException {
        Line myLine= new Line(1,2,4,10);
        String expected="Line,1.0,2.0,4.0,10.0,";
        String actual= myLine.stringToTextFile();
        assertEquals(expected,actual);
    }

}