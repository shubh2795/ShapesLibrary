package shapes;
import java.awt.*;

public interface Shapes {

    public void render(Graphics graphics, int xOffset, int yOffset)throws ShapeException;
    public void move(double deltaX, double deltaY)throws ShapeException;
    public void scale(double scaleFactor)throws ShapeException;
    public double computeArea()throws ShapeException;

}