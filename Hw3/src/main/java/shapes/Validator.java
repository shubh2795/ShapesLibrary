package shapes;

public class Validator {
    public static void validateDouble(double value, String errorMessage) throws ShapeException {
        if (Double.isInfinite(value) || Double.isNaN(value)){
            throw new ShapeException(errorMessage);
        }
    }

    public static void validatePositiveDouble(double value, String errorMessage) throws ShapeException {
        validateDouble(value, errorMessage);
        if (value<0){
            throw new ShapeException(errorMessage);
        }
    }

    public static void validateTriangleColinearity(Line line1,Line line2,Line line3) throws ShapeException {
        if(line1.computeSlope() == line2.computeSlope() || line2.computeSlope() == line3.computeSlope() || line1.computeSlope() == line3.computeSlope()){
            throw new ShapeException("Sides of a triangle can not be collinear");
        }

    }
    public static void validateRectanglePerpendicularLines(Point point1,Point point2,Point point3,Point point4,Line line1,Line line2,Line line3,Line line4) throws ShapeException {
        if(point1.getX() != point2.getX() && point2.getY() != point3.getY() && point3.getX() != point4.getX() && point4.getY() != point1.getY()){

            if(line1.computeSlope()*line2.computeSlope() != -1 || line2.computeSlope()*line3.computeSlope() != -1 || line3.computeSlope()*line4.computeSlope() != -1 || line1.computeSlope()*line4.computeSlope() != -1) {
                throw new ShapeException("Adjacent lines should be perpendicular to each other");
            }
        }

    }



}
