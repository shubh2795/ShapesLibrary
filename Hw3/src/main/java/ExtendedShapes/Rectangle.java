package ExtendedShapes;

import shapes.*;
import shapes.Point;

import java.awt.*;

public class Rectangle implements Shapes {

        private double width;
        private double length;
        private Line line1;
        private Line line2;
        private Line line3;
        private Line line4;
        private Point point1,point2,point3,point4;
        /**
         * Constructor based on x-y Locations of points of Rectangle
         * @param x1                The x-location of first point -- must be a valid double.
         * @param y1                The y-location of first point -- must be a valid double.
         * @param x2                The x-location of second point -- must be a valid double.
         * @param y2                The y-location of second point -- must be a valid double.
         * @param x3                The y-location of second point -- must be a valid double.
         * @param y3                The y-location of second point -- must be a valid double.
         * @param x4                The y-location of second point -- must be a valid double.
         * @param y4                The y-location of second point -- must be a valid double.
         * @throws ShapeException   Exception throw if any parameter is invalid
         */

        public Rectangle(double x1,double y1,double x2,double y2,double x3,double y3,double x4,double y4) throws ShapeException
        {
                point1 = new Point(x1,y1);
                point2 = new Point(x2,y2);
                point3 = new Point(x3,y3);
                point4 = new Point(x4,y4);
                line1 = new Line(point1,point2);
                line2 = new Line(point2,point3);
                line3 = new Line(point3,point4);
                line4 = new Line(point4,point1);
                this.length = line1.computeLength();
                this.width = line2.computeLength();
        /*
        condition to check lines are perpendicular to each other
         */
                if(point1.getX() != point2.getX() && point2.getY() != point3.getY() && point3.getX() != point4.getX() && point4.getY() != point1.getY())
                        if(line1.computeSlope()*line2.computeSlope() != -1 || line2.computeSlope()*line3.computeSlope() != -1 || line3.computeSlope()*line4.computeSlope() != -1 || line1.computeSlope()*line4.computeSlope() != -1)
                                throw new ShapeException("Adjacent lines must be perpendicular to each other");

        }
        /*
         * Constructs a rectangle with given 4 lines of rectangle
         * @param line1                 First line of rectangle -- must contain a valid Line reference
         * @param line2                 Second line of rectangle -- must contain a valid Line reference
         * @param line3                 Third line of rectangle -- must contain a valid Line reference
         * @param line4                 Fourth line of rectangle -- must contain a valid Line reference
         */
        public Rectangle(Line line1, Line line2, Line line3, Line line4) throws ShapeException
        {
                if(line1==null||line2==null||line3==null||line4==null)
                        throw new ShapeException("Invalid Point");
                this.line1 = line1;
                this.line2 = line2;
                this.line3 = line3;
                this.line4 = line4;
                this.point1 = line1.getPoint1();
                this.point2 = line2.getPoint1();
                this.point3 = line3.getPoint1();
                this.point4 = line4.getPoint1();
                this.length = line1.computeLength();
                this.width = line2.computeLength();
        /*
        condition to check lines are perpendicular to each other
         */
                if(point1.getX() != point2.getX() && point2.getY() != point3.getY() && point3.getX() != point4.getX() && point4.getY() != point1.getY())
                        if(line1.computeSlope()*line2.computeSlope() != -1 || line2.computeSlope()*line3.computeSlope() != -1 || line3.computeSlope()*line4.computeSlope() != -1 || line1.computeSlope()*line4.computeSlope() != -1)
                                throw new ShapeException("Adjacent lines must be perpendicular to each other");


        }
        /*
         * Constructs a rectangle with given length and width of rectangle
         * @param length               length of rectangle -- must be a valid double
         * @param width                Width of rectangle -- must be a valid double
         */
        public Rectangle(double length, double width) throws ShapeException
        {
                if(width <= 0 || length <= 0)
                        throw new ShapeException("Invalid side");
                this.width = width;
                this.length = length;
        }

        @Override
        public double computeArea()
        {
                return length*width;
        }

        /*
         * getVertices function returns all four vertices of rectangle
         */
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

        /*
         * getWidth function returns width of rectangle
         */
        public double getWidth()
        {
                return width;
        }
        /*
         * getLength function returns length of rectangle
         */
        public double getLength()
        {
                return length;
        }
        /**
         * Move a rectangle
         *
         * @param deltaX            The delta x-location by which the line should be moved -- must be a valid double
         * @param deltaY            The delta y-location by which the line should be moved -- must be a valid double
         * @throws ShapeException   Exception throw if any parameter is invalid
         */
        @Override
        public void move(double deltaX,double deltaY)throws ShapeException{
                point1.move(deltaX,deltaY);
                point2.move(deltaX,deltaY);
                point3.move(deltaX,deltaY);
                point4.move(deltaX,deltaY);
        }

        //TODO: Modify the scale method at the moment just addding to remove errors
        @Override
        public void scale(double scaleFactor) throws ShapeException {
                Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");

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
        public void render(Graphics graphics, int xOffset, int yOffset) throws ShapeException {

                // Shift the shape by the specified rendering offset
                move(-xOffset, -yOffset);

                // Compute the left side of the bounding box
                int x = (int) Math.round(point1.getX() - getLength());

                // Compute the top side of the bounding box
                int y = (int) Math.round(point1.getY() - getWidth());

                // Compute the width of the bounding box
                int width = (int) getWidth();

                int [] xPoints = {(int)point1.getX(), (int)point2.getX(), (int)point3.getX(), (int)point4.getX()};
                int [] yPoints = {(int)point1.getY(), (int)point2.getY(), (int)point3.getY(), (int)point4.getY()};

                // Draw the circle by drawing an oval in a square bounding box
                graphics.drawPolygon(xPoints, yPoints, 4);

                // Shift the shape back to its original location
                move(xOffset, yOffset);
        }
}
