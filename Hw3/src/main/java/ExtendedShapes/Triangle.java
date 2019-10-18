package ExtendedShapes;
import shapes.*;
import shapes.Point;
import shapes.ShapeException;
import shapes.Validator;

import java.awt.*;

public class Triangle implements Shapes {

    private Line line1;
    private Line line2;
    private Line line3;
    private Point point1, point2, point3;
    private double side1;
    private double side2;
    private double side3;

    public Triangle(double x1,double y1,double x2,double y2,double x3,double y3) throws ShapeException
    {
        point1 = new Point(x1,y1);
        point2 = new Point(x2,y2);
        point3 = new Point(x3,y3);
        line1 = new Line(point1,point2);
        line2 = new Line(point2,point3);
        line3 = new Line(point3,point1);
        side1 = line1.computeLength();
        side2 = line2.computeLength();
        side3 = line3.computeLength();

        //Colinearity check

        if(line1.computeSlope() == line2.computeSlope() || line2.computeSlope() == line3.computeSlope() || line1.computeSlope() == line3.computeSlope())
            throw new ShapeException("Edges of triangle must not be collinear");
    }

    //Constructs a Triangle with given all points.

    public Triangle(Line line1, Line line2, Line line3) throws ShapeException {
        if (line1 == null || line2 == null || line3 == null)
            throw new ShapeException("Invalid Point");
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;

        //Colinearity check

        if(line1.computeSlope() == line2.computeSlope() || line2.computeSlope() == line3.computeSlope() || line1.computeSlope() == line3.computeSlope())
            throw new ShapeException("Edges of triangle must not be collinear");
    }

    //valid double check

    public Triangle(double side1, double side2, double side3) throws ShapeException
    {
        if(side1 <= 0 || side2 <= 0 || side3 <= 0)
            throw new ShapeException("Invalid side");
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // Compute Perimeter method returns perimeter of Triangle
    public double getPerimeter()
    {
        return side1 + side2 + side3;
    }

    // Compute Area  of Triangle


    public double computeArea()
    {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * ((p - side1) * (p - side2) * (p - side3)));
    }
    /*
     * getVertices method returns an array of vertices of Triangle
     */
    public Point[] getVertices()
    {
        Point [] vertices = {point1, point2, point3};
        return vertices;
    }
    public void move(double deltaX,double deltaY)throws ShapeException{
        point1.move(deltaX,deltaY);
        point2.move(deltaX,deltaY);
        point3.move(deltaX,deltaY);
    }

    public Line getLine1() {
        return line1;
    }

    public Line getLine2() {
        return line2;
    }

    public Line getLine3() {
        return line3;
    }

    //TODO: Modify the scale method at the moment just addding to remove errors
    @Override
    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");

    }
    @Override
    public String toString() {
        return "Triangle,"+String.valueOf(point1.getX())+","+String.valueOf(point1.getY())+","+String.valueOf(point2.getX())+","+String.valueOf(point2.getY())+","+String.valueOf(point3.getX())+","+String.valueOf(point3.getY())+",";
    }

    public void render(Graphics graphics, int xOffset, int yOffset) throws ShapeException {

        // Shift the shape by the specified rendering offset
        move(-xOffset, -yOffset);

        // Compute the left side of the bounding box
        int x = (int) Math.round(point1.getX() - line1.computeLength());

        // Compute the top side of the bounding box
        int y = (int) Math.round(point3.getY() - line3.computeLength());

        // Compute the width of the bounding box
        int width = (int) line2.computeLength();

        int [] xPoints = {(int)point1.getX(), (int)point2.getX(), (int)point3.getX()};
        int [] yPoints = {(int)point1.getY(), (int)point2.getY(), (int)point3.getY()};

        // Draw the circle by drawing an oval in a square bounding box
        graphics.drawPolygon(xPoints, yPoints, 3);

        // Shift the shape back to its original location
        move(xOffset, yOffset);
    }
}
