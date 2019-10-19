package shapes;

public class Validator {
    public static void validateDouble(double value, String errorMessage) throws ShapeException {
        if (Double.isInfinite(value) || Double.isNaN(value))
            throw new ShapeException(errorMessage);
    }

    public static void validatePositiveDouble(double value, String errorMessage) throws ShapeException {
        validateDouble(value, errorMessage);
        if (value<0)
            throw new ShapeException(errorMessage);
    }

    public static void validateTriangleColinearity(Line line1,Line line2,Line line3) throws ShapeException {
        if(line1.computeSlope() == line2.computeSlope() || line2.computeSlope() == line3.computeSlope() || line1.computeSlope() == line3.computeSlope()){
            throw new ShapeException("Sides of a triangle can not be collinear");
        }

    }

}
