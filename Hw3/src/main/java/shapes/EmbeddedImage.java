package shapes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

 public class EmbeddedImage implements Shapes {

    private double length;
    private double breadth;
    private Point imageLocation;
    private String source;

    public EmbeddedImage(double length, double breadth, double x, double y, String source) throws ShapeException {

        this.length = length;
        this.breadth = breadth;
        imageLocation = new Point(x,y);
        this.source = source;
    }

    public String getSource(){
        return source;
    }

    @Override
    public String toString() {
         return "EmbeddedImage,"+imageLocation.getX()+","+imageLocation.getY()+","+(length)+","+(breadth)+","+(source)+",";
    }

    @Override
    public double computeArea() throws ShapeException {
        return length* breadth;
    }

    @Override
    public void move(double deltaX, double deltaY) throws ShapeException {
        imageLocation.move(deltaX,deltaY);
    }
    
    @Override
    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");

    }

    @Override
    public void render(Graphics graphics) throws ShapeException {


        int x = (int) Math.round(imageLocation.getX() - length);
        int y = (int) Math.round(imageLocation.getY() - breadth);
        File sourceFile = new File(source);
        BufferedImage sourceImage = null;
        try{
            sourceImage = ImageIO.read(sourceFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        graphics.drawImage(sourceImage, 0, 0, (int) breadth, (int)length, null);

    }


}

