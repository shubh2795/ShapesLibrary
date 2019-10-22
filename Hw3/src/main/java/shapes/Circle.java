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

    public String toString() {

        return "Circle,"+String.valueOf(center.getX())+","+String.valueOf(center.getY())+","+String.valueOf(radius)+",";
    }

    @Override
    public double computeArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");
        radius *= scaleFactor;
    }

    @Override
    public void render(Graphics graphics) throws ShapeException {

        int x = (int) Math.round(center.getX() - getRadius());
        int y = (int) Math.round(center.getY() - getRadius());
        int width = (int) Math.round(getRadius()*2);
        graphics.drawOval(x, y, width, width);

    }

}
