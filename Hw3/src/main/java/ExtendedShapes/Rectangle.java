package ExtendedShapes;

import shapes.Point;
import shapes.ShapeException;

import java.awt.*;

public class Rectangle {

        public Rectangle(Point A, Point B, Point C, Point D) throws ShapeException {


        }

        public double computeArea() {
                return width * height; // area of a rectangle
        }

        public void draw(Graphics g) {
//                g.setColor(super.getFillColor());
//                g.fillRect(super.getX(), super.getY(), width, height);
//                g.setColor(super.getBorderColor());
//                g.drawRect(super.getX(), super.getY(), width, height);
        }
}
