package shapes;

public interface Shapes {

    public void draw();
    public void move(double deltaX, double deltaY)throws ShapeException;
    public void scale(double scaleFactor)throws ShapeException;
    public double computeArea();

}