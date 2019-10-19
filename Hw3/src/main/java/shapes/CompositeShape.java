package shapes;
import ExtendedShapes.Ellipse;
import ExtendedShapes.EmbeddedImage;
import ExtendedShapes.Rectangle;
import ExtendedShapes.Triangle;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

    public class CompositeShape implements Shapes {
        //collection of Shapes
        private List<Shapes> shapes = new ArrayList<Shapes>();

        public void addShape(Shapes s){
            this.shapes.add(s);
        }

        public void removeShape(Shapes s){
            shapes.remove(s);
        }

        public void removeAllShapes(){
            System.out.println("Clearing all the shapes from drawing");
            this.shapes.clear();
        }

        //TODO:: refactor
        public CompositeShape(String [] broke) {

            try{

                for(int i=0; i<broke.length; i++)
                {
                    if(broke[i].equals("\nPoint")){
                        shapes.add(new Point(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2])));
                    }

                    if(broke[i].equals("\nLine")) {
                        System.out.println("line addShape");
                        shapes.add(new Line(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2]), Double.parseDouble(broke[i+3]), Double.parseDouble(broke[i+4])));
                    }

                    if(broke[i].equals("\nCircle")){
                        System.out.println("circle addShape");
                        shapes.add(new Circle(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2]), Double.parseDouble(broke[i+3])));
                    }

                    if(broke[i].equals("\nTriangle")){
                        System.out.println("triangle addShape");
                        shapes.add(new Triangle(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2]), Double.parseDouble(broke[i+3]), Double.parseDouble(broke[i+4]),Double.parseDouble(broke[i+5]), Double.parseDouble(broke[i+6])));
                    }

                    if(broke[i].equals("\nRectangle")){
                        System.out.println("rectangle addShape");
                        shapes.add(new Rectangle(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2]), Double.parseDouble(broke[i+3]), Double.parseDouble(broke[i+4]), Double.parseDouble(broke[i+5]), Double.parseDouble(broke[i+6]), Double.parseDouble(broke[i+7]), Double.parseDouble(broke[i+8])));
                    }

                    if(broke[i].equals("\nEllipse")){
                        shapes.add(new Ellipse(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2]), Double.parseDouble(broke[i+3]),Double.parseDouble(broke[i+4]),Double.parseDouble(broke[i+5]),Double.parseDouble(broke[i+6]),Double.parseDouble(broke[i+7]),Double.parseDouble(broke[i+8])));
                    }

                    if(broke[i].equals("\nCompositeshape")){
                        System.out.println("compositeShape addShape");
                        while(!(broke[i].equals("\n/Compositeshape")))
                        {

                            String packetString = "";
                            String [] broke2 = null;
                            while(!(broke[i].equals("\n/Compositeshape")))
                            {
                                packetString = packetString.concat(broke[i]);
                                packetString = packetString.concat(",");
                                i++;
                            }
                            broke2 = packetString.split(",");
                            CompositeShape compositeShape = new CompositeShape(broke2);
                            shapes.add(compositeShape);
                        }
                    }
                    if(broke[i].equals("\nEmbeddedPicture")){
                        shapes.add(new EmbeddedImage(Double.parseDouble(broke[i+1]), Double.parseDouble(broke[i+2]), Double.parseDouble(broke[i+3]), Double.parseDouble(broke[i+4]), broke[i+5]));
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public File makeFile(String sampleFile) {
            try {
                File file = new File(sampleFile);
                if (!file.exists()) {
                    file.createNewFile();
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    bufferedWriter.newLine();
                    for (Shapes s : shapes) {
                        System.out.println(s.getClass().getName());
                        System.out.println("for loop");
                        if (!(s.getClass().getName().equals("examples.shapes.CompositeShape"))) {
                            bufferedWriter.write(s.toString());
                            bufferedWriter.newLine();
                        }
                        else{
                            bufferedWriter.write("Compositeshape,");
                            bufferedWriter.newLine();
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
        public double computeArea() throws ShapeException {
            double areaSum = 0;
            for(Shapes s : shapes)
            {
                areaSum = areaSum + s.computeArea();
            }
            return areaSum;
        }

        @Override
        public void move(double deltaX, double deltaY) throws ShapeException {
            for(Shapes s : shapes)
            {
                s.move(deltaX,deltaY);
            }
        }

        //TODO: Modify the scale method at the moment just addding to remove errors
        @Override
        public void scale(double scaleFactor) throws ShapeException {
            Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");

        }

        @Override
        public void render(Graphics graphics, int xOffset, int yOffset) throws ShapeException {
            for(Shapes s : shapes)
            {
                s.render(graphics,xOffset,yOffset);
            }
        }

    }
