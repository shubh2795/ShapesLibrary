package shapes;
import java.awt.*;

public class Triangle implements Shapes {

    private Line line1;
    private Line line2;
    private Line line3;
    private Point point1, point2, point3;


    public Triangle(double x1,double y1,double x2,double y2,double x3,double y3) throws ShapeException {
        point1 = new Point(x1,y1);
        point2 = new Point(x2,y2);
        point3 = new Point(x3,y3);
        line1 = new Line(point1,point2);
        line2 = new Line(point2,point3);
        line3 = new Line(point1,point3);


        //Collinearity check
        Validator.validateTriangleColinearity(line1,line2,line3);
    }

    public Triangle(Line line1, Line line2, Line line3) throws ShapeException {

        if (line1 == null || line2 == null || line3 == null){
            throw new ShapeException("Invalid Point");
        }
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;

        //Collinearity check
        Validator.validateTriangleColinearity(line1,line2,line3);
    }

    public double getPerimeter(){
        return line1.computeLength() + line2.computeLength() + line3.computeLength();
    }

    public void move(double deltaX,double deltaY)throws ShapeException{
        point1.move(deltaX,deltaY);
        point2.move(deltaX,deltaY);
        point3.move(deltaX,deltaY);
    }

    public Line getLine1() {
        return line1;
    }

    public Line getLine2() {
        return line2;
    }

    public Line getLine3() {
        return line3;
    }

    @Override
    public String toString() {
        return "Triangle,"+String.valueOf(point1.getX())+","+String.valueOf(point1.getY())+","+String.valueOf(point2.getX())+","+String.valueOf(point2.getY())+","+String.valueOf(point3.getX())+","+String.valueOf(point3.getY())+",";
    }


    @Override
    public double computeArea() {
        //Area of a triangle = √s(s−a)(s−b)(s−c) where s is perimeter/2
        double p = getPerimeter() / 2;
        return Math.sqrt(p * ((p - line1.computeLength()) * (p - line2.computeLength()) * (p - line3.computeLength())));
    }

   @Override
    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");
        line1.scale(scaleFactor);
        line3.scale(scaleFactor);
    }

    @Override
    public void render(Graphics graphics) throws ShapeException {


        int x = (int) Math.round(point1.getX() - line1.computeLength());
        int y = (int) Math.round(point3.getY() - line3.computeLength());
        int width = (int) line2.computeLength();
        int [] xPoints = {(int)point1.getX(), (int)point2.getX(), (int)point3.getX()};
        int [] yPoints = {(int)point1.getY(), (int)point2.getY(), (int)point3.getY()};
        graphics.drawPolygon(xPoints, yPoints, 3);

    }
}
