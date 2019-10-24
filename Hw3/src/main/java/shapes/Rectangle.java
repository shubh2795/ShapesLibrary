package shapes;
import java.awt.*;

public class Rectangle implements Shapes {

        private double breadth;
        private double length;
        private Line line1;
        private Line line2;
        private Line line3;
        private Line line4;
        private Point point1,point2,point3,point4;

        public Rectangle(double x1,double y1,double x2,double y2,double x3,double y3,double x4,double y4) throws ShapeException {
                point1 = new Point(x1,y1);
                point2 = new Point(x2,y2);
                point3 = new Point(x3,y3);
                point4 = new Point(x4,y4);
                line1 = new Line(point1,point2);
                line2 = new Line(point2,point3);
                line3 = new Line(point3,point4);
                line4 = new Line(point1,point4);
                this.length = line1.computeLength();
                this.breadth = line2.computeLength();

                //perpendicular check
                Validator.validateRectanglePerpendicularLines(point1,point2,point3,point4,line1,line2,line3,line4);
        }

        public Rectangle(Line line1, Line line2, Line line3, Line line4) throws ShapeException {
                if(line1==null||line2==null||line3==null||line4==null){
                        throw new ShapeException("Invalid Point");
                }

                this.line1 = line1;
                this.line2 = line2;
                this.line3 = line3;
                this.line4 = line4;
                this.point1 = line1.getPoint1();
                this.point2 = line2.getPoint1();
                this.point3 = line3.getPoint1();
                this.point4 = line4.getPoint1();
                this.length = line1.computeLength();
                this.breadth = line2.computeLength();

                //perpendicular check
                Validator.validateRectanglePerpendicularLines(point1,point2,point3,point4,line1,line2,line3,line4);

        }

        public Rectangle(double length, double breadth) throws ShapeException {
                if(breadth <= 0 || length <= 0){
                        throw new ShapeException("Invalid side");
                }

                this.breadth = breadth;
                this.length = length;
        }

        @Override
        public double computeArea()
        {
                return length* breadth;
        }

        public Point getPoint1()
        {
                return point1;
        }

        public Point getPoint2()
        {
                return point2;
        }

        public Point getPoint3()
        {
                return point3;
        }

        public Point getPoint4()
        {
                return point4;
        }

        public double getBreadth() {
                return breadth;
        }

        public double getLength()
        {
                return length;
        }

        @Override
        public String stringToTextFile() {
                return "Rectangle,"+(point1.getX())+","+(point1.getY())+","+(point2.getX())+","+(point2.getY())+","+(point3.getX())+","+(point3.getY())+","+(point4.getX())+","+(point4.getY())+",";
        }

        @Override
        public void move(double deltaX,double deltaY)throws ShapeException{
                point1.move(deltaX,deltaY);
                point2.move(deltaX,deltaY);
                point3.move(deltaX,deltaY);
                point4.move(deltaX,deltaY);
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

        public Line getLine4() {
                return line4;
        }


        @Override
        public void scale(double scaleFactor) throws ShapeException {
                Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");
                line1.scale(scaleFactor);
                line4.scale(scaleFactor);
                Line diagonal = new Line(point1,point3);
                diagonal.scale(scaleFactor);
        }

        @Override
        public void render(Graphics graphics) throws ShapeException {


                int x = (int) Math.round(point1.getX() - getLength());
                int y = (int) Math.round(point1.getY() - getBreadth());
                int width = (int) getBreadth();
                int [] xPoints = {(int)point1.getX(), (int)point2.getX(), (int)point3.getX(), (int)point4.getX()};
                int [] yPoints = {(int)point1.getY(), (int)point2.getY(), (int)point3.getY(), (int)point4.getY()};
                graphics.drawPolygon(xPoints, yPoints, 4);

        }
}
