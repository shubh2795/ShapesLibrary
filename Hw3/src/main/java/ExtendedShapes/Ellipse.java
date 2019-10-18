package ExtendedShapes;

import shapes.Line;
import shapes.ShapeException;
import shapes.Shapes;
import shapes.Validator;

import java.awt.*;

public class Ellipse implements Shapes {

    private Point center;
    private Point focus1;
    private Point focus2;
    private double majorAxis;
    private double minorAxis;
    private Line focalDistance1;
    private Line focalDistance2;


    public Ellipse(double x, double y, double x1, double y1, double x2, double y2, double majorAxis, double minorAxis) throws ShapeException {
        Validator.validatePositiveDouble(majorAxis, "Invalid Major-axis");
        Validator.validatePositiveDouble(minorAxis, "Invalid Minor-axis");
        center = new Point(x, y);
        focus1 = new Point(x1, y1);
        focus2 = new Point(x2, y2);
        this.majorAxis = majorAxis;
        this.minorAxis = minorAxis;
        focalDistance1 = new Line(center, focus1);
        focalDistance2 = new Line(center, focus2);
        if (focalDistance1.computeLength() != focalDistance2.computeLength())
            throw new ShapeException("Invalid Focal distance");
        if (focalDistance1.computeLength() + focalDistance2.computeLength() >= majorAxis)
            throw new ShapeException("sum of focal distances can not be greater than majorAxis");
    }


    public Ellipse(Point center, Point focus1, Point focus2, double majorAxis, double minorAxis) throws ShapeException {
        Validator.validatePositiveDouble(majorAxis, "Invalid Major Radius");
        Validator.validatePositiveDouble(minorAxis, "Invalid Minor Radius");
        if (center == null)
            throw new ShapeException("Invalid center point");
        if (focus1 == null || focus2 == null)
            throw new ShapeException("Invalid Focal points");
        this.focus1 = focus1;
        this.focus2 = focus2;
        this.center = center;
        this.majorAxis = majorAxis;
        this.minorAxis = minorAxis;
        focalDistance1 = new Line(center, focus1);
        focalDistance2 = new Line(center, focus2);
        if (focalDistance1.computeLength() != focalDistance2.computeLength())
            throw new ShapeException("Invalid Focal distance");
        if (focalDistance1.computeLength() + focalDistance2.computeLength() >= majorAxis)
            throw new ShapeException("sum of focal distances can not be greater than majorAxis");
    }


    public Ellipse(Point center, Point focus1, Point focus2) throws ShapeException {
        if (center == null)
            throw new ShapeException("Invalid center point");
        if (focus1 == null || focus2 == null)
            throw new ShapeException("Invalid Focal points");
        this.focus1 = focus1;
        this.focus2 = focus2;
        this.center = center;
    }


    public Point getCenter() {
        return center;
    }

    //TODO: Modify the scale method at the moment just addding to remove errors
    @Override
    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");

    }

    public double getMajorAxis() {
        return majorAxis;
    }

    public double getMinorAxis() {
        return minorAxis;
    }


    public Point getFocus1() {
        return focus1;
    }

    public Point getFocus2() {
        return focus2;
    }

    public void move(double deltaX, double deltaY) throws ShapeException {
        center.move(deltaX, deltaY);
        focus1.move(deltaX, deltaY);
        focus2.move(deltaX, deltaY);
    }


    public double computeArea() {
        return Math.PI * (majorAxis / 2) * (minorAxis / 2);
    }

    @Override
    public String toString() {
        System.out.println("toString method");
        return "Ellipse,"+String.valueOf(center.getX())+","+String.valueOf(center.getY())+","+String.valueOf(focus1.getX())+","+String.valueOf(focus1.getY())+","+String.valueOf(focus2.getX())+","+String.valueOf(focus2.getY())+","+String.valueOf(majorAxis)+","+String.valueOf(minorAxis)+",";
    }


    public void render(Graphics graphics, int xOffset, int yOffset) throws ShapeException {
        move(-xOffset, -yOffset);
        int x = (int) Math.round(center.getX() - getMajorAxis()/2);
        int y = (int) Math.round(center.getY() - getMinorAxis()/2);
        int width = (int) Math.round(getMajorAxis()/2 * 2);
        graphics.drawOval(x, y, width, width);
        move(xOffset, yOffset);
    }
}
