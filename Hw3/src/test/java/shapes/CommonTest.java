package shapes;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;

public class CommonTest {

    @Test
    public void getShapesFromTextFiles() {

        try{
            EmbeddedImage embeddedPicture = new EmbeddedImage(600, 600, 10, 10, "images\\panda.jpg");
            Common.saveShapeToTextFile(embeddedPicture, "textFiles\\embedded.txt");
            Circle myCircle = new Circle(1, 2, 5);
            Common.saveShapeToTextFile(myCircle, "textFiles\\circle.txt");
            Line myLine = new Line(1, 2, 4, 10);
            Common.saveShapeToTextFile(myLine, "textFiles\\line.txt");
            Point p1 = new Point(1,2);
            Common.saveShapeToTextFile(p1, "textFiles\\point.txt");
            Rectangle rectangle = new Rectangle(1, 1,1,0,3,5,8,0);
            Common.saveShapeToTextFile(rectangle, "textFiles\\rectangle.txt");
            Triangle triangle = new Triangle(1, 1, 1, 2, 2, 1);
            Common.saveShapeToTextFile(triangle, "textFiles\\rectangle.txt");
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }
    }

    @Test
    public void saveShapeToTextFile() {
        try {
            File file = new File("C:\\Users\\shubh\\IdeaProjects\\HW3\\Hw3\\textFiles\\embedded.txt");
            Shapes shape = Common.getShapesFromTextFiles(new FileInputStream(file));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}