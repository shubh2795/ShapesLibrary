package shapes;
import java.awt.*;
import java.io.*;



    public interface Shapes {


        public void render(Graphics graphics)throws ShapeException;
        public void move(double deltaX, double deltaY)throws ShapeException;
        public void scale(double scaleFactor)throws ShapeException;
        public double computeArea()throws ShapeException;
        public String toString()throws ShapeException;

        default void saveShape(String fileName, Shapes shape){
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

         static Shapes load(FileInputStream fileInputStream){
             try {

                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                 String[] retrievedData = null;
                 String text = null;
                 String fullString = "";

                 while ((text = bufferedReader.readLine())!=null) {
                     fullString = fullString.concat(text).concat("\n");
                 }

                 retrievedData = fullString.split(",");

                 AllShapesEnum.allShapes shape = AllShapesEnum.allShapes.valueOf(retrievedData[0]);

                 switch (shape) {
                     case Line:
                         return new Line(Double.parseDouble(retrievedData[1]), Double.parseDouble(retrievedData[2]), Double.parseDouble(retrievedData[3]), Double.parseDouble(retrievedData[4]));

                     case Point:
                         return new Point(Double.parseDouble(retrievedData[1]), Double.parseDouble(retrievedData[2]));

                     case Circle:
                         return new Circle(Double.parseDouble(retrievedData[1]), Double.parseDouble(retrievedData[2]), Double.parseDouble(retrievedData[3]));

                     case Rectangle:
                         return new Rectangle(Double.parseDouble(retrievedData[1]), Double.parseDouble(retrievedData[2]), Double.parseDouble(retrievedData[3]), Double.parseDouble(retrievedData[4]), Double.parseDouble(retrievedData[5]), Double.parseDouble(retrievedData[6]), Double.parseDouble(retrievedData[7]), Double.parseDouble(retrievedData[8]));

                     case Triangle:
                         return new Triangle(Double.parseDouble(retrievedData[1]), Double.parseDouble(retrievedData[2]), Double.parseDouble(retrievedData[3]), Double.parseDouble(retrievedData[4]), Double.parseDouble(retrievedData[5]), Double.parseDouble(retrievedData[6]));

                     case EmbeddedImage:
                         return new EmbeddedImage(Double.parseDouble(retrievedData[1]), Double.parseDouble(retrievedData[2]), Double.parseDouble(retrievedData[3]), Double.parseDouble(retrievedData[4]), retrievedData[5]);

                     case CompositeShape:
                         return new CompositeShape(retrievedData);
                 }
             }
             catch(Exception e){
                 System.out.println("Exception "+ e);
             }
             return null;
         }



    }