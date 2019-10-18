package shapes;
import ExtendedShapes.Ellipse;
import ExtendedShapes.EmbeddedImage;
import ExtendedShapes.Triangle;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

    public class CompositeShape implements Shapes {
    //collection of Shapes
    private List<Shape> shapes;

        public CompositeShape()
        {
            shapes = new ArrayList<Shape>();
        }

        public List<Shape> getShapes() {
            return shapes;
        }

        public CompositeShape(String [] broke)
        {
//        for(int j = 0;j<=broke.length;j++)
//        {
//            System.out.println(broke[j]);
//        }
            try{
                shapes = new ArrayList<Shape>();
                for(int i=1; i<=broke.length; i++)
                {
                    if(broke[i].equals("\nCircle"))
                    {
                        System.out.println("circle add");
                        shapes.add(new Circle(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2]), Double.parseDouble(broke[i+3])));
                        //i = i+4;
                        //System.out.println(i);
                        //System.out.println(broke[i]);
                    }

                    if(broke[i].equals("\nTriangle"))
                    {
                        System.out.println("triangle add");
                        shapes.add(new Triangle(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2]), Double.parseDouble(broke[i+3]), Double.parseDouble(broke[i+4]),Double.parseDouble(broke[i+5]), Double.parseDouble(broke[i+6])));
                        //i=i+7;
                    }
                    if(broke[i].equals("\nEllipse"))
                    {
                        shapes.add(new Ellipse(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2]), Double.parseDouble(broke[i+3]),Double.parseDouble(broke[i+4]),Double.parseDouble(broke[i+5]),Double.parseDouble(broke[i+6]),Double.parseDouble(broke[i+7]),Double.parseDouble(broke[i+8])));
                        //i=i+9;
                    }
                    if(broke[i].equals("\nRectangle"))
                    {
                        System.out.println("rectangle add");
                        shapes.add(new Rectangle(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2]), Double.parseDouble(broke[i+3]), Double.parseDouble(broke[i+4]), Double.parseDouble(broke[i+5]), Double.parseDouble(broke[i+6]), Double.parseDouble(broke[i+7]), Double.parseDouble(broke[i+8])));
                        //i=i+9;
                    }
                    if(broke[i].equals("\nLine"))
                    {
                        System.out.println("line add");
                        shapes.add(new Line(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2]), Double.parseDouble(broke[i+3]), Double.parseDouble(broke[i+4])));
                        //i=i+4;
                    }
                    if(broke[i].equals("\nPoint"))
                    {
                        shapes.add(new Point(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2])));
                        //i=i+3;
                    }
                    if(broke[i].equals("\nCompositeshape"))
                    {
                        System.out.println("compositeShape add");
                        while(!(broke[i].equals("\n/Compositeshape")))
                        {
                            // System.out.println("second loop");
                            String packetString = "";
                            String [] broke2 = null;
                            while(!(broke[i].equals("\n/Compositeshape")))
                            {
                                //System.out.println("third loop");
                                packetString = packetString.concat(broke[i]);
                                packetString = packetString.concat(",");
                                //System.out.println(packetString);
                                i++;
                            }
                            //System.out.println(packetString);
                            broke2 = packetString.split(",");
                            //System.out.println("packet string is :");
//                    for(int j = 0; j<broke2.length; j++)
//                        System.out.print(broke2[j]);
                            CompositeShape compositeShape = new CompositeShape(broke2);
                            shapes.add(compositeShape);
                        }

                    }
                    if(broke[i].equals("\nEmbeddedPicture"))
                    {
                        shapes.add(new EmbeddedImage(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2]), Double.parseDouble(broke[i+3]), Double.parseDouble(broke[i+4]), broke[i+5]));
                        //i=i+3;
                    }

                    //System.out.println("size is :" +shapes.size());
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public void addShape(Shape shape){
            shapes.add(shape);
        }
        public void removeShape(Shape shape)
        {
            shapes.remove(shape);
        }
        public void removeAllShapes()
        {
            shapes.clear();
        }

        @Override
        public double computeArea() throws ShapeException {
            double areaSum = 0;
            for(Shape s : shapes)
            {
                areaSum = areaSum + s.computeArea();
            }
            return areaSum;
        }

        @Override
        public void move(double deltaX, double deltaY) throws ShapeException {
            for(Shape s : shapes)
            {
                s.move(deltaX,deltaY);
            }
        }
        public File makeFile(String sampleFile) {
            try {
                File file = new File(sampleFile);
                if (!file.exists()) {
                    file.createNewFile();
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    bufferedWriter.newLine();
                    for (Shape s : shapes) {
                        System.out.println(s.getClass().getName());
                        System.out.println("for loop");
                        if (!(s.getClass().getName().equals("examples.shapes.CompositeShape"))) {
                            bufferedWriter.write(s.toString());
                            bufferedWriter.newLine();
                        }
                        else{
                            bufferedWriter.write("Compositeshape,");
                            bufferedWriter.newLine();
                            for(int i = 0; i<s.getShapes().size(); i++)
                            {
                                bufferedWriter.write(s.getShapes().get(i).toString());
                                bufferedWriter.newLine();
                            }
                            bufferedWriter.write("/Compositeshape,");
                            bufferedWriter.newLine();
                        }
                    }
                    bufferedWriter.close();
                    return file;
                }
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        public String toString() {
            try {
                File newFile = makeFile("sample.txt");
                String text = new String(Files.readAllBytes(Paths.get("sample.txt")), StandardCharsets.UTF_8);
                return "Compositeshape,"+text;
            }catch(Exception e){

            }
            return null;
        }

        //TODO: Modify the scale method at the moment just addding to remove errors
        @Override
        public void scale(double scaleFactor) throws ShapeException {
            Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");

        }

        @Override
        public void render(Graphics graphics, int xOffset, int yOffset) throws ShapeException {
            for(Shape s : shapes)
            {
                s.render(graphics,xOffset,yOffset);
            }
        }

    }
