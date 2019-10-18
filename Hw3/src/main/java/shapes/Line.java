package shapes;


import java.awt.*;

@SuppressWarnings("WeakerAccess")
public class Line implements Shapes {
    private Point point1;
    private Point point2;

    public Line(double x1, double y1, double x2, double y2) throws ShapeException {
        point1 = new Point(x1, y1);
        point2 = new Point(x2, y2);
    }

    public Line(Point point1, Point point2) throws ShapeException {
        if (point1==null || point2==null){
            throw new ShapeException("Invalid Point");
        }

        this.point1 = point1.copy();
        this.point2 = point2.copy();
    }

    public Point getPoint1() throws ShapeException { return point1.copy(); }

    public Point getPoint2() throws ShapeException { return point2.copy(); }

   @Override
    public void move(double deltaX, double deltaY) throws ShapeException {
        point1.move(deltaX, deltaY);
        point2.move(deltaX, deltaY);
    }

    public double computeLength() {
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) +
                Math.pow(point2.getY() - point1.getY(), 2));
    }

    public double computeSlope() {
        return (point2.getX() - point1.getX())/(point2.getY() - point1.getY());
    }

    @Override
    public void render(Graphics graphics, int xOffset, int yOffset) throws ShapeException {

        // Shift the shape by the specified rendering offset
        move(-xOffset, -yOffset);

        // Compute the left side of the bounding box
        int x1 = (int) Math.round(point1.getX());
        int x2 = (int) Math.round(point2.getX());

        // Compute the top side of the bounding box
        int y1 = (int) Math.round(point1.getY());
        int y2 = (int) Math.round(point2.getY());

        // Draw the circle by drawing an oval in a square bounding box
        graphics.drawLine(x1,y1,x2,y2);

        // Shift the shape back to its original location
        move(xOffset, yOffset);
    }

    @Override
    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");
        double getSlope = computeSlope();
    }

    @Override
    public double computeArea() {
    return 0;
    }
}
