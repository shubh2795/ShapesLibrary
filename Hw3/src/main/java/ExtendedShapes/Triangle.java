package ExtendedShapes;
import shapes.*;
import shapes.Point;
import shapes.ShapeException;
import shapes.Validator;

import java.awt.*;

public class Triangle {

        public Triangle(Point A, Point B, Point C) throws ShapeException {

            Line line = new Line(A,B);

        }

        public Triangle(Line A, Line B, Line C) throws ShapeException {


        }
    public double computeArea() {
        return 1.0/2 * sideLength * height; // area of a triangle
    }

    public void draw(Graphics g) {
//
//        // initializing the values of the x coordinates
//        xCoordinates[0] = (int) (this.getX() - sideLength / 2.0);
//        xCoordinates[1] = (int) (this.getX() + sideLength / 2.0);
//        xCoordinates[2] = this.getX();
//
//        // initializing the values of the y coordinates
//        yCoordinates[0] = this.getY() + height;
//        yCoordinates[1] = this.getY() + height;
//        yCoordinates[2] = this.getY();
//
//        g.setColor(super.getFillColor());
//        g.fillPolygon(xCoordinates, yCoordinates, 3);
//        g.setColor(super.getBorderColor());
//        g.drawPolygon(xCoordinates, yCoordinates, 3);
    }
}
