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

    public static void validateTriangleCoordinates(double A,double B,double C, String errorMessage) throws ShapeException {
        validateDouble(A, errorMessage);
        validateDouble(B, errorMessage);
        validateDouble(C, errorMessage);

        validatePositiveDouble(A,errorMessage);
        validatePositiveDouble(B,errorMessage);
        validatePositiveDouble(C,errorMessage);

    }

}
