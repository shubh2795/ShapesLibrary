package shapes;

import java.io.*;

public class Common {


    public static Shapes getShapesFromTextFiles(FileInputStream fileInputStream){
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

    public static void saveShapeToTextFile(Shapes shapes, String fileName){
        try{
            File file = new File(fileName);
            if(!file.exists())
            {
                file.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(shapes.stringToTextFile());
                bufferedWriter.close();
            }

        }catch(Exception e){
            System.out.println("Exception "+ e);
        }
    }
}
