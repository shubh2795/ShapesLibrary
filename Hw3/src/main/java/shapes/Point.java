package shapes;
import java.awt.*;

    @SuppressWarnings("WeakerAccess")
    public class Point implements Shapes {
    private double x;
    private double y;

    public Point(double x, double y) throws ShapeException {
        if (Double.isInfinite(x) || Double.isInfinite(y) ||
                Double.isNaN(x) || Double.isNaN(y)){
            throw new ShapeException("Invalid Point");
        }
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }

    public double getY() { return y; }

    public void moveX(double deltaX) throws ShapeException {
        if (Double.isInfinite(deltaX) || Double.isNaN(deltaX))
            throw new ShapeException("Invalid delta value for move operation");

        x += deltaX;
    }

    public void moveY(double deltaY) throws ShapeException {
        if (Double.isInfinite(deltaY) || Double.isNaN(deltaY))
            throw new ShapeException("Invalid delta value for move operation");

        y += deltaY;
    }

    public Point copy() throws ShapeException {
        return new Point(x, y);
    }

    @Override
    public void move(double deltaX, double deltaY) throws ShapeException {
        moveX(deltaX);
        moveY(deltaY);
    }

    // TODO: Modify the scale method at the moment just addding to remove errors
    @Override
    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");

    }

    @Override
    public double computeArea() {
    return 0;
    }

    public void render(Graphics graphics, int xOffset, int yOffset) throws ShapeException {

        move(-xOffset, -yOffset);
        int x1 = (int) Math.round(x);
        int x2 = (int) Math.round(x);
        int y1 = (int) Math.round(y);
        int y2 = (int) Math.round(y);
        graphics.drawLine(x1,y1,x2,y2);
        move(xOffset, yOffset);
    }
}
