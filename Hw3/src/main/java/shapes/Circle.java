package shapes;
import java.awt.*;

@SuppressWarnings("WeakerAccess")
public class Circle implements Shapes {
    private Point center;
    private double radius;


    public Circle(double x, double y, double radius) throws ShapeException {
        Validator.validatePositiveDouble(radius, "Invalid radius");
        center = new Point(x, y);
        this.radius = radius;
    }


    public Circle(Point center, double radius) throws ShapeException {
        Validator.validatePositiveDouble(radius, "Invalid radius");
        if (center==null){
            throw new ShapeException("Invalid center point");
        }
        this.center = center.copy();
        this.radius = radius;
    }

    public Point getCenter() throws ShapeException { return center.copy(); }

    public double getRadius() { return radius; }

    public void move(double deltaX, double deltaY) throws ShapeException {
        center.move(deltaX, deltaY);
    }


    @Override
    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");
        radius *= scaleFactor;
    }

    @Override
    public double computeArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public void render(Graphics graphics, int xOffset, int yOffset) throws ShapeException {

        // Shift the shape by the specified rendering offset
        move(-xOffset, -yOffset);

        // Compute the left side of the bounding box
        int x = (int) Math.round(center.getX() - getRadius());

        // Compute the top side of the bounding box
        int y = (int) Math.round(center.getY() - getRadius());

        // Compute the width of the bounding box
        int width = (int) Math.round(getRadius()*2);

        // Draw the circle by drawing an oval in a square bounding box
        graphics.drawOval(x, y, width, width);

        // Shift the shape back to its original location
        move(xOffset, yOffset);
    }

}
