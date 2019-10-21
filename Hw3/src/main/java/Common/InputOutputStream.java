package Common;
import shapes.*;
import shapes.Point;
import shapes.Rectangle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

    public class InputOutputStream {

            public static Shapes loadShape(FileInputStream fileInputStream){
                try {
                System.out.println("Loading Shape...");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String[] retrievedData = null;
                String text = null;
                String fullString = "";

                while ((text = bufferedReader.readLine())!=null) {
                    fullString = fullString.concat(text).concat("\n");
                }

                System.out.println("full string is "+fullString);

                retrievedData = fullString.split(",");
                String first = retrievedData[0];

                // TODO: Remove all the duplicate code

                if (first.equals(AllShapesEnum.allShapes.Point)) {
                    System.out.println(retrievedData[0]);
                    return new Point(Double.parseDouble(retrievedData[1]), Double.parseDouble(retrievedData[2]));
                }

                if (first.equals(AllShapesEnum.allShapes.Line)) {
                    System.out.println(retrievedData[0]);
                    return new Line(Double.parseDouble(retrievedData[1]), Double.parseDouble(retrievedData[2]), Double.parseDouble(retrievedData[3]), Double.parseDouble(retrievedData[4]));
                }

                if (first.equals(AllShapesEnum.allShapes.Circle)) {
                    System.out.println(retrievedData[1]);
                    return new Circle(Double.parseDouble(retrievedData[1]), Double.parseDouble(retrievedData[2]), Double.parseDouble(retrievedData[3]));
                }

                if (first.equals(AllShapesEnum.allShapes.Triangle)) {
                    System.out.println(retrievedData[0]);
                    return new Triangle(Double.parseDouble(retrievedData[1]), Double.parseDouble(retrievedData[2]), Double.parseDouble(retrievedData[3]), Double.parseDouble(retrievedData[4]), Double.parseDouble(retrievedData[5]), Double.parseDouble(retrievedData[6]));
                }

                if (first.equals(AllShapesEnum.allShapes.Rectangle)) {
                    System.out.println(retrievedData[0]);
                    return new Rectangle(Double.parseDouble(retrievedData[1]), Double.parseDouble(retrievedData[2]), Double.parseDouble(retrievedData[3]), Double.parseDouble(retrievedData[4]), Double.parseDouble(retrievedData[5]), Double.parseDouble(retrievedData[6]), Double.parseDouble(retrievedData[7]), Double.parseDouble(retrievedData[8]));
                }

                if (first.equals(AllShapesEnum.allShapes.CompositeShape)) {
                    return new CompositeShape(retrievedData);
                }
                if(first.equals(AllShapesEnum.allShapes.EmbeddedImage)){
                    return new EmbeddedImage(Double.parseDouble(retrievedData[1]), Double.parseDouble(retrievedData[2]), Double.parseDouble(retrievedData[3]), Double.parseDouble(retrievedData[4]), retrievedData[5]);
                }
            }
            catch(Exception e){
                System.out.println("Exception "+ e);
            }
                return null;
            }

            public static void drawImageOnExternalFile(Shapes shape,Color backgroundColor,Color shapeColor)throws ShapeException{

                try {
                    BufferedImage bImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics = bImg.createGraphics();
                    graphics.setColor(Color.BLACK);
                    graphics.fillRect(0, 0, 100, 100);
                    graphics.setColor(Color.BLACK);
                    shape.render(graphics);
                    ImageIO.write(bImg, "jpg", new File("Composite.jpg"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public static void saveShape(String fileName, Shapes shape) {
                try{
                    File file = new File(fileName);
                    if(!file.exists())
                    {
                        file.createNewFile();
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                        System.out.println("saveShape method");
                        bufferedWriter.write(shape.toString());
                        bufferedWriter.close();
                    }

                }catch(Exception e){
                    System.out.println("Exception "+ e);
                }
            }
}
