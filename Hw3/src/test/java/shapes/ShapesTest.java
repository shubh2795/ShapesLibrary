package shapes;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;

public class ShapesTest {

    @Test
    public void saveShape() {
        try{
            EmbeddedImage embeddedPicture = new EmbeddedImage(600, 600, 10, 10, "forrest-gump.jpg");
            embeddedPicture.saveShape("embedded.txt", embeddedPicture);
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }
    }

    @Test
    public void load() {
        try {
            File file = new File("C:\\Users\\shubh\\IdeaProjects\\HW3\\Hw3\\textFiles\\embedded.txt");
            Shapes shape = Shapes.load(new FileInputStream(file));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}