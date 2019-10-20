package shapes;
import Common.AllShapesEnum;

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
        public CompositeShape(String [] data){

            try{

                for(int i=0; i<data.length; i++) {

                    if(data[i].equals(AllShapesEnum.allShapes.Point)){
                        shapes.add(new Point(Double.parseDouble(data[i+1]), Double.parseDouble(data[i+2])));
                    }

                    if(data[i].equals(AllShapesEnum.allShapes.Line)) {
                        System.out.println("line addShape");
                        shapes.add(new Line(Double.parseDouble(data[i+1]), Double.parseDouble(data[i+2]), Double.parseDouble(data[i+3]), Double.parseDouble(data[i+4])));
                    }

                    if(data[i].equals(AllShapesEnum.allShapes.Circle)){
                        System.out.println("circle addShape");
                        shapes.add(new Circle(Double.parseDouble(data[i+1]), Double.parseDouble(data[i+2]), Double.parseDouble(data[i+3])));
                    }

                    if(data[i].equals(AllShapesEnum.allShapes.Triangle)){
                        System.out.println("triangle addShape");
                        shapes.add(new Triangle(Double.parseDouble(data[i+1]), Double.parseDouble(data[i+2]), Double.parseDouble(data[i+3]), Double.parseDouble(data[i+4]),Double.parseDouble(data[i+5]), Double.parseDouble(data[i+6])));
                    }

                    if(data[i].equals(AllShapesEnum.allShapes.Rectangle)){
                        System.out.println("rectangle addShape");
                        shapes.add(new Rectangle(Double.parseDouble(data[i+1]), Double.parseDouble(data[i+2]), Double.parseDouble(data[i+3]), Double.parseDouble(data[i+4]), Double.parseDouble(data[i+5]), Double.parseDouble(data[i+6]), Double.parseDouble(data[i+7]), Double.parseDouble(data[i+8])));
                    }

                    if(data[i].equals(AllShapesEnum.allShapes.Ellipse)){
                        shapes.add(new Ellipse(Double.parseDouble(data[i+1]), Double.parseDouble(data[i+2]), Double.parseDouble(data[i+3]),Double.parseDouble(data[i+4]),Double.parseDouble(data[i+5]),Double.parseDouble(data[i+6]),Double.parseDouble(data[i+7]),Double.parseDouble(data[i+8])));
                    }

                    if(data[i].equals(AllShapesEnum.allShapes.CompositeShape)){
                        System.out.println("compositeShape addShape");
                        while(!(data[i].equals("\n/CompositeShape")))
                        {

                            String packetString = "";
                            String [] broke2 = null;
                            while(!(data[i].equals("\n/CompositeShape")))
                            {
                                packetString = packetString.concat(data[i]);
                                packetString = packetString.concat(",");
                                i++;
                            }
                            broke2 = packetString.split(",");
                            CompositeShape compositeShape = new CompositeShape(broke2);
                            shapes.add(compositeShape);
                        }
                    }
                    if(data[i].equals(AllShapesEnum.allShapes.EmbeddedImage)){
                        shapes.add(new EmbeddedImage(Double.parseDouble(data[i+1]), Double.parseDouble(data[i+2]), Double.parseDouble(data[i+3]), Double.parseDouble(data[i+4]), data[i+5]));
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
                    for (Shapes shape : shapes) {
                        System.out.println(shape.getClass().getName());
                        if (!(shape.getClass().getName().equals("examples.shapes.CompositeShape"))) {
                            bufferedWriter.write(shape.toString());
                            bufferedWriter.newLine();
                        }
                        else{
                            bufferedWriter.write("CompositeShape,");
                            bufferedWriter.newLine();
                            bufferedWriter.write("/CompositeShape,");
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

            double sumOfAllAreas = 0;
            for(Shapes shape : shapes){
                sumOfAllAreas = sumOfAllAreas + shape.computeArea();
            }
            return sumOfAllAreas;
        }

        @Override
        public void move(double deltaX, double deltaY) throws ShapeException {
            for(Shapes shape : shapes) {
                shape.move(deltaX,deltaY);
            }
        }

        //TODO: Modify the scale method at the moment just addding to remove errors
        @Override
        public void scale(double scaleFactor) throws ShapeException {
            Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");

        }

        @Override
        public void render(Graphics graphics, int xOffset, int yOffset) throws ShapeException {
            for(Shapes shape : shapes)
            {
                shape.render(graphics,xOffset,yOffset);
            }
        }

    }
