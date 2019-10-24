package shapes;
import java.awt.*;
import java.io.*;



    public interface Shapes {


        public void render(Graphics graphics)throws ShapeException;
        public void move(double deltaX, double deltaY)throws ShapeException;
        public void scale(double scaleFactor)throws ShapeException;
        public double computeArea()throws ShapeException;
        public String stringToTextFile() ;


    }