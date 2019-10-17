package ExtendedShapes;

import java.awt.*;

public class Ellipse {


    public double computeArea() {
        double a = width / 2.0;
        double b = height / 2.0;

        return Math.PI * a * b; //exact elliptical area
    }

    public void draw(Graphics g) {
        g.setColor(super.getFillColor());
        g.fillOval(super.getX(), super.getY(), width, height);
        g.setColor(super.getBorderColor());
        g.drawOval(super.getX(), super.getY(), width, height);
    }
}
