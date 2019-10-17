package ExtendedShapes;
import shapes.Circle;
import shapes.CompositeShape;
import java.awt.*;
import java.io.*;

    public class InputOutputStream {

            public static Shape load(FileInputStream fileInputStream) {
                try {
                System.out.println("opening");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String[] broken_text = null;
                String text = null;
                String fullString = "";

                while ((text = bufferedReader.readLine())!=null) {
                    fullString = fullString.concat(text).concat("\n");
                }

                System.out.println("full string is "+fullString);

                broken_text = fullString.split(",");
                String first = broken_text[0];

                // TODO: Remove all the duplicate code

                if (first.equals("Circle")) {
                    System.out.println("if condition");
                    System.out.println(broken_text[1]);
                    return new Circle(Double.parseDouble(broken_text[1]), Double.parseDouble(broken_text[2]), Double.parseDouble(broken_text[3]));
                }
                if (first.equals("Ellipse")) {
                    System.out.println(broken_text[0]);
                    return new Ellipse(Double.parseDouble(broken_text[1]), Double.parseDouble(broken_text[2]), Double.parseDouble(broken_text[3]), Double.parseDouble(broken_text[4]), Double.parseDouble(broken_text[5]), Double.parseDouble(broken_text[6]), Double.parseDouble(broken_text[7]), Double.parseDouble(broken_text[8]));
                }
                if (first.equals("Line")) {
                    System.out.println(broken_text[0]);
                    return new Line(Double.parseDouble(broken_text[1]), Double.parseDouble(broken_text[2]), Double.parseDouble(broken_text[3]), Double.parseDouble(broken_text[4]));
                }
                if (first.equals("Point")) {
                    System.out.println(broken_text[0]);
                    return new Point(Double.parseDouble(broken_text[1]), Double.parseDouble(broken_text[2]));
                }
                if (first.equals("Rectangle")) {
                    System.out.println(broken_text[0]);
                    return new Rectangle(Double.parseDouble(broken_text[1]), Double.parseDouble(broken_text[2]), Double.parseDouble(broken_text[3]), Double.parseDouble(broken_text[4]), Double.parseDouble(broken_text[5]), Double.parseDouble(broken_text[6]), Double.parseDouble(broken_text[7]), Double.parseDouble(broken_text[8]));
                }
                if (first.equals("Square")) {
                    System.out.println(broken_text[0]);
                    return new Rectangle(Double.parseDouble(broken_text[1]), Double.parseDouble(broken_text[2]), Double.parseDouble(broken_text[3]), Double.parseDouble(broken_text[4]), Double.parseDouble(broken_text[5]), Double.parseDouble(broken_text[6]), Double.parseDouble(broken_text[7]), Double.parseDouble(broken_text[8]));
                }
                if (first.equals("Triangle")) {
                    System.out.println(broken_text[0]);
                    return new Triangle(Double.parseDouble(broken_text[1]), Double.parseDouble(broken_text[2]), Double.parseDouble(broken_text[3]), Double.parseDouble(broken_text[4]), Double.parseDouble(broken_text[5]), Double.parseDouble(broken_text[6]));
                }
                if (first.equals("Compositeshape")) {
                    return new CompositeShape(broken_text);
                }
                if(first.equals("EmbeddedPicture")){
                    return new EmbeddedPicture(Double.parseDouble(broken_text[1]), Double.parseDouble(broken_text[2]), Double.parseDouble(broken_text[3]), Double.parseDouble(broken_text[4]), broken_text[5]);
                }
            }
                    catch(Exception e)
            {
                System.out.println("Exception "+ e);
            }
                return null;
            }


            public static void saveShape(String fileName, Shape shape) {
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
