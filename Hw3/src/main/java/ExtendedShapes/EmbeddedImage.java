package ExtendedShapes;
import shapes.Point;
import shapes.ShapeException;
import shapes.Shapes;
import shapes.Validator;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;



public class EmbeddedImage implements Shapes {

    private double length;
    private double width;
    private Point imageLocation;
    private String source;


    public EmbeddedImage(double length, double width, double x, double y, String source) throws ShapeException {

        this.length = length;
        this.width = width;
        imageLocation = new Point(x,y);
        this.source = source;
    }
    public String getSource(){
        return source;
    }

    @Override
    public double computeArea() throws ShapeException {
        return length*width;
    }

    @Override
    public void move(double deltaX, double deltaY) throws ShapeException {
        imageLocation.move(deltaX,deltaY);
    }

    //TODO: Modify the scale method at the moment just addding to remove errors
    @Override
    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");

    }
    @Override
    public void render(Graphics graphics, int xOffset, int yOffset) throws ShapeException {
        // Shift the shape by the specified rendering offset
        move(-xOffset, -yOffset);

        // Compute the left side of the bounding box
        int x = (int) Math.round(imageLocation.getX() - length);

        // Compute the top side of the bounding box
        int y = (int) Math.round(imageLocation.getY() - width);

        File sourceFile = new File(source);
        BufferedImage sourceImage = null;
        try{
            sourceImage = ImageIO.read(sourceFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        graphics.drawImage(sourceImage, 0, 0, (int)width, (int)length, null);
        // Shift the shape back to its original location
        move(xOffset, yOffset);
    }


}

