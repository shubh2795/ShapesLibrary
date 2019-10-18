package shapes;
import java.awt.*;


    @SuppressWarnings("WeakerAccess")
    public class Point implements Shapes {
    private double x;
    private double y;


    public Point(double x, double y) throws ShapeException {
        if (Double.isInfinite(x) || Double.isInfinite(y) ||
                Double.isNaN(x) || Double.isNaN(y))
            throw new ShapeException("Invalid Point");

        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }

    public double getY() { return y; }


    @Override
    public void move(double deltaX, double deltaY) throws ShapeException {
        moveX(deltaX);
        moveY(deltaY);
    }

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

        // Shift the shape by the specified rendering offset
        move(-xOffset, -yOffset);

        // Compute the left side of the bounding box
        int x1 = (int) Math.round(x);
        int x2 = (int) Math.round(x);

        // Compute the top side of the bounding box
        int y1 = (int) Math.round(y);
        int y2 = (int) Math.round(y);

        // Compute the width of the bounding box
        //int width = (int) myrectangle1.getWidth();

        //  int [] xPoints = {(int)myrectangle1.getPoint1().getX(), (int)myrectangle1.getPoint2().getX(), (int)myrectangle1.getPoint3().getX(), (int)myrectangle1.getPoint4().getX()};
        // int [] yPoints = {(int)myrectangle1.getPoint1().getY(), (int)myrectangle1.getPoint2().getY(), (int)myrectangle1.getPoint3().getY(), (int)myrectangle1.getPoint4().getY()};

        // Draw the circle by drawing an oval in a square bounding box
        graphics.drawLine(x1,y1,x2,y2);

        // Shift the shape back to its original location
        move(xOffset, yOffset);
    }
}
