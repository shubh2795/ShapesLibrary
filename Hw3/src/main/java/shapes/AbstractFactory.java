package shapes;

public abstract class AbstractFactory {
    abstract Shapes getShape(String shapeType) throws ShapeException ;
}