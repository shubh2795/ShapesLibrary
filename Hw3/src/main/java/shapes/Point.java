package shapes;

/**
 * Point
 *
 * This class represents point objects that can be moved and copied
 */

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


    public void move(double deltaX, double deltaY) throws ShapeException {
        moveX(deltaX);
        moveY(deltaY);
    }


    public Point copy() throws ShapeException {
        return new Point(x, y);
    }

    @Override
    public void draw() {

    }

    @Override
    public void scale(double scaleFactor) throws ShapeException {

    }
    @Override
    public double computeArea() {
    return 0;
    }
}
