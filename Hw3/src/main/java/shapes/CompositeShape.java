package shapes;
import java.util.ArrayList;
import java.util.List;

    public class CompositeShape implements Shapes {
    //collection of Shapes
    private List<Shapes> shapes = new ArrayList<Shapes>();

    @Override
    public void render() {}

    //adding shape to drawing
    public void add(Shapes s){
        this.shapes.add(s);
    }

    //removing shape from drawing
    public void remove(Shapes s){
        shapes.remove(s);
    }
    @Override
    public void move(double deltaX, double deltaY) throws ShapeException {
        //shape.move(deltaX, deltaY);
        //shape.move(deltaX, deltaY);
    }
    @Override
    public double computeArea() {
        return 0;
    }

    @Override
    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");
        //double a = computeLength();
    }
}
